package su.leff.smartcounter.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodType
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.database.entity.meal.MealRepository


class MealViewModel(
    private val mealRepository: MealRepository,
    private val foodTypeRepository: FoodTypeRepository,
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val meals = MutableLiveData<List<Meal>>()

    val allMeals: LiveData<List<Meal>> = meals  // 1

    fun allMeals() = viewModelScope.launch {
        meals.postValue(mealRepository.fetchAllMeal())   // 3
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

    fun getMealsByDay(timestamp: Long) {

    }


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


    private val foodTypes = MutableLiveData<List<FoodType>>()

    val allFoodTypes: LiveData<List<FoodType>> = foodTypes  // 1

    fun allFoodTypes() = viewModelScope.launch {
        foodTypes.postValue(foodTypeRepository.fetchAllFood())   // 3
    }

    fun insertFoodType(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.insertFood(t)
    }

    fun updateFoodType(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.updateFood(t)
    }

    fun deleteFoodType(t: FoodType) = viewModelScope.launch {
        foodTypeRepository.deleteFood(t)
    }

    fun getFoodType(t: Long) = viewModelScope.launch {
        foodTypeRepository.getFood(t)
    }


    fun getMealOnDay(timestamp: Long): List<Meal> {
        val localMeals = allMeals.value!!
        for (food in allFood.value!!.toList()) {
            for (meal in localMeals) {
                if (food.mealId == meal.id) {
                    meal.foods.add(food)
                }
            }
        }
        return localMeals
    }
}