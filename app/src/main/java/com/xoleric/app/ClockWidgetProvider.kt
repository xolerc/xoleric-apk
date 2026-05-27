package com.xoleric.app

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClockWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Update each widget instance
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        startUpdateService(context)
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
        stopUpdateService(context)
    }

    private fun startUpdateService(context: Context) {
        // Start a periodic update using AlarmManager or WorkManager would be better
        // For simplicity, we'll use a repeating alarm
        // Note: For production, consider using WorkManager for better battery efficiency
    }

    private fun stopUpdateService(context: Context) {
        // Stop the update service
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_clock)
        
        // Update time
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val dateFormat = SimpleDateFormat("d MMMM yyyy", Locale("uz", "UZ"))
        
        val now = Date()
        val timeString = timeFormat.format(now)
        var dateString = dateFormat.format(now)
        
        // Capitalize first letter of date string
        if (dateString.length > 0) {
            dateString = dateString.substring(0, 1).uppercase(Locale.getDefault()) + dateString.substring(1)
        }
        
        views.setTextViewText(R.id.clock_time, timeString)
        views.setTextViewText(R.id.clock_date, dateString)
        
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    companion object {
        private const val ACTION_UPDATE_CLOCK = "com.xoleric.app.action.UPDATE_CLOCK"
        
        fun startClockUpdates(context: Context) {
            // This would typically use WorkManager or AlarmManager for periodic updates
            // For now, we'll just update once when requested
        }
    }
}