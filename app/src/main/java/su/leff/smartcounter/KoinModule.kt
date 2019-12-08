package su.leff.smartcounter

import android.content.Context
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import su.leff.smartcounter.database.AppDatabase
import su.leff.smartcounter.database.entity.food.FoodRepository
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository
import su.leff.smartcounter.database.entity.meal.MealRepository
import su.leff.smartcounter.network.INetworkAPI
import su.leff.smartcounter.network.NetworkAPIImpl
import su.leff.smartcounter.orchestrator.IOrchestrator
import su.leff.smartcounter.orchestrator.OrchestratorImpl
import su.leff.smartcounter.sharedpreferences.ISharedPreferencesAPI
import su.leff.smartcounter.sharedpreferences.SharedPreferencesImpl
import su.leff.smartcounter.viewmodels.*

val appModule = applicationContext {

    bean { NetworkAPIImpl() as INetworkAPI }
    bean { OrchestratorImpl() as IOrchestrator }
    bean { SharedPreferencesImpl() as ISharedPreferencesAPI }

    viewModel { FoodViewModel(get()) }
    viewModel { MealViewModel(get()) }
    viewModel { FoodTypeViewModel(get()) }

    bean { FoodRepository(AppDatabase.getInstance(context = MyApplication.getContext() as Context).foodDAO()) }
    bean { FoodViewModelFactory(get())}

    bean { MealRepository(AppDatabase.getInstance(context = MyApplication.getContext() as Context).mealDAO(), get(), get()) }
    bean { MealViewModelFactory(get())}

    bean { FoodTypeRepository(AppDatabase.getInstance(context = MyApplication.getContext() as Context).foodTypeDAO()) }
    bean { FoodTypeViewModelFactory(get())}

}

val modules = listOf(appModule)