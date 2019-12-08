package su.leff.smartcounter.database.entity.food

import androidx.lifecycle.Transformations
import java.util.*


class FoodRepository(private val mDao: FoodDAO) {

    suspend fun insertFood(food: Food) {
        mDao.insertFood(FoodEntity.from(food))
    }

    suspend fun fetchAllFood(): List<Food> {
        return FoodEntity.toFood(mDao.fetchAllFood())
    }

    suspend fun getFood(foodId: Long): Food {
        return mDao.getFood(foodId).toFood()
    }

    suspend fun getFoodByMeal(mealId: Long): List<Food> {
        return FoodEntity.toFood(mDao.getFoodByMeal(mealId))
    }

    suspend fun updateFood(food: Food) {
        return mDao.updateFood(FoodEntity.from(food))
    }

    suspend fun deleteFood(food: Food) {
        return mDao.deleteFood(FoodEntity.from(food))
    }
}