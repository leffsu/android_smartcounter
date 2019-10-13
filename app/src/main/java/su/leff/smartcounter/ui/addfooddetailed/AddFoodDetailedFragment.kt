package su.leff.smartcounter.ui.addfooddetailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_addfooddetailed.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager

class AddFoodDetailedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_addfooddetailed, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.applicationContext?.let {
            addfooddetailed_background.setBackgroundColor(ResourceManager.getBackgroundColor(it))
            txvTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtSearchBar.setTextColor(ResourceManager.getUsualTextColorColor(it))
            edtSearchBar.setHintTextColor(ResourceManager.getUsualTextColorColor(it))
            imgvScan.setImageResource(ResourceManager.getScanButtonResource())
        }

        val arrayList = ArrayList<FoodDetailed>()
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))
        arrayList.add(FoodDetailed("123", "123", 124124))

        recyclerFood.layoutManager = LinearLayoutManager(context)

        val addFoodDetailedAdapter = AddFoodDetailedAdapter(activity, arrayList)
        recyclerFood.adapter = addFoodDetailedAdapter

        setTitle("snacky snacks")
        setSearchBarHint("what was that?")

        imgvBackButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setTitle(value: String) {
        txvTitle.text = value
    }

    private fun setSearchBarHint(value: String) {
        edtSearchBar.hint = value
    }
}