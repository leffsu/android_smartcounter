package su.leff.smartcounter.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import su.leff.smartcounter.R
import android.content.res.ColorStateList
import android.os.Build
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import su.leff.smartcounter.colorer.ResourceManager
import androidx.navigation.Navigation




class HomePageFragment : Fragment() {

    var adapter: HomePageFoodAdapter? = null

    val ANIMATION_TIME = 2000

    var chartClicked = 0L


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        setCarboCount("Carbo (160/240)")
        setFatCount("Fat (200/240)")
        setProteinCount("Protein (120/240)")
        initGraph()
        setWelcomeMessage("hello there, Michael")

        recyclerFood.layoutManager = LinearLayoutManager(context)


        val foodArrayList = ArrayList<Food>()

        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))
        foodArrayList.add(Food("123", "76236", 124))

        adapter = HomePageFoodAdapter(context, foodArrayList)

        recyclerFood.adapter = adapter

        fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_addFoodCategoryFragment)
        }

        imgPreferences.setOnClickListener {
            findNavController().navigate(R.id.action_homePageFragment_to_foodInfoFragment)
        }

        imgCalendar.setOnClickListener {

        }

        slimChart.setOnClickListener {
            if (System.currentTimeMillis() > chartClicked + ANIMATION_TIME) {
                chartClicked = System.currentTimeMillis()
                slimChart.playStartAnimation()
            }
        }

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
        slimChart.text = "234"
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
}