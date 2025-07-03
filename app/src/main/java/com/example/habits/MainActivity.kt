package com.example.habits

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habits.data.AppDatabase
import com.example.habits.repository.HabitRepository
import com.example.habits.repository.UserRepository
import com.example.habits.screen.HabitDetailScreen
import com.example.habits.screen.HabitListScreen
import com.example.habits.screen.LoginScreen
import com.example.habits.screen.RegisterScreen
import com.example.habits.screen.WelcomeScreen
import com.example.habits.ui.theme.HabitsTheme
import com.example.habits.viewmodel.HabitViewModel
import com.example.habits.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up Room database and repositories
        val db = AppDatabase.getDatabase(applicationContext)
        val habitRepo = HabitRepository(db.habitDao())
        val userRepo = UserRepository(db.userDao())

        // ViewModels
        val habitViewModel = HabitViewModel(habitRepo)
        val userViewModel = UserViewModel(userRepo)

        setContent {
            HabitsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost(userViewModel, habitViewModel)
                }
            }
        }
    }
}


@Composable
fun AppNavHost(userViewModel: UserViewModel, habitViewModel: HabitViewModel) {
    val navController = rememberNavController()

    val loggedInUser by userViewModel.loggedInUser.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (loggedInUser == null) "welcome" else "habits"
    )

    {
        composable("welcome") {
            WelcomeScreen(navController)
        }

        composable("login") {
            LoginScreen(
                viewModel = userViewModel,
                onLoginSuccess = { navController.navigate("habits") },
                onGoToRegister = {navController.navigate("register")}


            )
        }

        composable("register") {
            RegisterScreen(
                viewModel = userViewModel,
                onRegisterSuccess = { navController.navigate("login") }
            )
        }

        composable("habits") {
            HabitListScreen(viewModel = habitViewModel)
        }
       
    }
}

