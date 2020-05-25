package su.leff.smartcounter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.daily.lifehacks.app.di.application.AppComponent
import com.daily.lifehacks.app.di.application.AppModule
import com.daily.lifehacks.app.di.application.DaggerAppComponent
import su.leff.smartcounter.database.AppDatabase
import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import su.leff.smartcounter.database.entity.meal.MealRepository
import su.leff.smartcounter.sharedpreferences.ISharedPreferencesAPI
import su.leff.smartcounter.viewmodels.MealViewModel
import javax.inject.Inject

//import su.leff.smartcounter.di.application.AppComponent
//import su.leff.smartcounter.di.application.AppModule


class MyApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    lateinit var mealViewModel: MealViewModel

    @Inject
    lateinit var shared: ISharedPreferencesAPI

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        savedContext = this

        mealViewModel = MealViewModel(
            MealRepository(database.mealDAO()),
            FoodTypeRepository(database.foodTypeDAO()),
            FoodRepository(database.foodDAO())
        )
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var savedContext: Context? = null

        fun getContext(): Context? {
            return savedContext
        }
    }
}