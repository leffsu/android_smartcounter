package su.leff.smartcounter.database.entity.food


class FoodRepository(private val mDao: FoodDAO) {

    suspend fun insertFood(food: Food): Int {
        return mDao.insertFood(food)
    }

    suspend fun fetchAllFood(): List<Food> {
        return mDao.fetchAllFood()
    }

    suspend fun getFood(foodId: Int): Food {
        return mDao.getFood(foodId)
    }

    suspend fun getFoodByMeal(mealId: Int): List<Food> {
        return mDao.getFoodByMeal(mealId)
    }

    suspend fun updateFood(food: Food) {
        return mDao.updateFood(food)
    }

    suspend fun deleteFood(food: Food) {
        return mDao.deleteFood(food)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: FoodRepository? = null

        fun getInstance(foodDAO: FoodDAO) =
            instance ?: synchronized(this) {
                instance ?: FoodRepository(foodDAO).also { instance = it }
            }
    }
}