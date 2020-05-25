package su.leff.smartcounter.database.entity.meal

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import su.leff.smartcounter.database.entity.food.Food

@Entity(tableName = "meal_table")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "meal_id") val id: Long,
    @ColumnInfo(name = "meal_timestamp") @NonNull val timestamp: Long,
    @ColumnInfo(name = "meal_type") @NonNull val mealType: Long
) {
    fun toMeal(): Meal {
        return Meal(id, timestamp, mealType)
    }

    companion object {
        fun from(user: Meal): MealEntity {
            return MealEntity(0L, user.timestamp, user.mealType)
        }

        fun from(meals: List<Meal>): List<MealEntity> {
            val entities = ArrayList<MealEntity>()

            for (meal in meals) {
                entities.add(from(meal))
            }

            return entities
        }


        fun toMeal(mealEntity: MealEntity): Meal {
            return Meal(mealEntity.id, mealEntity.timestamp, mealEntity.mealType)
        }

        fun toMeal(entities: List<MealEntity>): List<Meal> {
            val meal = ArrayList<Meal>()

            for (mealentity in entities) {
                meal.add(mealentity.toMeal())
            }
            return meal
        }
    }
}

data class Meal(
    val id: Long,
    val timestamp: Long,
    var mealType: Long,
    var title: String,
    var description: String,
    var calories: Long,
    val foods: ArrayList<Food> = ArrayList()
) {
    constructor(id: Long, timestamp: Long, mealType: Long) : this(
        id,
        timestamp,
        mealType,
        "",
        "",
        0L
    )
}