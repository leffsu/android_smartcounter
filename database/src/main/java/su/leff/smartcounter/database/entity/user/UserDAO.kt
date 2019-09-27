package su.leff.smartcounter.database.entity.user

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): String?


    @Query("SELECT * FROM user")
    suspend fun fetchAllUsers(): List<User>


    @Query("SELECT * FROM user WHERE id =:userId")
    suspend fun getUser(userId: String): User


    @Transaction
    @Update
    suspend fun updateUser(user: User)

    @Transaction
    @Delete
    suspend fun deleteUser(user: User)
}