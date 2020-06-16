package su.leff.smartcounter.ui.addfooddetailed

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcoscg.dialogsheet.DialogSheet
import kotlinx.android.synthetic.main.fragment_addfoodcategory.*
import kotlinx.android.synthetic.main.fragment_addfooddetailed.*
import kotlinx.android.synthetic.main.fragment_addfooddetailed.imgvBackButton
import kotlinx.android.synthetic.main.layout_food_dialog.view.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.foodtype.FoodType
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.ui.homepage.HomePageFragment
import su.leff.smartcounter.util.BaseFragment
import su.leff.smartcounter.util.ImageChooserUtility
import su.leff.smartcounter.util.getMealName
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class AddFoodDetailedFragment : BaseFragment(), IAddFoodPickReceiver {

    private var mealType: Long = -1L
    private val CODE = 1

    var adapter: AddFoodDetailedAdapter? = null

    lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addfooddetailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealType = arguments?.getLong("mealType") ?: -1L

        activity?.applicationContext?.let {
            addfooddetailed_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtSearchBar.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtSearchBar.setHintTextColor(ResourceManager.getUsualTextColorColor(it))
            imgvScan.setImageResource(ResourceManager.getScanButtonResource())
        }

        recyclerFood.layoutManager = LinearLayoutManager(context)
        adapter = AddFoodDetailedAdapter(activity, ArrayList(), this)
        recyclerFood.adapter = adapter

        mealViewModel.foodByMeal.observe(viewLifecycleOwner, Observer {
            adapter?.setSelectedFoodList(foodToDetailed(it))
            adapter?.notifyDataSetChanged()
        })

        mealViewModel.allFoodTypes.observe(viewLifecycleOwner, Observer {
            adapter?.setFoodList(foodTypesToDetailed(it))
            adapter?.notifyDataSetChanged()
        })

        if(mealViewModel.activeMeal.value!=null){
            mealViewModel.getFoodByMeal((mealViewModel.activeMeal.value as Meal).id)
        }


        mealViewModel.allFoodTypes()

        edtSearchBar.doAfterTextChanged {
            val text = edtSearchBar.text.toString()
            if (text.length == 1 && text == " ") {
                edtSearchBar.setText("")
            } else {
                adapter?.search(text)
            }
        }

        var mealTypeName = getMealName(mealType)

        setTitle(mealTypeName)
        setSearchBarHint("а что съели?")

        imgvBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFoodDetailedFragment_to_homePageFragment)
            mealViewModel.clearActiveMeal()
        }

        imgvScan.setOnClickListener {
            val intent = ImageChooserUtility.getPickImageIntent(requireContext())
            startActivityForResult(intent, CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == CODE) {
                val bitmap =
                    ImageChooserUtility.getImageFromResult(requireContext(), resultCode, data)
                val array = data!!.data!!.toString().split('/')
                val name = array[array.size - 1]
                file = saveBitmapToFile(bitmap, requestCode, name)

                mealViewModel.activeMeal
                val bundle = bundleOf("file" to file.name, "mealType" to mealType)
                findNavController()
                    .navigate(R.id.action_addFoodDetailedFragment_to_lol, bundle)
            }
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap, position: Int, name: String): File {
        val filename = "${name}.jpeg"
        val file = File(requireActivity().cacheDir, filename)
        file.createNewFile()

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream)
        val bitmapData = byteArrayOutputStream.toByteArray()

        val fos = FileOutputStream(file)
        fos.write(bitmapData)
        fos.flush()
        fos.close()

        return file
    }

    private fun foodTypesToDetailed(typeList: List<FoodType>): List<FoodDetailed> {
        val newList = ArrayList<FoodDetailed>()
        for (type in typeList) {
            newList.add(FoodDetailed(type.id, type.title, "", type.calories, false, type as Any))
        }
        return newList
    }

    private fun foodToDetailed(typeList: List<Food>): List<FoodDetailed> {
        val newList = ArrayList<FoodDetailed>()
        for (type in typeList) {
            val food = FoodDetailed(
                type.id,
                mealViewModel.foodTypesMap[type.foodType]?.title ?: "",
                "",
                ((mealViewModel.foodTypesMap[type.foodType]?.calories!! * type.amount) / 100),
                true,
                type as Any
            )
            newList.add(
                food
            )
        }
        return newList
    }

    private fun setTitle(value: String) {
        txvTitle.text = value
    }

    private fun setSearchBarHint(value: String) {
        edtSearchBar.hint = value
    }

    override fun onFoodClicked(foodType: FoodDetailed) {


        fun saveToDatabase(gramm: Int) {
            mealViewModel.activeMeal.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val food = Food(0L, it.id, foodType.id, gramm.toLong())
                    mealViewModel.insertFood(food)
                }
            })
            if (mealViewModel.activeMeal.value == null) {
                val meal = Meal(0L, System.currentTimeMillis(), mealType)
                mealViewModel.insertMeal(meal)
                mealViewModel.getMealByTimestamp(meal.timestamp)
            }

        }

        showDialog(::saveToDatabase)


    }

    override fun onFoodDeleted(food: Food) {
        mealViewModel.deleteFood(food)
    }

    fun showDialog(save: (gr: Int) -> Unit) {

        var gramms: Int = 400

        val dialog = DialogSheet(context)
            .setView(R.layout.layout_food_dialog)
            .setColoredNavigationBar(true)
            .setTitleTextSize(20) // In SP
            .setCancelable(true)
            .setPositiveButton(android.R.string.ok) {
                // Your action
                save(gramms)
//                HomePageFragment.debug = true
//                findNavController().navigate(R.id.action_addFoodDetailedFragment_to_homePageFragment)
            }
            .setNegativeButton(android.R.string.cancel) {
                // Your action
            }
            .setRoundedCorners(false) // Default value is true
            .setBackgroundColor(Color.BLACK) // Your custom background color
            .setButtonsColorRes(R.color.colorAccent) // You can use dialogSheetAccent style attribute instead

        val inflatedView = dialog.inflatedView
        inflatedView.txvHowManyGramms.text = "how many gramms?"
        inflatedView.txvGramms.text = gramms.toString()

        inflatedView.imgPlus.setOnClickListener {
            if (gramms < 950) {
                gramms += 50
            }
            inflatedView.txvGramms.text = gramms.toString()
        }

        inflatedView.imgMinus.setOnClickListener {
            if (gramms > 50) {
                gramms -= 50
            }
            inflatedView.txvGramms.text = gramms.toString()
        }

        dialog.setOnDismissListener {
            // memory leaks
            inflatedView.imgPlus.setOnClickListener(null)
            inflatedView.imgMinus.setOnClickListener(null)
        }

        dialog.show()
    }
}