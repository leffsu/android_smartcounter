package su.leff.smartcounter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import su.leff.smartcounter.database.Constant.DATABASE_NAME
import su.leff.smartcounter.database.entity.food.FoodEntity
import su.leff.smartcounter.database.entity.food.FoodDAO
import su.leff.smartcounter.database.entity.meal.MealEntity
import su.leff.smartcounter.database.entity.meal.MealDAO
import su.leff.smartcounter.database.entity.foodtype.FoodTypeDAO
import su.leff.smartcounter.database.entity.foodtype.FoodTypeEntity
import su.leff.smartcounter.database.entity.foodtype.population
import java.util.concurrent.Executors


@Database(
    entities = [FoodEntity::class, MealEntity::class, FoodTypeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDAO(): FoodDAO
    abstract fun mealDAO(): MealDAO
    abstract fun foodTypeDAO(): FoodTypeDAO

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Executors.newSingleThreadScheduledExecutor().execute(Runnable {
                            getInstance(context).foodTypeDAO().insertAll(population)
                        })
                    }
                })
                .build()
        }
    }
}