package su.leff.smartcounter.util

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry
import su.leff.smartcounter.MyApplication
import su.leff.smartcounter.sharedpreferences.ISharedPreferencesAPI
import su.leff.smartcounter.viewmodels.MealViewModel

@Suppress("LeakingThis")
abstract class BaseFragment : Fragment() {

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry

    lateinit var app: MyApplication

    lateinit var shared: ISharedPreferencesAPI

    lateinit var mealViewModel: MealViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        app = requireActivity().application as MyApplication
        shared = app.shared
        mealViewModel = app.mealViewModel
        super.onViewCreated(view, savedInstanceState)
    }
}