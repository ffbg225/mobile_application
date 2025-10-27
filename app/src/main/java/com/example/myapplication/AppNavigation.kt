package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController

@Composable
fun AppNavigation(
    navController: NavHostController,
    tasks: MutableList<String>
) {
    NavHost(
        navController = navController,
        startDestination = Screen.TaskListScreen.route
    ) {
        // Define the TaskListScreen composable
        composable(route = Screen.TaskListScreen.route) {
            TaskListScreen(
                tasks = tasks,
                onNavigateToCreateTask = {
                    // Navigate to CreateTaskScreen when FAB is clicked
                    navController.navigate(Screen.CreateTaskScreen.route)
                },
                // Add the missing onDeleteTask parameter here
                onDeleteTask = { taskToDelete ->
                    tasks.remove(taskToDelete)
                }
            )
        }

        // Define the CreateTaskScreen composable
        composable(route = Screen.CreateTaskScreen.route) {
            CreateTaskScreen(
                onSaveTask = { newTask ->
                    // Add new task to the list and navigate back
                    tasks.add(newTask)
                    navController.popBackStack()
                },
                onNavigateBack = {
                    // Navigate back when back arrow is clicked
                    navController.popBackStack()
                }
            )
        }
    }
}
