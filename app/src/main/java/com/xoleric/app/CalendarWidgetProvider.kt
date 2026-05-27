package com.xoleric.app

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CalendarWidgetProvider : AppWidgetProvider() {

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

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_calendar)
        
        // Update date
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val monthYearFormat = SimpleDateFormat("MMMM yyyy", Locale("uz", "UZ"))
        val weekdayFormat = SimpleDateFormat("EEEE", Locale("uz", "UZ"))
        
        val now = Date()
        val dayString = dayFormat.format(now)
        var monthYearString = monthYearFormat.format(now)
        var weekdayString = weekdayFormat.format(now)
        
        // Capitalize first letter of month
        if (monthYearString.length > 0) {
            monthYearString = monthYearString.substring(0, 1).uppercase(Locale.getDefault()) + monthYearString.substring(1)
        }
        
        // Uppercase weekday
        weekdayString = weekdayString.uppercase(Locale.getDefault())
        
        views.setTextViewText(R.id.calendar_day, dayString)
        views.setTextViewText(R.id.calendar_month_year, monthYearString)
        views.setTextViewText(R.id.calendar_weekday, weekdayString)
        
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}