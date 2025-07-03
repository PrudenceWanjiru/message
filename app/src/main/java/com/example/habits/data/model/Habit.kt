package com.example.habits.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String,
    val description: String,
    val isCompleted: Boolean = false,
    val date: String // store as yyyy-MM-dd
)