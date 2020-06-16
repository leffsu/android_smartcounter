package su.leff.smartcounter.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.food.Food
import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodType
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.database.entity.meal.MealRepository
import su.leff.smartcounter.util.TimeHelper
import su.leff.smartcounter.util.getMealName
import java.lang.Exception
import java.lang.StringBuilder


class MealViewModel(
    private val mealRepository: MealRepository,
    private val foodTypeRepository: FoodTypeRepository,
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val meals = MutableLiveData<List<Meal>>()

    val allMeals: LiveData<List<Meal>> = meals  // 1
    var activeMeal: MutableLiveData<Meal> = MutableLiveData<Meal>()

    fun allMeals() {
        meals.postValue(mealRepository.fetchAllMeal())   // 3
    }


    fun insertMeal(t: Meal) {
        mealRepository.insertMeal(t)
    }


    fun getMealByTimestamp(t: Long) {
        activeMeal.postValue(mealRepository.getMealByTimestamp(t))
    }


    fun updateMeal(t: Meal) {
        mealRepository.updateMeal(t)
    }


    fun deleteMeal(t: Meal) {
        mealRepository.deleteMeal(t)
    }


    fun getMeal(t: Int) {
        mealRepository.getMeal(t)
    }


    private val food = MediatorLiveData<List<Food>>()

    val allFood: MutableLiveData<List<Food>> = food  // 1

    val foodByMeal: MutableLiveData<List<Food>> = MutableLiveData()

    fun clearActiveMeal(){
        activeMeal.postValue(null)
        foodByMeal.postValue(ArrayList<Food>())
    }

    fun allFood() {
        food.postValue(foodRepository.fetchAllFood())   // 3
    }


    fun insertFood(t: Food) {
        foodRepository.insertFood(t)
        getFoodByMeal(t.mealId)
    }

    fun updateFood(t: Food) {
        foodRepository.updateFood(t)
    }


    fun deleteFood(t: Food) {
        foodRepository.deleteFood(t)
        getFoodByMeal(t.mealId)
    }

    fun getFood(t: Long) {
        foodRepository.getFood(t)
    }


    fun getFoodByDay(timestamp: Long) {
        foodRepository.fetchAllFood()
    }


    fun getFoodByMeal(mealId: Long) {
        val value = foodRepository.getFoodByMeal(mealId)
        foodByMeal.postValue(value)
    }


    private val foodTypes = MutableLiveData<List<FoodType>>()

    val allFoodTypes: LiveData<List<FoodType>> = foodTypes  // 1

    val foodTypesMap = HashMap<Long, FoodType>()

    fun allFoodTypes() {
        val foodType = foodTypeRepository.fetchAllFood()
        foodTypes.postValue(foodType)   // 3
        for (tempFoodType in foodType) {
            foodTypesMap[tempFoodType.id] = tempFoodType
        }
    }

    fun insertFoodType(t: FoodType) {
        foodTypeRepository.insertFood(t)
    }


    fun updateFoodType(t: FoodType) {
        foodTypeRepository.updateFood(t)
    }

    fun deleteFoodType(t: FoodType) {
        foodTypeRepository.deleteFood(t)
    }

    fun getFoodType(t: Long) {
        foodTypeRepository.getFood(t)
    }

    fun getMealOnDay(timestamp: Long): List<Meal> {
        allFoodTypes()
        val pair = TimeHelper.getStartEndOfTheDay(timestamp)
        val localMeals = mealRepository.fetchAllMealByDay(pair.first, pair.second)
        for (localMeal in localMeals) {
            localMeal.foods.addAll(foodRepository.getFoodByMeal(localMeal.id))
            val sb = StringBuilder()
            var calorieCounter = 0
            var cCounter = 0
            var pCounter = 0
            var fCounter = 0
            for (food in localMeal.foods) {
                val foodType = foodTypesMap[food.foodType] as FoodType
                sb.append(foodType.title).append(", ")
                foodType.fats
                cCounter += (foodType.carbos * (food.amount.toFloat() / 100.toFloat())).toInt()
                pCounter += (foodType.proteins * (food.amount.toFloat() / 100.toFloat())).toInt()
                fCounter += (foodType.fats * (food.amount.toFloat() / 100.toFloat())).toInt()
                calorieCounter += (foodType.calories * (food.amount.toFloat() / 100.toFloat())).toInt()
            }
            localMeal.title = getMealName(localMeal.mealType)
            localMeal.calories = calorieCounter.toLong()
            localMeal.carbo = cCounter
            localMeal.proteins = pCounter
            localMeal.fats = fCounter
            try {
                val description = sb.toString().substring(0)
                localMeal.description = description.substring(0, description.length - 2)
            } catch (e: Exception) {

            }
        }
        val list = ArrayList<Meal>()
        for (meal in localMeals) {
            if (meal.foods.isNotEmpty()) {
                list.add(meal)
            }
        }
        return list
    }
}