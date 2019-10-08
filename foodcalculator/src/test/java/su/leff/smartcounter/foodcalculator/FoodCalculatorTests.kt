package su.leff.smartcounter.foodcalculator

import org.junit.Assert.*
import org.junit.Test

class FoodCalculatorTests {

    private val duck200apple200 = ResultPayload(900, 160, 160, 160)

    private val duck400apple200 = ResultPayload(1710, 240, 240, 240)

    private val errorMessage = "calculator is broken"

    @Test
    fun foodCalculator_CorrectResult1() {
        val list = ArrayList<FoodPayload>()
        list.add(FoodPayload(FoodType.APPLE, 200))
        list.add(FoodPayload(FoodType.DUCK, 200))
        assertEquals(errorMessage, duck200apple200, FoodCalculator.getCalories(list))
    }

    @Test
    fun foodCalculator_CorrectResult2() {
        val list = ArrayList<FoodPayload>()
        list.add(FoodPayload(FoodType.APPLE, 200))
        list.add(FoodPayload(FoodType.DUCK, 400))
        assertEquals(errorMessage, duck400apple200, FoodCalculator.getCalories(list))
    }
}