package su.leff.smartcounter

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import su.leff.smartcounter.database.DatabaseAPIImpl
import su.leff.smartcounter.database.IDatabaseAPI
import su.leff.smartcounter.network.INetworkAPI
import su.leff.smartcounter.network.NetworkAPIImpl
import su.leff.smartcounter.orchestrator.IOrchestrator
import su.leff.smartcounter.orchestrator.OrchestratorImpl
import su.leff.smartcounter.sharedpreferences.ISharedPreferencesAPI
import su.leff.smartcounter.sharedpreferences.SharedPreferencesImpl

class MyApplication: Application(){

    val kodein = Kodein {
        bind<IDatabaseAPI>() with singleton { DatabaseAPIImpl() }
        bind<INetworkAPI>() with singleton { NetworkAPIImpl() }
        bind<IOrchestrator>() with singleton { OrchestratorImpl() }
        bind<ISharedPreferencesAPI>() with singleton { SharedPreferencesImpl() }
    }

    override fun onCreate() {
        super.onCreate()
    }

}