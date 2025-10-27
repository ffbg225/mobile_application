package com.example.myapplication

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    tasks: List<String>,
    onNavigateToCreateTask: () -> Unit,
    onDeleteTask: (String) -> Unit
) {
    // Background gradient
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE8F5E9),
            Color(0xFFE0F7FA),
            Color.White
        )
    )

    // Gradient for AppBar + FAB
    val appBarGradient = Brush.horizontalGradient(
        listOf(Color(0xFF00BCD4), Color(0xFF4CAF50))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Field Research Checklist",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            letterSpacing = 0.5.sp
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    modifier = Modifier.background(appBarGradient)
                )
            },
            floatingActionButton = {
                GradientFAB(
                    gradient = appBarGradient,
                    onClick = onNavigateToCreateTask
                )
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            if (tasks.isEmpty()) {
                EmptyState(innerPadding)
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(tasks) { task ->
                        TaskListItem(
                            taskText = task,
                            onDeleteTask = { onDeleteTask(task) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GradientFAB(
    gradient: Brush,
    onClick: () -> Unit
) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (pressed) 1.1f else 1f,
        animationSpec = tween(150)
    )

    Box(
        modifier = Modifier
            .size((60 * scale).dp)
            .shadow(12.dp, CircleShape)
            .background(gradient, CircleShape)
            .clickable {
                pressed = true
                onClick()
                pressed = false
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Task",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}


@Composable
fun EmptyState(innerPadding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.task),
            contentDescription = "No tasks",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "No Tasks Yet",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.4.sp
            )
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Tap the '+' button to begin adding your research activities.",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color(0xFF546E7A)
        )
    }
}


@Composable
fun TaskListItem(
    taskText: String,
    onDeleteTask: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    val animatedColor by animateColorAsState(
        targetValue = if (isPressed) Color(0xFFDCEDC8) else Color.White,
        animationSpec = tween(300)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = animatedColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = taskText,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1B5E20),
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                isPressed = true
                onDeleteTask()
                isPressed = false
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task",
                    tint = Color(0xFFD32F2F)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Gradient FAB - Task List")
@Composable
fun TaskListScreenPreview() {
    MyApplicationTheme {
        TaskListScreen(
            tasks = listOf("Collect soil sample", "Measure temperature", "Record observations"),
            onNavigateToCreateTask = {},
            onDeleteTask = {}
        )
    }
}

@Preview(showBackground = true, name = "Gradient FAB - Empty State")
@Composable
fun TaskListScreenEmptyPreview() {
    MyApplicationTheme {
        TaskListScreen(
            tasks = emptyList(),
            onNavigateToCreateTask = {},
            onDeleteTask = {}
        )
    }
}
