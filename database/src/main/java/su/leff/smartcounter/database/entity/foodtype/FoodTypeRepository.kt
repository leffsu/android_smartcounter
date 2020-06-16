package su.leff.smartcounter.database.entity.foodtype


class FoodTypeRepository(private val mDao: FoodTypeDAO) {

    fun insertFood(food: FoodType) {
        mDao.insertFoodType(FoodTypeEntity.from(food))
    }

    fun fetchAllFood(): List<FoodType> {
        return FoodTypeEntity.toFoodType(mDao.fetchAllFoodTypes())
    }

    fun getFood(foodId: Long): FoodType {
        return mDao.getFoodType(foodId).toFoodType()
    }

     fun updateFood(food: FoodType) {
        return mDao.updateFoodType(FoodTypeEntity.from(food))
    }

     fun deleteFood(food: FoodType) {
        return mDao.deleteFoodType(FoodTypeEntity.from(food))
    }
}