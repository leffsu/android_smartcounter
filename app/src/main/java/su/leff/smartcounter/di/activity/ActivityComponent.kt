package com.daily.lifehacks.app.di.activity

import com.daily.lifehacks.app.di.application.AppComponent
import dagger.Component
import su.leff.smartcounter.ui.MainActivity

@ActivityScope
@Component(dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}