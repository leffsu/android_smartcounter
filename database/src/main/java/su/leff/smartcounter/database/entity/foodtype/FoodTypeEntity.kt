package su.leff.smartcounter.database.entity.foodtype

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FOODTYPETABLE")
data class FoodTypeEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "food_type_id") val id: Long,
    @NonNull @ColumnInfo(name = "food_type_title") val title: String,
    @NonNull @ColumnInfo(name = "food_type_calories") val calories: Long
) {

    fun toFoodType(): FoodType {
        return FoodType(id, title, calories)
    }

    companion object {
        fun from(foodType: FoodType): FoodTypeEntity {
            return FoodTypeEntity(foodType.id, foodType.title, foodType.calories)
        }

        fun from(foods: List<FoodType>): List<FoodTypeEntity> {
            val entities = ArrayList<FoodTypeEntity>()

            for (food in foods) {
                entities.add(from(food))
            }

            return entities
        }

        fun toFoodType(entities: List<FoodTypeEntity>): List<FoodType> {
            val food = ArrayList<FoodType>()

            for (foodentity in entities) {
                food.add(foodentity.toFoodType())
            }
            return food
        }
    }
}

data class FoodType(
    val id: Long,
    val title: String,
    val calories: Long
)