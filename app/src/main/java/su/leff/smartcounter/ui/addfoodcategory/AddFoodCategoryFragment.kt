package su.leff.smartcounter.ui.addfoodcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_addfoodcategory.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager

class AddFoodCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addfoodcategory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.applicationContext?.let {
            addfoodcategory_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvFoodTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
        }

        setTitleText("tell me about that food")

        cbBreakfast.setTitleText("Breakfast")
        cbDinner.setTitleText("Dinner")
        cbDrink.setTitleText("Drink")
        cbEvening.setTitleText("Evening")
        cbLunch.setTitleText("Lunch")
        cbSnack.setTitleText("Snack")
        cbSupper.setTitleText("Supper")
        cbWater.setTitleText("Water")


        cbBreakfast.setOnClickListener {
            val bundle = bundleOf("mealType" to 1L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbDinner.setOnClickListener {
            val bundle = bundleOf("mealType" to 2L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbDrink.setOnClickListener {
            val bundle = bundleOf("mealType" to 3L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbEvening.setOnClickListener {
            val bundle = bundleOf("mealType" to 4L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbLunch.setOnClickListener {
            val bundle = bundleOf("mealType" to 5L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbSnack.setOnClickListener {
            val bundle = bundleOf("mealType" to 6L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbWater.setOnClickListener {
            val bundle = bundleOf("mealType" to 7L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        cbSupper.setOnClickListener {
            val bundle = bundleOf("mealType" to 8L)
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment, bundle)
        }

        imgvBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setTitleText(value: String) {
        txvFoodTitle.text = value
    }
}