package su.leff.smartcounter.database.entity.meal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal")
data class Meal(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    val timestamp: Long
)