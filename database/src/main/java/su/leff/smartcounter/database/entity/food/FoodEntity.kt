package su.leff.smartcounter.database.entity.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val mealId: Int,
    val foodId: Int,
    val amount: Int
)

data class Food(
    val id: Int,
    val mealId: Int,
    val foodId: Int,
    val amount: Int
) {
    companion object {
        fun from(user: Food): FoodEntity {
            return FoodEntity(user.id, user.mealId, user.foodId, user.amount)
        }
    }

    fun toUser(): Food {
        return Food(id, mealId, foodId, amount)
    }
}