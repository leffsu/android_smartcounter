package su.leff.smartcounter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.android.startKoin


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        savedContext = this
        startKoin(this, modules)
        if (BuildConfig.DEBUG) {
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog = debugDB.getMethod("getAddressLog")
                val value = getAddressLog.invoke(null)
                println("DEBUG DB " + value.toString())
            } catch (ignore: Exception) {

            }
        }
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var savedContext: Context? = null

        fun getContext(): Context? {
            return savedContext
        }
    }
}