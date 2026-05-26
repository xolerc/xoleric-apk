package xoleric.app.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import xoleric.app.R;

public class CalendarWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager manager, int[] appWidgetIds) {
        for (int id : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_calendar);
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String month = new SimpleDateFormat("MMMM", Locale.forLanguageTag("uz")).format(cal.getTime());
            String year = String.valueOf(cal.get(Calendar.YEAR));
            String weekDay = new SimpleDateFormat("EEEE", Locale.forLanguageTag("uz")).format(cal.getTime());
            views.setTextViewText(R.id.widget_day, String.valueOf(day));
            views.setTextViewText(R.id.widget_month, month);
            views.setTextViewText(R.id.widget_year, year);
            views.setTextViewText(R.id.widget_weekday, weekDay);
            manager.updateAppWidget(id, views);
        }
    }
}
