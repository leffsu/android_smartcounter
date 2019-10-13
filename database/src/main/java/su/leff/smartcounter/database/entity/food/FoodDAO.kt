package su.leff.smartcounter.database.entity.food

import androidx.room.*
import su.leff.smartcounter.database.entity.meal.Meal
import su.leff.smartcounter.database.entity.user.User

@Dao
interface FoodDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food): Int

    @Query("SELECT * FROM meal")
    suspend fun fetchAllFood(): List<Food>

    @Query("SELECT * FROM food WHERE id =:foodId")
    suspend fun getFood(foodId: Int): Food

    @Query("SELECT * FROM food WHERE mealId =:mealId")
    suspend fun getFoodByMeal(mealId: Int): List<Food>

    @Transaction
    @Update
    suspend fun updateFood(food: Food)

    @Transaction
    @Delete
    suspend fun deleteFood(food: Food)
}