package cs499.cpp.edu.l10_data_sync_hybrid;

import android.app.Application;

import com.urbanairship.Logger;
import com.urbanairship.UAirship;

/**
 * Created by yusun on 2/29/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UAirship.takeOff(this, new UAirship.OnReadyCallback() {
            @Override
            public void onAirshipReady(UAirship uAirship) {
                // Enable user notifications
                UAirship.shared().getPushManager().setUserNotificationsEnabled(true);
                String channelId = UAirship.shared().getPushManager().getChannelId();
                Logger.info("My Application Channel ID: " + channelId);
            }
        });
    }
}
