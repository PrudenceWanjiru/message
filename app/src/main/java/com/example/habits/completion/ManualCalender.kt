package com.example.habits.completion

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getDaysInMonth(month: Int, year: Int): List<LocalDate> {
    val firstDay = LocalDate.of(year, month, 1)
    val length = firstDay.lengthOfMonth()
    return (1..length).map { day ->
        LocalDate.of(year, month, day)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ManualCalendar(
    completedDates: List<String>,
    month: Int = LocalDate.now().monthValue,
    year: Int = LocalDate.now().year
) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val completedSet = completedDates.map { LocalDate.parse(it, formatter) }.toSet()
    val days = getDaysInMonth(month, year)

    Column {
        Text(
            text = "${Month.of(month).name.lowercase().replaceFirstChar { it.uppercase() }} $year",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(days) { date ->
                val isCompleted = date in completedSet
                val backgroundColor = if (isCompleted) Color(0xFF81C784) else Color(0xFF424242)

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp)
                        .background(backgroundColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
