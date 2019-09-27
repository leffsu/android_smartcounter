package su.leff.smartcounter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import su.leff.smartcounter.database.entity.user.User
import su.leff.smartcounter.database.entity.user.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val users = MediatorLiveData<List<User>>()

    val tasks : LiveData<List<User>> = users  // 1

    fun allTasks() = viewModelScope.launch {  // 2
        users.postValue(userRepository.fetchAllUsers())   // 3
    }

    fun insertUser(t : User) = viewModelScope.launch {
        userRepository.insertUser(t)
    }

    fun updateUser(t: User) = viewModelScope.launch {
        userRepository.updateUser(t)
    }

    fun deleteUser(t: User) = viewModelScope.launch {
        userRepository.deleteUser(t)
    }

    fun getUser(t: String) = viewModelScope.launch {
        userRepository.getUser(t)
    }
}