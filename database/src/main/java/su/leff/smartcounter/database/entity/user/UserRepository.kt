package su.leff.smartcounter.database.entity.user

import androidx.lifecycle.LiveData

class UserRepository(private val mDao: UserDAO) {

    suspend fun insertUser(user: User): String? {
        return mDao.insertUser(user)
    }

    suspend fun fetchAllUsers(): List<User> {
        return mDao.fetchAllUsers()
    }

    suspend fun getUser(userId: String): User {
        return mDao.getUser(userId)
    }

    suspend fun updateUser(user: User) {
        return mDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        return mDao.deleteUser(user)
    }
}