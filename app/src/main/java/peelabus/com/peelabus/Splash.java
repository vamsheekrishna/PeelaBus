package peelabus.com.peelabus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import peelabus.com.R;
import peelabus.com.authentication.LoginActivity;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.PeelaBusHome;


public class Splash extends BaseActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private boolean isGoToNextScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTagName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //checkNetworkConnection();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                isGoToNextScreen = true;
                goToNextScreen();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void goToNextScreen() {

        if(isNetworkAvailable() && isGoToNextScreen) {
            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            boolean loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
            //If we will get true
            if(loggedIn){
                //We will start the Profile Activity
                Intent intent = new Intent(Splash.this, PeelaBusHome.class);
                startActivity(intent);
            } else {
                Intent mainIntent = new Intent(Splash.this, LoginActivity.class);
                startActivity(mainIntent);
            }
            finish();
        }
    }

    @Override
    public void setTagName() {
        setTagName(getLocalClassName());
    }

    @Override
    public void onNetworkConnected() {
        super.onNetworkConnected();
        goToNextScreen();
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

    }
}
