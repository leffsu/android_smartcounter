package su.leff.smartcounter.database.entity.meal

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_table")
data class MealEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "meal_id") val id: Long,
    @ColumnInfo(name = "meal_timestamp") @NonNull val timestamp: Long
) {
    fun toMeal(): Meal {
        return Meal(id, timestamp)
    }

    companion object {
        fun from(user: Meal): MealEntity {
            return MealEntity(user.id, user.timestamp)
        }

        fun from(meals: List<Meal>): List<MealEntity> {
            val entities = ArrayList<MealEntity>()

            for (meal in meals) {
                entities.add(from(meal))
            }

            return entities
        }


        fun toMeal(mealEntity: MealEntity): Meal {
            return Meal(mealEntity.id, mealEntity.timestamp)
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
    var title: String,
    var description: String,
    var calories: Long
) {
    constructor(id: Long, timestamp: Long) : this(id, timestamp, "", "", 0L)
}