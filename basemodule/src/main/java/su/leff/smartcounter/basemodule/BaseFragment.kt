package su.leff.smartcounter.basemodule

import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner

@Suppress("LeakingThis")
abstract class BaseLifecycleFragment<T : AndroidViewModel> : Fragment(),
    LifecycleRegistryOwner {

    abstract val viewModelClass: Class<T>

    private val registry = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = registry
}