package peelabus.com.baseclasses;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import peelabus.com.R;

public abstract class BaseActivity extends AppCompatActivity implements OnBaseAppListener {

    boolean isWifiConnected = false;
    boolean isMobileDataConnected = false;
    protected String TAG = "BaseActivity";
    private GlobalDialogBox globalDialogBox;

    public void setTagName(String name) {
        String classpath[] = name.split("\\.");
        TAG = classpath[classpath.length-1];
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalDialogBox = new GlobalDialogBox("Network Alert", "Network Disconnected", this,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    }
                }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //checkNetworkConnection();
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onStop() {
        super.onStop();
        isWifiConnected = false;
        isMobileDataConnected = false;
        unregisterReceiver(networkChangeReceiver);
    }

    BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkNetworkConnection();
        }
    };

    @Override
    public void onNetworkChanging() {
        Log.d(TAG, "Network Changing");
        Toast.makeText(this, "Network Changing........", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNetworkConnected() {
        globalDialogBox.alertDialog.dismiss();
    }

    @Override
    public void onNetworkDisConnected() {
        //alertDialog();
        globalDialogBox.alertDialog.show();
    }

    @Override
    public void checkNetworkConnection() {
        isWifiConnected = false;
        isMobileDataConnected = false;
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivity != null;
        NetworkInfo[] netInfo = connectivity.getAllNetworkInfo();
        if (null != netInfo) {
            for (NetworkInfo ni : netInfo) {
                if (ni.getState() == NetworkInfo.State.CONNECTED) {
                    if (ni.getTypeName().equalsIgnoreCase("WIFI")  && ni.isConnected())
                        isWifiConnected = true;
                    if (ni.getTypeName().equalsIgnoreCase("MOBILE") && ni.isConnected())
                        isMobileDataConnected = true;
                    //onNetworkConnected();
                }/* else if (ni.getState() == NetworkInfo.State.CONNECTING || ni.getState() == NetworkInfo.State.CONNECTING) {
                    isWifiConnected = false;
                    isMobileDataConnected = false;
                    //onNetworkChanging();
                } else if (ni.getState() == NetworkInfo.State.DISCONNECTED) {
                    isWifiConnected = false;
                    isMobileDataConnected = false;
                    //onNetworkDisConnected();
                }*/
            }
        }

        if(isWifiConnected || isMobileDataConnected) {
            onNetworkConnected();
        } else {
            onNetworkDisConnected();
        }
    }
    protected void addFragment(Fragment fragment, boolean isReplace, boolean isAddToBackStack, String fragment_name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(isReplace) {
            fragmentTransaction.replace(R.id.root_content, fragment, fragment_name);
        } else {
            fragmentTransaction.add(R.id.root_content, fragment, fragment_name);
        }
        if(isAddToBackStack) {
            fragmentTransaction.addToBackStack(fragment_name);
        }
        fragmentTransaction.commit();
    }

    public void alertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Network not found.");
        alertDialog.setPositiveButton("Check Setting",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        alertDialog.show();
    }
    @Override
    public boolean isNetworkAvailable() {
        return isWifiConnected || isMobileDataConnected;
    }
}
