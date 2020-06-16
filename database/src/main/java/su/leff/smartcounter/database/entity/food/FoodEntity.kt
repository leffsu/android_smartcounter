package su.leff.smartcounter.database.entity.food

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FOODTABLE")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "food_id") val id: Long,
    @NonNull @ColumnInfo(name = "food_meal_id") val mealId: Long,
    @NonNull @ColumnInfo(name = "food_type") val foodType: Long,
    @NonNull @ColumnInfo(name = "food_amount") val amount: Long
) {

    fun toFood(): Food {
        return Food(id, mealId, foodType, amount)
    }

    companion object {
        fun from(user: Food): FoodEntity {
            return FoodEntity(user.id, user.mealId, user.foodType, user.amount)
        }

        fun from(foods: List<Food>): List<FoodEntity> {
            val entities = ArrayList<FoodEntity>()

            for (food in foods) {
                entities.add(from(food))
            }

            return entities
        }

        fun toFood(entities: List<FoodEntity>): List<Food> {
            val food = ArrayList<Food>()

            for (foodentity in entities) {
                food.add(foodentity.toFood())
            }
            return food
        }
    }
}

data class Food(
    val id: Long,
    val mealId: Long,
    val foodType: Long,
    val amount: Long
)