package su.leff.smartcounter.ui.addfooddetailed

import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.meal.Meal

interface IAddFoodPickReceiver {
    fun onFoodClicked(foodType: FoodDetailed)
    fun onFoodDeleted(food: Food)
}

interface IListFoodPickReceiver {
    fun onFoodClicked(food: Meal)
    fun onFoodDeleted(food: Meal)
}