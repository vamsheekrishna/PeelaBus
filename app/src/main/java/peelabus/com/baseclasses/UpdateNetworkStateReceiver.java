package peelabus.com.baseclasses;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class UpdateNetworkStateReceiver extends BroadcastReceiver {
    private static final String UpdateNetwork_LOG_TAG = "UpdateNetworkStateReceiver";
    private boolean isConnected = false;

    @SuppressLint("LongLogTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(UpdateNetwork_LOG_TAG, "Notification about network");
        context.sendBroadcast(new Intent("NETWORK_STATE_CHANGE"));
    }
}
