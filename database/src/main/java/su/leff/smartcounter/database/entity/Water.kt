package su.leff.smartcounter.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water")
data class Water(
    @PrimaryKey @ColumnInfo(name = "id") val waterId: String,
    val userId: String,
    val amount: Long
) {

//    /**
//     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
//     * watering + watering Interval; false otherwise.
//     */
//    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
//        since > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }


}