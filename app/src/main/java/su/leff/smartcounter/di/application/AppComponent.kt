package su.leff.smartcounter.di.application

import dagger.Component
import su.leff.smartcounter.MyApplication
import su.leff.smartcounter.di.application.AppModule

@Component(
    modules = [AppModule::class]
)
interface AppComponent {
    fun inject(application: MyApplication)
}