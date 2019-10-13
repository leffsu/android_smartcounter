package su.leff.smartcounter.database.entity.meal

import androidx.room.*

@Dao
interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealEntity): Int?


    @Query("SELECT * FROM meal_table")
    suspend fun fetchAllMeal(): List<MealEntity>


    @Query("SELECT * FROM meal_table WHERE meal_id =:mealId")
    suspend fun getMeal(mealId: Int): MealEntity


    @Transaction
    @Update
    suspend fun updateMeal(meal: MealEntity)

    @Transaction
    @Delete
    suspend fun deleteMeal(meal: MealEntity)
}