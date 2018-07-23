package peelabus.com.peelabus;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import peelabus.com.R;
import peelabus.com.authentication.LoginActivity;
import peelabus.com.baseclasses.BaseActivity;


public class Splash extends BaseActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTagName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //checkNetworkConnection();
    }

    @Override
    public void setTagName() {
        setTagName(getLocalClassName());
    }

    @Override
    public void onNetworkConnected() {
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        if(isNetworkAvailable()) {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent mainIntent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }

    @Override
    public void onNetworkDisConnected() {
        Log.d(TAG, "Network DisConnected.");
        Toast.makeText(this, "Network DisConnected.", Toast.LENGTH_SHORT).show();
    }
}
