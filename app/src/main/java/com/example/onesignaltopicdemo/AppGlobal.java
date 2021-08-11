package com.example.onesignaltopicdemo;

import android.app.Application;

import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class AppGlobal extends Application implements OneSignal.OSNotificationOpenedHandler {

    private static final String ONESIGNAL_APP_ID = "ac8026f7-5284-4f62-b9e9-030c5ba749e2";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
    }

    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {
        JSONObject data = result.notification.payload.additionalData;
        String topic;

        if (data != null) {
            topic = data.optString("topic", null);
            if (topic != null)
                OneSignal.sendTag("topic", topic);
        }
    }
}
