package su.leff.smartcounter.database.entity.food

import androidx.room.*

@Dao
public interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: FoodEntity): Int

    @Query("SELECT * FROM FOODTABLE")
    suspend fun fetchAllFood(): List<FoodEntity>

    @Query("SELECT * FROM FOODTABLE WHERE food_id =:foodId")
    suspend fun getFood(foodId: Int): FoodEntity

    @Query("SELECT * FROM FOODTABLE WHERE food_meal_id =:mealId")
    suspend fun getFoodByMeal(mealId: Int): List<FoodEntity>

    @Transaction
    @Update
    suspend fun updateFood(food: FoodEntity)

    @Transaction
    @Delete
    suspend fun deleteFood(food: FoodEntity)
}