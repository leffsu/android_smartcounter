package su.leff.smartcounter.database.entity.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val mealId: Int,
    val foodId: Int,
    val amount: Int
)