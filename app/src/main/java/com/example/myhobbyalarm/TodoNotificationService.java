package com.example.myhobbyalarm;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.UUID;

public class TodoNotificationService extends IntentService {
    public static final String TODOTEXT = "TodoNotificationServiceText";
    public static final String TODOUUID = "TodoNotificationServiceUUID";
    private String mTodoText;
    private UUID mTodoUUID;

    public TodoNotificationService() {
        super("TodoNotificationService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        mTodoText = intent.getStringExtra(TODOTEXT);
        mTodoUUID = (UUID) intent.getSerializableExtra(TODOUUID);

        Log.d("TodoNotificationService", "onHandleIntent called");
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, ReminderActivity.class);
        i.putExtra(TodoNotificationService.TODOUUID, mTodoUUID);
        Intent deleteIntent = new Intent(this, DeleteNotificationService.class);
        deleteIntent.putExtra(TODOUUID, mTodoUUID);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = new Notification.Builder(this)
                    .setContentTitle(mTodoText)
                    .setSmallIcon(R.drawable.ic_done_white_24dp)
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setDeleteIntent(PendingIntent.getService(this, mTodoUUID.hashCode(), deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                    .setContentIntent(PendingIntent.getActivity(this, mTodoUUID.hashCode(), i, PendingIntent.FLAG_UPDATE_CURRENT))
                    .build();
        }

        manager.notify(100, notification);
    }
}
