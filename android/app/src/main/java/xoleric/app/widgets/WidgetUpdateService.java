package xoleric.app.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;

public class WidgetUpdateService {
    public static void updateAllWidgets(Context context) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int[] ids = manager.getAppWidgetIds(
            new android.content.ComponentName(context, ClockWidget.class)
        );
        for (int id : ids) {
            ClockWidget.updateWidget(context, manager, id);
        }
    }
}
