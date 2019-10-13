package su.leff.smartcounter.database.entity.food

import androidx.lifecycle.Transformations


class FoodRepository(private val mDao: FoodDAO) {

    // todo https://jacquessmuts.github.io/post/modularization_room/

    suspend fun insertFood(food: Food): Int {
        return mDao.insertFood(FoodEntity.from(food))
    }

    suspend fun fetchAllFood(): List<Food> {
        return FoodEntity.toFood(mDao.fetchAllFood())
    }

    suspend fun getFood(foodId: Int): Food {
        return mDao.getFood(foodId).toFood()
    }

    suspend fun getFoodByMeal(mealId: Int): List<Food> {
        return FoodEntity.toFood(mDao.getFoodByMeal(mealId))
    }

    suspend fun updateFood(food: Food) {
        return mDao.updateFood(FoodEntity.from(food))
    }

    suspend fun deleteFood(food: Food) {
        return mDao.deleteFood(FoodEntity.from(food))
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(foodDAO: FoodDAO) =
            instance ?: synchronized(this) {
                instance ?: FoodRepository(foodDAO).also { instance = it }
            }
    }
}