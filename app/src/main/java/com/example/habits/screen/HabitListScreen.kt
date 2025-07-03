package com.example.habits.screen

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.habits.R
import com.example.habits.data.model.Habit
import com.example.habits.viewmodel.HabitViewModel
import com.example.habits.viewmodel.scheduleDailyReminder
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.text.isNotBlank



@Composable
fun HabitListScreen(viewModel: HabitViewModel) {
    val habits by viewModel.allHabits.collectAsState(initial = emptyList())

    var habitName by remember { mutableStateOf("") }
    var habitDate by remember { mutableStateOf("") }
    var habitDescription by remember { mutableStateOf("") }

//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF040228)))

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.habitlist), // your image name
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text("Add New Habit", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))



            OutlinedTextField(
                value = habitName,
                onValueChange = { habitName = it },
                label = { Text("Habit Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = habitDescription,
                onValueChange = { habitDescription = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = habitDate,
            onValueChange = { habitDate = it },
            label = { Text("Date (yyyy-MM-dd)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
//            DatePickerField(
//                label = "Start Date",
//                selectedDate = habitDate,
////                onDateSelected = { habitDate = it }
//            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (habitName.isNotBlank()) {
                        val currentDate =
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        val dateToSave = if (habitDate.isNotBlank()) habitDate else currentDate

                        val date =
                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                        viewModel.addHabit(
                            Habit(
                                name = habitName,
                                date = dateToSave,
                                description = habitDescription,
                            )
                        )
//                    scheduleDailyReminder(context, habitName)

                        habitName = ""
                        habitDate = ""
                        habitDescription = ""

                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Habit")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Your Habits", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(8.dp))

            val habits by viewModel.allHabits.collectAsState(initial = emptyList())


            LazyColumn {
                items(habits) { habit ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Name: ${habit.name}")
                            Text("Date: ${habit.date}")
                            Text("Description: ${habit.description}")

                        }
                    }
                }
            }
        }
    }
    @Composable
    fun DatePickerField(
        label: String,
        selectedDate: String,
        onDateSelected: (String) -> Unit
    ) {
        val context = LocalContext.current
        val calendar = Calendar.getInstance()

        val datePickerDialog = remember {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    val pickedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth)
                    onDateSelected(pickedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        }
    }
}