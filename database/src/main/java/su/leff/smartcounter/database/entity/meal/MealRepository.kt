package su.leff.smartcounter.database.entity.meal

import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import java.util.*

class MealRepository(private val mDao: MealDAO,
                     private val foodRepository: FoodRepository,
                     private val foodTypeRepository: FoodTypeRepository
) {

    suspend fun insertMeal(meal: Meal) {
        mDao.insertMeal(MealEntity.from(meal))
    }

    suspend fun fetchAllMeal(): List<Meal> {
        return fillMealWithFoodInfo(MealEntity.toMeal(mDao.fetchAllMeal()))
    }

    suspend fun getMealByDay(timestamp: Long): List<Meal> {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = timestamp
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        val start = calendar.timeInMillis
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 0)
        }

        val end = calendar.timeInMillis

        return fillMealWithFoodInfo(MealEntity.toMeal(mDao.getAllMealsByDay(start, end)))
    }

    private suspend fun fillMealWithFoodInfo(mealList: List<Meal>): List<Meal> {
        for (meal in mealList) {
            val foodlist = foodRepository.getFoodByMeal(meal.id)

            for (food in foodlist) {
                meal.description += food.foodType
            }
        }
        return mealList
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