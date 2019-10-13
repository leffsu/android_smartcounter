package su.leff.smartcounter.ui.addfoodcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbDinner.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbDrink.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbEvening.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbLunch.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbSnack.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbWater.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        cbSupper.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addFoodCategoryFragment_to_addFoodDetailedFragment)
        }

        imgvBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setTitleText(value: String) {
        txvFoodTitle.text = value
    }
}