package su.leff.smartcounter.database.entity.meal

import su.leff.smartcounter.database.entity.food.FoodDAO
import su.leff.smartcounter.database.entity.food.FoodRepository

class MealRepository(private val mDao: MealDAO) {

    suspend fun insertMeal(meal: Meal): Int? {
        return mDao.insertMeal(meal)
    }


    suspend fun fetchAllMeal(): List<Meal> {
        return mDao.fetchAllMeal()
    }


    suspend fun getMeal(mealId: Int): Meal {
        return mDao.getMeal(mealId)
    }


    suspend fun updateMeal(meal: Meal) {
        return mDao.updateMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal) {
        return mDao.deleteMeal(meal)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: MealRepository? = null

        fun getInstance(mealDAO: MealDAO) =
            instance ?: synchronized(this) {
                instance ?: MealRepository(mealDAO).also { instance = it }
            }
    }
}