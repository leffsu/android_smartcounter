package su.leff.smartcounter.ui.addfooddetailed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.R
import su.leff.smartcounter.ui.homepage.HomePageFoodAdapter

class AddFoodDetailedAdapter(
    private val context: Context?,
    private val newList: List<FoodDetailed>,
    private val receiver: IAddFoodPickReceiver) :
    RecyclerView.Adapter<AddFoodDetailedAdapter.FoodViewHolder>() {
    private val cachedList = ArrayList(newList)
    private val foodList: ArrayList<FoodDetailed> = ArrayList<FoodDetailed>(newList)

    fun setFoodList(newList: List<FoodDetailed>) {
        foodList.clear()
        foodList.addAll(newList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val holder = FoodViewHolder(// 1
            LayoutInflater.from(context).inflate(// 2
                R.layout.viewholder_food,
                parent,
                false
            )
        )
        holder.color()// 3
        holder.itemView.cardViewFood.setOnClickListener {// 4
            receiver.process(foodList[holder.adapterPosition])
        }
        return holder// 5
    }

    fun search(string: String) {
        val newList = cachedList.filter { foodDetailed -> foodDetailed.title.toLowerCase().contains(string.toLowerCase()) }
        foodList.clear()
        foodList.addAll(newList)
        notifyDataSetChanged()
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