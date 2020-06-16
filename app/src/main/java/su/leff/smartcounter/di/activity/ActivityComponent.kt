package su.leff.smartcounter.di.activity

import su.leff.smartcounter.di.application.AppComponent
import dagger.Component
import su.leff.smartcounter.di.activity.ActivityModule
import su.leff.smartcounter.di.activity.ActivityScope
import su.leff.smartcounter.ui.MainActivity

@ActivityScope
@Component(dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun inject(activity: MainActivity)
}