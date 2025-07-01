package com.example.habits.repository

import com.example.habits.data.dao.UserDao
import com.example.habits.data.model.Person
import kotlinx.coroutines.flow.Flow


class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<Person>> = userDao.getAllUsers()

    suspend fun insertUser(user: Person) {
        userDao.insertUser(user)
    }

    suspend fun getUserByCredentials(email: String, password: String): Person? {
        return userDao.getUserByCredentials(email, password)
    }
}