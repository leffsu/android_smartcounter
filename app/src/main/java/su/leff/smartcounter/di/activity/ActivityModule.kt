package su.leff.smartcounter.di.activity

import dagger.Module
import su.leff.smartcounter.ui.MainActivity

@Module
class ActivityModule(private val activity: MainActivity) {

//    @Provides
//    @ActivityScope
//    fun sharedPref(): SharedPref = SharedPrefImpl(activity)
}