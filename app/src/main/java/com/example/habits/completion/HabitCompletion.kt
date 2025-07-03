package com.example.habits.completion

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HabitCompletion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val habitId: Int,
    val date: String // format: "yyyy-MM-dd"
)