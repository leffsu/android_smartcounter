package su.leff.smartcounter.ui.scan

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_lol.*
import kotlinx.android.synthetic.main.fragment_lol.imgvBackButton
import kotlinx.android.synthetic.main.fragment_lol.txvTitle
import su.leff.smartcounter.R
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.util.BaseFragment
import su.leff.smartcounter.util.ImageChooserUtility
import java.io.File
import kotlin.random.Random

class ScanFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lol, container, false)
    }

    private var mealType: Long = -1L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filename = arguments?.getString("file") ?: ""
        mealType = arguments?.getLong("mealType") ?: -1L
        val file = File(requireActivity().cacheDir, filename)


        imgvBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_lol_to_homePageFragment)
        }

        pic.setImageBitmap(ImageChooserUtility.getImageResized(requireContext(), file.toUri()))
        fab.visibility = View.GONE

        enqueueLoading()

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_lol_to_homePageFragment)
        }
    }

    private fun saveToDatabase(gramm: Int, foodType: Long, mealType: Long) {
        mealViewModel.activeMeal.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val food = Food(0L, it.id, foodType, gramm.toLong())
                mealViewModel.insertFood(food)
            }
        })
        if (mealViewModel.activeMeal.value == null) {
            val meal = Meal(0L, System.currentTimeMillis(), mealType)
            mealViewModel.insertMeal(meal)
            mealViewModel.getMealByTimestamp(meal.timestamp)
        }
    }

    private fun enqueueLoading() {
        val randomTime = Random(125).nextLong(25, 37)
        val handler = Handler()

        handler.postDelayed(Runnable {
            fab.visibility = View.VISIBLE
            txvTitle.text = "Всё так?"
            when (getTime()) {
                0 -> {
                    txvDescription.text = "Похоже на 350 гр картофельное пюре, 230 гр купата"
                    saveToDatabase(350, 9L, mealType)
                    saveToDatabase(230, 8L, mealType)
                }
                else -> {
                    txvDescription.text = "Похоже на 200 гр огурец, 80 гр помидор"
                    saveToDatabase(200, 5L, mealType)
                    saveToDatabase(80, 6L, mealType)
                }
            }

        }, randomTime * 1000L)

    }

    companion object {
        var timex: Int = 0

        fun getTime(): Int {
            val time = timex
            timex++
            return time
        }
    }
}