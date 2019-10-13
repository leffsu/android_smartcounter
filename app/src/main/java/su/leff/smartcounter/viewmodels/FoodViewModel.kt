package su.leff.smartcounter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.food.FoodEntity
import su.leff.smartcounter.database.entity.food.FoodRepository


class FoodViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    private val food = MediatorLiveData<List<FoodEntity>>()

    val allFood: LiveData<List<FoodEntity>> = food  // 1

    fun allFood() = viewModelScope.launch {
        food.postValue(foodRepository.fetchAllFood())   // 3
    }

    fun insertFood(t: FoodEntity) = viewModelScope.launch {
        foodRepository.insertFood(t)
    }

    fun updateFood(t: FoodEntity) = viewModelScope.launch {
        foodRepository.updateFood(t)
    }

    fun deleteFood(t: FoodEntity) = viewModelScope.launch {
        foodRepository.deleteFood(t)
    }

    fun getFood(t: Int) = viewModelScope.launch {
        foodRepository.getFood(t)
    }
}