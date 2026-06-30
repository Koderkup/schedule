package com.example.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schedule.ui.theme.ScheduleTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase
    private lateinit var repository: ChapterRepository
    private lateinit var settingsDataStore: SettingsDataStore
    private lateinit var reminderManager: ReminderManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.getDatabase(this)
        repository = ChapterRepository(database.chapterDao())
        settingsDataStore = SettingsDataStore(this)
        reminderManager = ReminderManager(this)

        setContent {
            val isDarkTheme by settingsDataStore.isDarkThemeFlow.collectAsState(initial = false)

            ScheduleTheme(
                darkTheme = isDarkTheme,
                dynamicColor = true
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(
                        repository = repository,
                        settingsDataStore = settingsDataStore,
                        isDarkTheme = isDarkTheme,
                        reminderManager = reminderManager
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    repository: ChapterRepository,
    settingsDataStore: SettingsDataStore,
    isDarkTheme: Boolean,
    reminderManager: ReminderManager
) {
    val navController = rememberNavController()
    val viewModel: BookViewModel = viewModel(
        factory = BookViewModelFactory(repository)
    )

    LaunchedEffect(Unit) {
        viewModel.updateStats()
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Book, contentDescription = "Библия") },
                    label = { Text("Чтение") },
                    selected = navController.currentDestination?.route == "home",
                    onClick = { navController.navigate("home") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Поиск") },
                    label = { Text("Поиск") },
                    selected = navController.currentDestination?.route == "search",
                    onClick = { navController.navigate("search") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.BarChart, contentDescription = "Статистика") },
                    label = { Text("Статистика") },
                    selected = navController.currentDestination?.route == "stats",
                    onClick = { navController.navigate("stats") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Настройки") },
                    label = { Text("Настройки") },
                    selected = navController.currentDestination?.route == "settings",
                    onClick = { navController.navigate("settings") }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                BookScreen(viewModel = viewModel)
            }
            composable("search") {
                SearchScreen(viewModel = viewModel)
            }
            composable("stats") {
                StatsScreen(viewModel = viewModel)
            }
            composable("settings") {
                SettingsScreen(
                    viewModel = viewModel,
                    settingsDataStore = settingsDataStore,
                    isDarkTheme = isDarkTheme,
                    reminderManager = reminderManager
                )
            }
        }
    }
}