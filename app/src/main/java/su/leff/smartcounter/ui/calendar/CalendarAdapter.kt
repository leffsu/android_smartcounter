package su.leff.smartcounter.ui.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.database.entity.meal.Meal

class CalendarAdapter(val context: Context?, foodList: ArrayList<Meal>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarFoodViewHolder>() {

    val foodList: ArrayList<Meal> = foodList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarFoodViewHolder {
        val holder = CalendarFoodViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.viewholder_food,
                parent,
                false
            )
        )
        holder.color()
        holder.itemView.cardViewFood.setOnClickListener { }
        return holder
    }

    fun setList(list: List<Meal>){
        foodList.clear()
        foodList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CalendarFoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }


    inner class CalendarFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: Meal) {
            itemView.txvFoodTitle.text = food.title
            itemView.txvFoodDescription.text = food.description
            itemView.txvFoodCalories.text = "~ ${food.calories}kcal"
        }

        fun color() {
            context?.applicationContext?.let {
                itemView.cardViewFood.setCardBackgroundColor(
                    ResourceManager.getItemBackgroundColor(
                        it
                    )
                )
                itemView.txvFoodTitle.setTextColor(ResourceManager.getUsualTextColorColor(it))
                itemView.txvFoodDescription.setTextColor(ResourceManager.getOrangeAccentColor(it))
                itemView.txvFoodCalories.setTextColor(ResourceManager.getUsualTextColorColor(it))
            }
        }
    }
}