package su.leff.smartcounter.util

import java.util.*

object TimeHelper {

    fun getStartEndOfTheDay(timestamp: Long): Pair<Long, Long> {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        calendar.set(
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
            0, 0, 0
        )
        val startMillis = calendar.timeInMillis
        calendar.set(
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
            23, 59, 59
        )
        val endMillis = calendar.timeInMillis
        return Pair(startMillis, endMillis)
    }

}