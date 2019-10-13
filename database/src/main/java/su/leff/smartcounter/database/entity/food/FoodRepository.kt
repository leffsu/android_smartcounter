package su.leff.smartcounter.database.entity.food


class FoodRepository(private val mDao: FoodDAO) {

    // todo https://jacquessmuts.github.io/post/modularization_room/

    suspend fun insertFood(food: FoodEntity): Int {
        return mDao.insertFood(food)
    }

    suspend fun fetchAllFood(): List<FoodEntity> {
        return mDao.fetchAllFood()
    }

    suspend fun getFood(foodId: Int): FoodEntity {
        return mDao.getFood(foodId)
    }

    suspend fun getFoodByMeal(mealId: Int): List<FoodEntity> {
        return mDao.getFoodByMeal(mealId)
    }

    suspend fun updateFood(food: FoodEntity) {
        return mDao.updateFood(food)
    }

    suspend fun deleteFood(food: FoodEntity) {
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