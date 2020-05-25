package su.leff.smartcounter.database.entity.meal

import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import java.util.*

class MealRepository(private val mDao: MealDAO
) {

    suspend fun insertMeal(meal: Meal) {
        mDao.insertMeal(MealEntity.from(meal))
    }

    suspend fun fetchAllMeal(): List<Meal> {
        return MealEntity.toMeal(mDao.fetchAllMeal())
    }


    suspend fun getMeal(mealId: Int): Meal {
        return MealEntity.toMeal(mDao.getMeal(mealId))
    }

    suspend fun updateMeal(meal: Meal) {
        return mDao.updateMeal(MealEntity.from(meal))
    }

    suspend fun deleteMeal(meal: Meal) {
        return mDao.deleteMeal(MealEntity.from(meal))
    }
}