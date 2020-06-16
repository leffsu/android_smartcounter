package su.leff.smartcounter.database.entity.foodtype

import androidx.room.*

@Dao
public interface FoodTypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertFoodType(food: FoodTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(food: List<FoodTypeEntity>)

    @Query("SELECT * FROM FOODTYPETABLE")
     fun fetchAllFoodTypes(): List<FoodTypeEntity>

    @Query("SELECT * FROM FOODTYPETABLE WHERE food_type_id =:foodTypeId")
     fun getFoodType(foodTypeId: Long): FoodTypeEntity

    @Transaction
    @Update
     fun updateFoodType(foodType: FoodTypeEntity)

    @Transaction
    @Delete
     fun deleteFoodType(foodType: FoodTypeEntity)
}