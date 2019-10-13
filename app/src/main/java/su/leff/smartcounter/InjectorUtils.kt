package su.leff.smartcounter

import android.content.Context
import su.leff.smartcounter.database.AppDatabase
import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.meal.MealRepository
import su.leff.smartcounter.viewmodels.FoodViewModelFactory
import su.leff.smartcounter.viewmodels.MealViewModelFactory


object InjectorUtils {
    private fun getFoodRepository(context: Context): FoodRepository {
        return FoodRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).foodDAO())
    }

    private fun getMealRepository(context: Context): MealRepository {
        return MealRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).mealDAO())
    }

    fun provideFoodViewModelFactory(
        context: Context
    ): FoodViewModelFactory {
        val repository = getFoodRepository(context)
        return FoodViewModelFactory(repository)
    }

    fun provideMealViewModelFactory(
        context: Context
    ): MealViewModelFactory {
        val repository = getMealRepository(context)
        return MealViewModelFactory(repository)
    }
}