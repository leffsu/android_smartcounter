package su.leff.smartcounter.database.entity.food

import androidx.lifecycle.Transformations
import java.util.*


class FoodRepository(private val mDao: FoodDAO) {

    fun insertFood(food: Food) {
        mDao.insertFood(FoodEntity.from(food))
    }

    fun fetchAllFood(): List<Food> {
        return FoodEntity.toFood(mDao.fetchAllFood())
    }

    fun getFood(foodId: Long): Food {
        return mDao.getFood(foodId).toFood()
    }

    fun getFoodByMeal(mealId: Long): List<Food> {
        return FoodEntity.toFood(mDao.getFoodByMeal(mealId))
    }

    fun updateFood(food: Food) {
        return mDao.updateFood(FoodEntity.from(food))
    }

    fun deleteFood(food: Food) {
        return mDao.deleteFood(FoodEntity.from(food))
    }
}