package su.leff.smartcounter.ui.addfooddetailed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewholder_food.view.*
import su.leff.smartcounter.colorer.ResourceManager
import su.leff.smartcounter.R
import su.leff.smartcounter.database.entity.food.Food

class AddFoodDetailedAdapter(
    private val context: Context?,
    private val newList: List<FoodDetailed>,
    private val receiver: IAddFoodPickReceiver
) :
    RecyclerView.Adapter<AddFoodDetailedAdapter.FoodViewHolder>() {
    private val cachedList = ArrayList(newList)
    private val foodList: ArrayList<FoodDetailed> = ArrayList<FoodDetailed>(newList)

    private val selectedFoodList = ArrayList<FoodDetailed>()
    private val foodListToSelectFrom = ArrayList<FoodDetailed>()

    private fun formList() {
        foodList.clear()
        foodList.addAll(selectedFoodList)
        foodList.addAll(foodListToSelectFrom)
    }

    fun setFoodList(newList: List<FoodDetailed>) {
        foodListToSelectFrom.clear()
        foodListToSelectFrom.addAll(newList)
        formList()
    }

    fun setSelectedFoodList(newList: List<FoodDetailed>) {
        selectedFoodList.clear()
        selectedFoodList.addAll(newList)
        formList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val holder = FoodViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.viewholder_food,
                parent,
                false
            )
        )

        holder.color()

        holder.itemView.cardViewFood.setOnClickListener {
            if(!foodList[holder.adapterPosition].alreadyAdded){
                receiver.onFoodClicked(foodList[holder.adapterPosition])
            }
        }

        holder.itemView.btnDelete.setOnClickListener {
            if(foodList[holder.adapterPosition].alreadyAdded) {
                receiver.onFoodDeleted(foodList[holder.adapterPosition].originalLink as Food)
            }
        }

        return holder
    }

    fun search(string: String) {
        val newList = cachedList.filter { foodDetailed ->
            foodDetailed.title.toLowerCase().contains(string.toLowerCase())
        }
        foodList.clear()
        foodList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodList[position])
    }


    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(food: FoodDetailed) {
            if (food.alreadyAdded) {
                itemView.swipe.isEnabled = true
                itemView.rights.visibility = View.VISIBLE
                itemView.cardViewFood.setCardBackgroundColor(
                    context?.getColor(R.color.orangeAccentSlight) ?: 0
                )
            } else {
                itemView.swipe.isEnabled = false
                itemView.rights.visibility = View.GONE
                itemView.cardViewFood.setCardBackgroundColor(
                    context?.getColor(R.color.itemBackground) ?: 0
                )
            }
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