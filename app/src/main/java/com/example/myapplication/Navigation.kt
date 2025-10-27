package com.example.myapplication

sealed class Screen(val route: String) {
    object TaskListScreen : Screen("task_list_screen")
    object CreateTaskScreen : Screen("create_task_screen")
}