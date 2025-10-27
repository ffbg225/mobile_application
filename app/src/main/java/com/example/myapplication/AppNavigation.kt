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

        composable(route = Screen.TaskListScreen.route) {
            TaskListScreen(
                tasks = tasks,
                onNavigateToCreateTask = {

                    navController.navigate(Screen.CreateTaskScreen.route)
                },

                onDeleteTask = { taskToDelete ->
                    tasks.remove(taskToDelete)
                }
            )
        }


        composable(route = Screen.CreateTaskScreen.route) {
            CreateTaskScreen(
                onSaveTask = { newTask ->

                    tasks.add(newTask)
                    navController.popBackStack()
                },
                onNavigateBack = {

                    navController.popBackStack()
                }
            )
        }
    }
}
