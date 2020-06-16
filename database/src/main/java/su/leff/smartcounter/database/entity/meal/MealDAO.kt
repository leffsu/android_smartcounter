package su.leff.smartcounter.database.entity.meal

import androidx.room.*

@Dao
interface MealDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertMeal(meal: MealEntity)

    @Query("SELECT * FROM meal_table")
     fun fetchAllMeal(): List<MealEntity>


    @Query("SELECT * FROM meal_table WHERE meal_timestamp > :timestampStart AND meal_timestamp < :timestampEnd")
     fun getAllMealsByDay(timestampStart: Long, timestampEnd: Long): List<MealEntity>

    @Query("SELECT * FROM meal_table WHERE meal_id =:mealId")
     fun getMeal(mealId: Int): MealEntity

    @Query("SELECT * FROM meal_table WHERE meal_timestamp =:timestamp")
     fun getMealByTimestamp(timestamp: Long): MealEntity


    @Transaction
    @Update
     fun updateMeal(meal: MealEntity)

    @Transaction
    @Delete
     fun deleteMeal(meal: MealEntity)
}