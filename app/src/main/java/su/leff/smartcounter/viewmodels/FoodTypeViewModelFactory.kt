package su.leff.smartcounter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import su.leff.smartcounter.database.entity.foodtype.FoodTypeRepository

class FoodTypeViewModelFactory(
    private val repository: FoodTypeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FoodTypeViewModel(repository) as T
}