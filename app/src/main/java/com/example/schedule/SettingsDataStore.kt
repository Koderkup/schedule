package com.example.schedule

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extension property для DataStore — должен быть на уровне файла, не внутри класса!
private val Context.dataStore by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {

    companion object {
        private val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
        private val REMINDERS_ENABLED_KEY = booleanPreferencesKey("reminders_enabled")
        private val REMINDER_HOUR_KEY = intPreferencesKey("reminder_hour")
        private val REMINDER_MINUTE_KEY = intPreferencesKey("reminder_minute")
    }

    // Темная тема
    val isDarkThemeFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    suspend fun setDarkTheme(isDark: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = isDark
        }
    }

    // Напоминания - включены/выключены
    val isRemindersEnabledFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[REMINDERS_ENABLED_KEY] ?: false
        }

    suspend fun setRemindersEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[REMINDERS_ENABLED_KEY] = enabled
        }
    }

    // Час напоминания
    val reminderHourFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[REMINDER_HOUR_KEY] ?: 8
        }

    suspend fun setReminderHour(hour: Int) {
        context.dataStore.edit { preferences ->
            preferences[REMINDER_HOUR_KEY] = hour
        }
    }

    // Минута напоминания
    val reminderMinuteFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[REMINDER_MINUTE_KEY] ?: 0
        }

    suspend fun setReminderMinute(minute: Int) {
        context.dataStore.edit { preferences ->
            preferences[REMINDER_MINUTE_KEY] = minute
        }
    }
}