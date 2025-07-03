package com.example.habits.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habits.completion.HabitViewModel
import com.example.habits.completion.ManualCalendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitDetailScreen(habitId: Int, viewModel: HabitViewModel) {
    val completedDates by viewModel.getCompletedDatesForHabit(habitId).collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF040228))
            .padding(16.dp)
    ) {
        Text("Habit Progress", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        ManualCalendar(completedDates)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.markHabitAsDone(habitId) }) {
            Text("Mark Today as Done")
        }
    }
}
