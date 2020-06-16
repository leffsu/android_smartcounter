package su.leff.smartcounter.database.entity.meal

import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import java.util.*

class MealRepository(private val mDao: MealDAO
) {

     fun insertMeal(meal: Meal) {
        mDao.insertMeal(MealEntity.from(meal))
    }

     fun getMealByTimestamp(t: Long): Meal {
        return MealEntity.toMeal(mDao.getMealByTimestamp(t))
    }

     fun fetchAllMeal(): List<Meal> {
        return MealEntity.toMeal(mDao.fetchAllMeal())
    }

     fun fetchAllMealByDay(timestampStart: Long, timestampEnd: Long): List<Meal> {
        return MealEntity.toMeal(mDao.getAllMealsByDay(timestampStart, timestampEnd))
    }

     fun getMeal(mealId: Int): Meal {
        return MealEntity.toMeal(mDao.getMeal(mealId))
    }

     fun updateMeal(meal: Meal) {
        return mDao.updateMeal(MealEntity.from(meal))
    }

     fun deleteMeal(meal: Meal) {
        return mDao.deleteMeal(MealEntity.from(meal))
    }
}