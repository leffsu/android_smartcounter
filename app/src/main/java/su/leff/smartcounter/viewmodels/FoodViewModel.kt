package su.leff.smartcounter.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.food.FoodRepository


class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    private val food = MediatorLiveData<List<Food>>()

    val allFood: MutableLiveData<List<Food>> = food  // 1

    fun allFood() = viewModelScope.launch {
        food.postValue(foodRepository.fetchAllFood())   // 3
    }

    fun insertFood(t: Food) = viewModelScope.launch {
        foodRepository.insertFood(t)
    }

    fun updateFood(t: Food) = viewModelScope.launch {
        foodRepository.updateFood(t)
    }

    fun deleteFood(t: Food) = viewModelScope.launch {
        foodRepository.deleteFood(t)
    }

    fun getFood(t: Long) = viewModelScope.launch {
        foodRepository.getFood(t)
    }

    fun getFoodByDay(timestamp: Long) = viewModelScope.launch {
        foodRepository.fetchAllFood()
    }

    fun getFoodByMeal(mealId: Long) = viewModelScope.launch {
        foodRepository.getFoodByMeal(mealId)
    }
}