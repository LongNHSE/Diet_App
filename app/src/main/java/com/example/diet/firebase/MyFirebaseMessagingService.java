package com.example.diet.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.diet.HomeActivity;
import com.example.diet.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String channelId = "notification_channel";
    private static final String channelName = "com.example.diet";

    @Override
    public void onMessageReceived(RemoteMessage message) {
        if (message.getNotification() != null) {
            generateNotification(message.getNotification().getTitle(), message.getNotification().getBody());
            Log.d("firebase noti:", message.getNotification().getTitle());
        }
    }

    private void generateNotification(String title, String message) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 8, intent,
                PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.lunch)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000, 1000, 1000, 1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        builder = builder.setContent(getRemoteView(title, message));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager.notify(0, builder.build());
    }

    private RemoteViews getRemoteView(String title, String content) {
        RemoteViews remoteView = new RemoteViews("com.example.fcmdemo", R.layout.notification);
        remoteView.setTextViewText(R.id.noti_title, title);
        remoteView.setTextViewText(R.id.noti_content, content);
        remoteView.setImageViewResource(R.id.app_logo, R.drawable.lunch);

        return remoteView;
    }
}
