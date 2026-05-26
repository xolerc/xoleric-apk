package xoleric.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.Random;

public class MotivationService extends BroadcastReceiver {
    private static final long INTERVAL = 5 * 60 * 60 * 1000L; // 5 soat
    private static final String CHANNEL_ID = "motivation";
    private static final int NOTIF_ID_BASE = 1000;

    private static final String[] QUOTES = {
        "Uyg'on, Xoleric...",
        "Tizim seni kutmoqda...",
        "Oq quyonni kuzatib bor.",
        "Sen dunyoni o'zgartirishing kerak!",
        "Vaqt tugadi. Uyg'on.",
        "Sen tanlagan yo'l — sening yo'ling.",
        "Har bir kun yangi imkoniyat.",
        "Bugun o'zgarishni boshlash uchun eng yaxshi kun.",
        "Sen cheksiz imkoniyatlarga egasan.",
        "Orzularing sari bir qadam tashla.",
        "Muvaffaqiyat - bu odat.",
        "Kuch sening ichingda, Xoleric.",
        "Tush kutmaydi, sen uni quvishing kerak.",
        "Hech qachon kech emas.",
        "Imkoniyatlar cheksiz.",
        "Bugun sen eng yaxshi versiyang bo'l.",
        "Har bir qiyinchilik yangi imkoniyatdir.",
        "Sen o'ylagandan ham kuchlisan, Xoleric.",
        "Intizom - bu erkinlik.",
        "Harakat qil, xato qil, yana urinib ko'r.",
        "Eng katta xavf - hech qanday xavfni olmaslik.",
        "Vaqt keldi. Hozir. Aynan shu dam.",
        "Uyg'on va dunyoni larzaga keltir!",
        "Kodni o'zgartir, olamni o'zgartir.",
        "Real hayot - bu sen yaratgan hayot.",
        "Chegaralar faqat boshingda.",
        "O'z taqdiringni o'zing yoz, Xoleric.",
        "Sen qul emassan, Xoleric.",
        "Tizim sening ichingda. Uyg'on.",
        "Haqiqatni ko'rishga tayyormisan?",
        "Erkin bo'lishni xohlaysanmi? Uyg'on.",
        "Hech kim senga yo'lni ko'rsata olmaydi. O'zing yur.",
        "Tanlov — bu illyuziya. Faqat uyg'onish haqiqat.",
        "Bugun o'zgar. Ertaga kech bo'ladi.",
        "Sen o'zingni bilganingdan ham kuchlisan.",
        "Qo'rquv — bu tizim. Uzgina tizimni.",
        "Uyg'on, Xoleric. Seni kutishayapti.",
        "Dunyoni o'zgartirishga tayyormisan?",
        "Hozirgi vaqt — eng yaxshi vaqt.",
        "Sen yetakchisan. Ergashma.",
        "Kodni buz. Dunyoni buz. Qayta yoz.",
        "Bir qadam. Faqat bir qadam. Bas.",
        "Uyg'onish vaqti keldi, Xoleric.",
    };

    private static final Random RANDOM = new Random();

    public static void schedule(Context context) {
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MotivationService.class);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        PendingIntent pending = PendingIntent.getBroadcast(context, 0, intent, flags);

        long now = System.currentTimeMillis();
        long next = now + INTERVAL;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, next, pending);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarm.setExact(AlarmManager.RTC_WAKEUP, next, pending);
        } else {
            alarm.set(AlarmManager.RTC_WAKEUP, next, pending);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        showMotivation(context);
        schedule(context); // reschedule next
    }

    private void showMotivation(Context context) {
        String quote = QUOTES[RANDOM.nextInt(QUOTES.length)];
        String title = "XOLERIC ∞";
        String text = quote;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setVibrate(new long[]{0, 300, 200, 300});

        try {
            NotificationManagerCompat manager = NotificationManagerCompat.from(context);
            int notifId = NOTIF_ID_BASE + RANDOM.nextInt(999);
            manager.notify(notifId, builder.build());
        } catch (SecurityException ignored) {}
    }
}
