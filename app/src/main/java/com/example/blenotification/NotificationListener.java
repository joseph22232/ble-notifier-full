package com.example.blenotification;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationListener extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String title = sbn.getNotification().extras.getString("android.title", "");
        String content = sbn.getNotification().extras.getString("android.text", "");
        String msg = title + ": " + content;
        if (MainActivity.notifyChar != null && MainActivity.bluetoothGatt != null) {
            MainActivity.notifyChar.setValue(msg);
            MainActivity.bluetoothGatt.writeCharacteristic(MainActivity.notifyChar);
        }
    }
}
