package peelabus.com.baseclasses;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

/**
 * Created by VAMSHEE on 21/07/2018.
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private Locale mLocale = null;
    public ApplicationLifecycleHandler handler;
    private AppCompatActivity mCurrentActivity = null;

    /**
     * Returns the application context
     *
     * @return application context
     */
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(AppCompatActivity currentActivity){
        this.mCurrentActivity = currentActivity;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

}

