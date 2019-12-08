package su.leff.smartcounter.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.database.entity.meal.MealRepository


class MealViewModel(private val mealRepository: MealRepository) : ViewModel() {

    private val meals = MutableLiveData<List<Meal>>()

    val allMeals: LiveData<List<Meal>> = meals  // 1

    fun allTasks() = viewModelScope.launch {
        meals.postValue(mealRepository.fetchAllMeal())   // 3
    }

    fun mealByDay(timestamp: Long) = viewModelScope.launch {
        meals.postValue(mealRepository.getMealByDay(timestamp))
    }

    fun insertMeal(t: Meal) = viewModelScope.launch {
        mealRepository.insertMeal(t)
    }

    fun updateMeal(t: Meal) = viewModelScope.launch {
        mealRepository.updateMeal(t)
    }

    fun deleteMeal(t: Meal) = viewModelScope.launch {
        mealRepository.deleteMeal(t)
    }

    fun getMeal(t: Int) = viewModelScope.launch {
        mealRepository.getMeal(t)
    }
}