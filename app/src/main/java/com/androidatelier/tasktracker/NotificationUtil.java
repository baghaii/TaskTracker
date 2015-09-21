package com.androidatelier.tasktracker;

/**
 * Created by baghaii on 9/21/15.
 */

import android.app.Notification;
import android.content.Context;


public class NotificationUtil {

    public static void showTaskDueNotification(Context context,
        Class <?> actionActivityClass) {
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.drawable.bread_notification)
                .setContentTitle("TaskTracker")
                .setContentText("Do some task!").build();
    }
}
