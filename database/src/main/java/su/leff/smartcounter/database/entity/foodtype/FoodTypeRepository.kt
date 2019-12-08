package su.leff.smartcounter.database.entity.foodtype


class FoodTypeRepository(private val mDao: FoodTypeDAO) {

    suspend fun insertFood(food: FoodType) {
        mDao.insertFoodType(FoodTypeEntity.from(food))
    }

    suspend fun fetchAllFood(): List<FoodType> {
        return FoodTypeEntity.toFoodType(mDao.fetchAllFoodTypes())
    }

    suspend fun getFood(foodId: Long): FoodType {
        return mDao.getFoodType(foodId).toFoodType()
    }

    suspend fun updateFood(food: FoodType) {
        return mDao.updateFoodType(FoodTypeEntity.from(food))
    }

    suspend fun deleteFood(food: FoodType) {
        return mDao.deleteFoodType(FoodTypeEntity.from(food))
    }
}