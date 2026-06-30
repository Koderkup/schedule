package com.example.schedule

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val reminderManager = ReminderManager(context)
        reminderManager.showReminderNotification()
    }
}