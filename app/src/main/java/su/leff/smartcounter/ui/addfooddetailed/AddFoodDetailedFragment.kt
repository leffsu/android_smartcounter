package su.leff.smartcounter.ui.addfooddetailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_addfooddetailed.*
import org.koin.android.architecture.ext.viewModel
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.foodtype.FoodType
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.viewmodels.FoodTypeViewModel
import su.leff.smartcounter.viewmodels.FoodViewModel
import su.leff.smartcounter.viewmodels.MealViewModel

class AddFoodDetailedFragment : Fragment(), IAddFoodPickReceiver {

    val viewmodel by viewModel<FoodTypeViewModel>()
    val foodViewModel by viewModel<FoodViewModel>()
    val mealViewModel by viewModel<MealViewModel>()

    var mealType: Long = -1L

    var adapter: AddFoodDetailedAdapter? = null

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

        viewmodel.allTasks()
        viewmodel.allMeals.observe(viewLifecycleOwner, Observer {

            if (adapter == null) {
                recyclerFood.layoutManager = LinearLayoutManager(context)
                adapter = AddFoodDetailedAdapter(activity, foodTypesToDetailed(it), this)
                recyclerFood.adapter = adapter
            } else {
                adapter?.setFoodList(foodTypesToDetailed(it))
                adapter?.notifyDataSetChanged()
            }
        })

        edtSearchBar.doAfterTextChanged {
            val text = edtSearchBar.text.toString()
            if (text.length == 1 && text == " ") {
                edtSearchBar.setText("")
            } else {
                adapter?.search(text)
            }
        }

        setTitle("snacky snacks")
        setSearchBarHint("what was that?")

        imgvBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        imgvScan.setOnClickListener {
            findNavController().navigate(R.id.action_addFoodDetailedFragment_to_QRScanFragment)
        }
    }

    private fun foodTypesToDetailed(typeList: List<FoodType>): List<FoodDetailed> {
        val newList = ArrayList<FoodDetailed>()
        for (type in typeList) {
            newList.add(FoodDetailed(type.id, type.title, "", type.calories))
        }
        return newList
    }

    private fun setTitle(value: String) {
        txvTitle.text = value
    }

    private fun setSearchBarHint(value: String) {
        edtSearchBar.hint = value
    }

    override fun process(foodType: FoodDetailed) {
        foodViewModel.allFood()

        mealViewModel.allMeals.observe(viewLifecycleOwner, Observer {
            var foundMeal: Meal? = null

            for (meal in it) {
                if (meal.mealType == mealType) {
                    foundMeal = meal
                }
            }

            if (foundMeal == null) {
                foundMeal = Meal(0L, System.currentTimeMillis(), mealType)
                mealViewModel.insertMeal(foundMeal)
                mealViewModel.mealByDay(System.currentTimeMillis())
            } else {
                val food = Food(0L, foundMeal.id, foodType.id, 100)
                var foundFood: Food? = null
                for (foodToFind in foodViewModel.allFood.value!!) {
                    if (foodToFind.mealId == food.mealId && food.foodType == foodToFind.foodType) {
                        foundFood = food
                    }
                }
                if (foundFood == null)
                    foodViewModel.insertFood(food)
            }
        })
        mealViewModel.allTasks()
    }
}