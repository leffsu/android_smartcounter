package com.daily.lifehacks.app.di.application

import android.app.Application
import dagger.Module
import dagger.Provides
import su.leff.smartcounter.database.AppDatabase
import su.leff.smartcounter.sharedpreferences.ISharedPreferencesAPI
import su.leff.smartcounter.sharedpreferences.SharedPreferencesImpl

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    internal fun application(): Application = application

    @Provides
    @AppScope
    internal fun shared(): ISharedPreferencesAPI = SharedPreferencesImpl(application)

    @Provides
    @AppScope
    internal fun database(): AppDatabase = AppDatabase.getInstance(application)

}