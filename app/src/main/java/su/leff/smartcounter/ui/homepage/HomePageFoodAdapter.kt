package su.leff.smartcounter.ui.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.R
import su.leff.smartcounter.database.entity.meal.Meal

class HomePageFoodAdapter(val context: Context?, newList: List<Meal>) :
    RecyclerView.Adapter<HomePageFoodAdapter.FoodViewHolder>() {
    init {

    }

    private val tempFoodList: ArrayList<Meal> = ArrayList<Meal>(newList)

    fun setFoodList(newList: List<Meal>) {
        tempFoodList.clear()
        tempFoodList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val holder = FoodViewHolder(LayoutInflater.from(context).inflate(R.layout.viewholder_food, parent, false))
        holder.color()
        holder.itemView.cardViewFood.setOnClickListener {  }
        return holder
    }

    override fun getItemCount(): Int {
        return tempFoodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(tempFoodList[position])
    }


    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tempFood: Meal) {
            itemView.txvFoodTitle.text = tempFood.id.toString()
            itemView.txvFoodDescription.text = tempFood.description
            itemView.txvFoodCalories.text = "~ ${tempFood.calories}kcal"
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