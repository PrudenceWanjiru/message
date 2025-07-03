package com.example.habits.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habits.data.model.Habit
import kotlinx.coroutines.flow.Flow


@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Delete
    suspend fun deleteHabit(habit: Habit)

    @Query("SELECT * FROM habits ORDER BY id DESC")
    fun getAllHabits():Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE date = :date")
    fun getHabitsByDate(date: String): Flow<List<Habit>>


}