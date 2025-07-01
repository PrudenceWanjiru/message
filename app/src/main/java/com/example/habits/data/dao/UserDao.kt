package com.example.habits.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habits.data.model.Person
import kotlinx.coroutines.flow.Flow




@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: Person)

    @Query("SELECT * FROM person WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByCredentials(email: String, password: String): Person?

    @Query("SELECT * FROM person")
    fun getAllUsers(): Flow<List<Person>>
}