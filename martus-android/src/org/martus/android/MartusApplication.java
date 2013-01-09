package org.martus.android;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

/**
 * @author roms
 *         Date: 10/24/12
 */
public class MartusApplication extends Application {

    public static final long INACTIVITY_TIMEOUT = 600000; // 10 min = 10 * 60 * 1000 ms
    public static boolean ignoreInactivity = false;

    private Handler inactivityHandler = new Handler(){
        public void handleMessage(Message msg) {
        }
    };

    private Runnable inactivityCallback = new LogOutProcess();

    public void resetInactivityTimer(){
        inactivityHandler.removeCallbacks(inactivityCallback);
        if (!ignoreInactivity) {
            inactivityHandler.postDelayed(inactivityCallback, INACTIVITY_TIMEOUT);
        }
    }

    public void stopInactivityTimer(){
        inactivityHandler.removeCallbacks(inactivityCallback);
    }

    public void setIgnoreInactivity(boolean ignore) {
        ignoreInactivity = ignore;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        initSingletons();
    }

    protected void initSingletons()
    {
        AppConfig.initInstance(this.getCacheDir(), this.getApplicationContext());
    }


}
