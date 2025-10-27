package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Main composable that sets up the app structure
                    FieldResearchApp()
                }
            }
        }
    }
}

@Composable
fun FieldResearchApp() {
    // This is the single source of truth for the list of tasks
    val tasks = remember { mutableStateListOf<String>() }

    // Set up the NavController for navigation between screens
    val navController = rememberNavController()

    // NavHost defines the navigation graph
    NavHost(navController = navController, startDestination = "taskList") {
        // Route for the TaskListScreen
        // Route for the TaskListScreen
        composable("taskList") {
            TaskListScreen(
                tasks = tasks,
                onNavigateToCreateTask = {
                    // Navigate to the create task screen when the FAB is clicked
                    navController.navigate("createTask")
                },
                onDeleteTask = { taskToDelete ->
                    // Remove the specified task from the shared list
                    tasks.remove(taskToDelete)
                }
            )
        }


        // Route for the CreateTaskScreen
        composable("createTask") {
            CreateTaskScreen(
                onSaveTask = { newTask ->
                    // Add the new task to the shared list
                    tasks.add(newTask)
                    // Navigate back to the task list
                    navController.popBackStack()
                },
                onNavigateBack = {
                    // Navigate back when the back arrow is clicked
                    navController.popBackStack()
                }
            )
        }
    }
}
