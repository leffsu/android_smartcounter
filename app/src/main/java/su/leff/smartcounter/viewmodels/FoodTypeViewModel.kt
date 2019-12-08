package su.leff.smartcounter.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.foodtype.FoodType
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository


class FoodTypeViewModel(private val foodTypeRepository: FoodTypeRepository) : ViewModel() {

    private val meals = MutableLiveData<List<FoodType>>()

    val allMeals: LiveData<List<FoodType>> = meals  // 1

    fun allTasks() = viewModelScope.launch {
        meals.postValue(foodTypeRepository.fetchAllFood())   // 3
    }

    fun insertMeal(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.insertFood(t)
    }

    fun updateMeal(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.updateFood(t)
    }

    fun deleteMeal(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.deleteFood(t)
    }

    fun getMeal(t: Long) = viewModelScope.launch {
        foodTypeRepository.getFood(t)
    }
}