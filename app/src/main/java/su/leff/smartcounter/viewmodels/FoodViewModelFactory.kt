package su.leff.smartcounter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import su.leff.smartcounter.database.entity.food.FoodRepository

class FoodViewModelFactory(
    private val repository: FoodRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FoodViewModel(repository) as T
}

