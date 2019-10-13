package su.leff.smartcounter.database.entity.meal

import androidx.room.*

@Dao
interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: Meal): Int?


    @Query("SELECT * FROM user")
    suspend fun fetchAllMeal(): List<Meal>


    @Query("SELECT * FROM user WHERE id =:mealId")
    suspend fun getMeal(mealId: Int): Meal


    @Transaction
    @Update
    suspend fun updateMeal(meal: Meal)

    @Transaction
    @Delete
    suspend fun deleteMeal(meal: Meal)
}