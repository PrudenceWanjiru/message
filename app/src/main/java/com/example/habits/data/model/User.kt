package com.example.habits.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String,
    val email: String,
    val password: String
)