package su.leff.smartcounter.ui.homepage

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.ui.addfooddetailed.IListFoodPickReceiver
import su.leff.smartcounter.util.BaseFragment
import kotlin.random.Random


class HomePageFragment : BaseFragment(), IListFoodPickReceiver {

//    val mealViewModel by viewModel<MealViewModel>()
//
//    val foodViewModel by viewModel<FoodViewModel>()
//
//    val foodTypeViewModel by viewModel<FoodTypeViewModel>()

    var adapter: HomePageFoodAdapter? = null

    val ANIMATION_TIME = 2000

    var chartClicked = 0L

    var data = ArrayList<Meal>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        mealViewModel.allTasks()
//        foodViewModel.allFood()
//        foodTypeViewModel.allTasks()

        mealViewModel.allFood()
        mealViewModel.allFoodTypes()
        mealViewModel.allMeals()

        activity?.applicationContext?.let {
            login_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvWelcomeMessage.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvCarbo.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvFat.setTextColor(ResourceManager.getUsualTextColorColor(it))
            txvProtein.setTextColor(ResourceManager.getUsualTextColorColor(it))
            appbarBottom.setBackgroundColor(ResourceManager.getBottomAppBarColor(it))

            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
                fabAdd.supportBackgroundTintList =
                    ColorStateList.valueOf(ResourceManager.getOrangeAccentColor(it));
            } else {
                ViewCompat.setBackgroundTintList(
                    fabAdd,
                    ColorStateList.valueOf(ResourceManager.getOrangeAccentColor(it))
                );
            }
        }

        initGraph()
        setWelcomeMessage("Привет, ${shared.firstName}")

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_addFoodCategoryFragment)
        }

        imgPreferences.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_foodInfoFragment)
        }

        imgCalendar.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_calendarFragment)
        }

        slimChart.setOnClickListener {
            if (System.currentTimeMillis() > chartClicked + ANIMATION_TIME) {
                chartClicked = System.currentTimeMillis()
                slimChart.playStartAnimation()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        recyclerFood.layoutManager = LinearLayoutManager(context)

        data = mealViewModel.getMealOnDay(System.currentTimeMillis()) as ArrayList<Meal>
        adapter = HomePageFoodAdapter(context, data, this@HomePageFragment)
        activity?.runOnUiThread {
            recyclerFood.adapter = adapter//            }
        }
        updateChart()
        mealViewModel.clearActiveMeal()
    }


    private fun updateList() {
        data = mealViewModel.getMealOnDay(System.currentTimeMillis()) as ArrayList<Meal>
        adapter?.setFoodList(data)
        updateChart()
    }

    private fun setWelcomeMessage(value: String) {
        txvWelcomeMessage.text = value
    }

    private fun setCarboCount(value: String) {
        txvCarbo.text = value
    }

    private fun setFatCount(value: String) {
        txvFat.text = value
    }

    private fun setProteinCount(value: String) {
        txvProtein.text = value
    }

    private fun initGraph() {
        //Optional - create colors array

        //Create array for your stats
        val stats = FloatArray(3)
        stats[0] = 100f
        stats[1] = 85f
        stats[2] = 40f

        slimChart.stats = stats

        //Play animation
        slimChart.setStartAnimationDuration(ANIMATION_TIME)

        slimChart.strokeWidth = 8

        activity?.applicationContext?.let {

            slimChart.textColor = ResourceManager.getUsualTextColorColor()

            val colors = IntArray(4)
            colors[0] = ResourceManager.getGreenAccentColor(it)
            colors[1] = ResourceManager.getOrangeAccentColor(it)
            colors[2] = ResourceManager.getPinkAccentColor(it)
            slimChart.colors = colors
        }
        slimChart.setRoundEdges(true)
    }

    private fun updateChart() {
        var counter = 0L
        var Fcounter = 0L
        var Pcounter = 0L
        var Ccounter = 0L
        for (meal in data) {
            Fcounter += meal.fats
            Pcounter += meal.proteins
            Ccounter += meal.carbo
            counter += meal.calories

        }
        slimChart.text = "${counter}"

        setCarboCount("Углеводов (${Ccounter}/150 гр)")
        setFatCount("Жиров (${Fcounter}/90 гр)")
        setProteinCount("Белков (${Pcounter}/70 гр)")

        slimChart.stats = getRelations(Fcounter, Pcounter, Ccounter)
    }

    fun getRelations(fats: Long, prot: Long, carbo: Long): FloatArray {
        val stats = FloatArray(3)
        val fatsPercentage = fats.toDouble() / 90
        val protPercentage = prot.toDouble() / 70
        val carboPercentage = carbo.toDouble() / 150
//        stats[0] = (carboPercentage * 33).toFloat()
//        stats[1] = (fatsPercentage * 33).toFloat() + stats[0]
//        stats[2] = (protPercentage * 33).toFloat() + stats[1]
        stats[0] = 100f
        stats[1] = Random(123).nextInt(30, 80).toFloat()
        stats[2] = Random(123).nextInt(10, 50).toFloat()
        return stats
    }

    companion object {
        var debug = false
        var debug1 = false
    }

    override fun onFoodClicked(food: Meal) {
        mealViewModel.activeMeal.postValue(food)
        val bundle = bundleOf("mealType" to 4L, "mealId" to food.id)
        findNavController()
            .navigate(R.id.action_homePageFragment_to_addFoodDetailedFragment, bundle)
    }

    override fun onFoodDeleted(food: Meal) {
        mealViewModel.deleteMeal(food)
        updateList()
    }
}