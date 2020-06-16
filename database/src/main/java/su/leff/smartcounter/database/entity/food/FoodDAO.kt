package su.leff.smartcounter.database.entity.food

import androidx.room.*

@Dao
public interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(food: FoodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(food: List<FoodEntity>)

    @Query("SELECT * FROM FOODTABLE")
    fun fetchAllFood(): List<FoodEntity>

    @Query("SELECT * FROM FOODTABLE WHERE food_id =:foodId")
    fun getFood(foodId: Long): FoodEntity

    @Query("SELECT * FROM FOODTABLE WHERE food_meal_id =:mealId")
    fun getFoodByMeal(mealId: Long): List<FoodEntity>

    @Transaction
    @Update
    fun updateFood(food: FoodEntity)

    @Transaction
    @Delete
    fun deleteFood(food: FoodEntity)
}