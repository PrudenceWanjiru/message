package com.example.habits.completion

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitCompletionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun markHabitCompleted(completion: HabitCompletion)

    @Query("SELECT date FROM HabitCompletion WHERE habitId = :habitId")
    fun getCompletedDates(habitId: Int): Flow<List<String>>
}