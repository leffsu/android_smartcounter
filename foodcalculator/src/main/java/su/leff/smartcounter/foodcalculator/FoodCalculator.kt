package su.leff.smartcounter.foodcalculator

object FoodCalculator {

    fun getCalories(payloadList: ArrayList<FoodPayload>): ResultPayload {
        var calories = 0
        var carbo = 0
        var fat = 0
        var protein = 0
        for (payload in payloadList) {
            calories += (payload.foodType.calories * (payload.amount / 100).toFloat()).toInt()
            carbo += (payload.foodType.carbo * (payload.amount / 100).toFloat()).toInt()
            fat += (payload.foodType.fat * (payload.amount / 100).toFloat()).toInt()
            protein += (payload.foodType.protein * (payload.amount / 100).toFloat()).toInt()
        }
        return ResultPayload(calories, fat, carbo, protein)
    }
}