package com.daily.lifehacks.app.di.application

import dagger.Component
import su.leff.smartcounter.MyApplication
import javax.inject.Singleton

@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: MyApplication)
}