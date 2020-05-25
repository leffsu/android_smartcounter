package su.leff.smartcounter

import org.junit.Test

import org.junit.Assert.*
import su.leff.smartcounter.util.TimeHelper
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun timehelperTest(){
        val test = TimeHelper.getStartEndOfTheDay(System.currentTimeMillis())
        val calendarStart = Calendar.getInstance().apply { timeInMillis = test.first }
        val calendarEnd = Calendar.getInstance().apply { timeInMillis = test.second }
        println(test)
    }
}
