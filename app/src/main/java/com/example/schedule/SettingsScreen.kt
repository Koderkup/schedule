package com.example.schedule

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun SettingsScreen(
    viewModel: BookViewModel,
    settingsDataStore: SettingsDataStore,
    isDarkTheme: Boolean,
    reminderManager: ReminderManager? = null
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var showConfirmDialog by remember { mutableStateOf(false) }
    var darkTheme by remember { mutableStateOf(isDarkTheme) }

    var remindersEnabled by remember { mutableStateOf(false) }
    var reminderHour by remember { mutableIntStateOf(8) }
    var reminderMinute by remember { mutableIntStateOf(0) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "⚙️ Настройки",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Темная тема
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "🌙 Темная тема",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Включить темную тему",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Switch(
                            checked = darkTheme,
                            onCheckedChange = { checked ->
                                darkTheme = checked
                                scope.launch {
                                    settingsDataStore.setDarkTheme(checked)
                                }
                            }
                        )
                    }
                }
            }

            // Напоминания с выбором времени
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "🔔 Напоминания",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = if (remindersEnabled)
                                        "Ежедневно в ${String.format(Locale.getDefault(), "%02d:%02d", reminderHour, reminderMinute)}"
                                    else
                                        "Ежедневное напоминание о чтении",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Switch(
                                checked = remindersEnabled,
                                onCheckedChange = { checked ->
                                    remindersEnabled = checked
                                    if (checked) {
                                        reminderManager?.scheduleDailyReminderAt(reminderHour, reminderMinute)
                                    } else {
                                        reminderManager?.cancelReminder()
                                    }
                                }
                            )
                        }

                        if (remindersEnabled) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { showTimePicker = true },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            ) {
                                Text("Изменить время: ${String.format(Locale.getDefault(), "%02d:%02d", reminderHour, reminderMinute)}")
                            }
                        }
                    }
                }
            }

            // Очистить данные
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "🗑️ Очистить данные",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.error
                            )
                            Text(
                                text = "Удалить все загруженные главы",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onErrorContainer
                            )
                        }
                        Button(
                            onClick = { showConfirmDialog = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Очистить")
                        }
                    }
                }
            }

            // Загрузить данные заново
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "🔄 Перезагрузить данные",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Загрузить план чтения заново",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Button(
                            onClick = {
                                scope.launch {
                                    viewModel.clearAllData()
                                    viewModel.addSampleData()
                                }
                            }
                        ) {
                            Text("Обновить")
                        }
                    }
                }
            }

            // Информация
            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "📖 План чтения Библии",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Версия 1.0 | Данные © Watch Tower",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    // Диалог выбора времени
    if (showTimePicker) {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                reminderHour = hourOfDay
                reminderMinute = minute
                if (remindersEnabled) {
                    reminderManager?.cancelReminder()
                    reminderManager?.scheduleDailyReminderAt(hourOfDay, minute)
                }
                showTimePicker = false
            },
            reminderHour,
            reminderMinute,
            true
        ).show()
        showTimePicker = false
    }

    // Диалог подтверждения очистки
    if (showConfirmDialog) {
        AlertDialog(
            onDismissRequest = { showConfirmDialog = false },
            title = { Text("Очистить данные?") },
            text = { Text("Все загруженные главы будут удалены. Вы уверены?") },
            confirmButton = {
                Button(
                    onClick = {
                        scope.launch {
                            viewModel.clearAllData()
                            showConfirmDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Удалить")
                }
            },
            dismissButton = {
                Button(onClick = { showConfirmDialog = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}