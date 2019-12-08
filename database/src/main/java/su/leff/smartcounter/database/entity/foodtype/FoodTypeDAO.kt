package su.leff.smartcounter.database.entity.foodtype

import androidx.room.*

@Dao
public interface FoodTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodType(food: FoodTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(food: List<FoodTypeEntity>)

    @Query("SELECT * FROM FOODTYPETABLE")
    suspend fun fetchAllFoodTypes(): List<FoodTypeEntity>

    @Query("SELECT * FROM FOODTYPETABLE WHERE food_type_id =:foodTypeId")
    suspend fun getFoodType(foodTypeId: Long): FoodTypeEntity

    @Transaction
    @Update
    suspend fun updateFoodType(foodType: FoodTypeEntity)

    @Transaction
    @Delete
    suspend fun deleteFoodType(foodType: FoodTypeEntity)
}