package su.leff.smartcounter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.database.entity.meal.MealRepository


class MealViewModel(private val foodRepository: MealRepository) : ViewModel() {

    private val food = MediatorLiveData<List<Meal>>()

    val allMeals: LiveData<List<Meal>> = food  // 1

    fun allTasks() = viewModelScope.launch {
        food.postValue(foodRepository.fetchAllMeal())   // 3
    }

    fun insertFood(t: Meal) = viewModelScope.launch {
        foodRepository.insertMeal(t)
    }

    fun updateFood(t: Meal) = viewModelScope.launch {
        foodRepository.updateMeal(t)
    }

    fun deleteFood(t: Meal) = viewModelScope.launch {
        foodRepository.deleteMeal(t)
    }

    fun getMeal(t: Int) = viewModelScope.launch {
        foodRepository.getMeal(t)
    }
}