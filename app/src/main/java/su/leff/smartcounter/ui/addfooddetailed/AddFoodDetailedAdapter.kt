package su.leff.smartcounter.ui.addfooddetailed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.R
import su.leff.smartcounter.ui.homepage.HomePageFoodAdapter

class AddFoodDetailedAdapter(val context: Context?, newList: ArrayList<FoodDetailed>) :
    RecyclerView.Adapter<AddFoodDetailedAdapter.FoodViewHolder>() {

    private val foodList: ArrayList<FoodDetailed> = ArrayList<FoodDetailed>(newList)

    fun setFoodList(newList: ArrayList<FoodDetailed>) {
        foodList.clear()
        foodList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val holder = FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.viewholder_food, parent, false))
        holder.color()
        holder.itemView.cardViewFood.setOnClickListener {  }
        return holder
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }


    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: FoodDetailed) {
            itemView.txvFoodTitle.text = food.title
            itemView.txvFoodDescription.text = food.description
            itemView.txvFoodCalories.text = "~ ${food.calories}kcal"
        }

        fun color() {
            context?.applicationContext?.let {
                itemView.cardViewFood.setCardBackgroundColor(ResourceManager.getItemBackgroundColor(it))
                itemView.txvFoodTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
                itemView.txvFoodDescription.setTextColor(ResourceManager.getOrangeAccentColor(it))
                itemView.txvFoodCalories.setTextColor(ResourceManager.getUsualTextColorColor(it))
            }
        }
    }

}