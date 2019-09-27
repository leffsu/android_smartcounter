package su.leff.smartcounter.database.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val userId: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val status: Int,
    val waterADay: Long
    ) {

//    /**
//     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
//     * watering + watering Interval; false otherwise.
//     */
//    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
//        since > lastWateringDate.apply { add(DAY_OF_YEAR, wateringInterval) }
    override fun toString(): String{
        return "User(userId='$userId', name='$name', firstName='$firstName', lastName='$lastName', email='$email', status=$status, waterADay=$waterADay)"
    }
}