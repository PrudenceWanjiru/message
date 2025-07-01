package com.example.habits.repository

import com.example.habits.data.dao.HabitDao
import com.example.habits.data.model.Habit
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {

    // All habits as LiveData
    val allHabits: Flow<List<Habit>> = habitDao.getAllHabits()

    // Get habits by date (e.g., today)
    fun getHabitsByDate(date: String): Flow<List<Habit>> {
        return habitDao.getHabitsByDate(date)
    }

    suspend fun insertHabit(habit: Habit) {
        habitDao.insertHabit(habit)
    }

    suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit)
    }

    suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(habit)
    }
}