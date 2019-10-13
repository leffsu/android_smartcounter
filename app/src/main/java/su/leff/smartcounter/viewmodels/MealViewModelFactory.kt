package su.leff.smartcounter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import su.leff.smartcounter.database.entity.meal.MealRepository

class MealViewModelFactory(
    private val repository: MealRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MealViewModel(repository) as T
}