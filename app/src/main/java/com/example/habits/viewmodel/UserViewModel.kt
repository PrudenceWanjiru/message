package com.example.habits.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habits.data.model.Person
import com.example.habits.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    // All users as Flow
    val allUsers: Flow<List<Person>> = repository.allUsers

    // StateFlow for the currently logged in user
    private val _loggedInUser = MutableStateFlow<Person?>(null)
    val loggedInUser: StateFlow<Person?> = _loggedInUser.asStateFlow()

    fun registerUser(user: Person) {
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }
//fun registerUser(user: Person, onResult: (Boolean) -> Unit) {
//    viewModelScope.launch {
//        try {
//            repository.insertUser(user)
//            onResult(true) // Registration successful
//        } catch (e: Exception) {
//            onResult(false) // Registration failed
//        }
//    }
//}
//}



    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByCredentials(email, password)
            _loggedInUser.value = user
        }
    }

    fun logout() {
        _loggedInUser.value = null
    }
}