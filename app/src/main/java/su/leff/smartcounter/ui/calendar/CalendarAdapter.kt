package su.leff.smartcounter.ui.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.R
import su.leff.smartcounter.colorer.ResourceManager

class CalendarAdapter(val context: Context?, foodList: List<CalendarFood>) :
    RecyclerView.Adapter<CalendarAdapter.CalendarFoodViewHolder>() {

    val foodList: List<CalendarFood> = foodList

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

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: CalendarFoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }


    inner class CalendarFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: CalendarFood) {
            itemView.txvFoodTitle.text = food.typeOfFood.toString()
            itemView.txvFoodDescription.text = food.food
            itemView.txvFoodCalories.text = "~ ${food.kcalAmount}kcal"
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