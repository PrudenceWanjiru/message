package com.example.habits.completion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HabitViewModel(
    private val habitCompletionDao: HabitCompletionDao
) : ViewModel() {

    fun getCompletedDatesForHabit(habitId: Int): Flow<List<String>> {
        return habitCompletionDao.getCompletedDates(habitId)
    }

    fun markHabitAsDone(habitId: Int) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        viewModelScope.launch {
            habitCompletionDao.markHabitCompleted(
                HabitCompletion(habitId = habitId, date = today)
            )
        }
    }
}