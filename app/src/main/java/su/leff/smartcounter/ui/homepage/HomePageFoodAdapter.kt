package su.leff.smartcounter.ui.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.colorer.ResourceManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import su.leff.smartcounter.R

class HomePageFoodAdapter(val context: Context?, newList: ArrayList<Food>) :
    RecyclerView.Adapter<HomePageFoodAdapter.FoodViewHolder>() {

    private val foodList: ArrayList<Food> = ArrayList<Food>(newList)

    fun setFoodList(newList: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val holder = FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.viewholder_food, parent, false))
        holder.color()
        return holder
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }


    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: Food) {
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