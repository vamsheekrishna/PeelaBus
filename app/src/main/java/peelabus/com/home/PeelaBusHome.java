package peelabus.com.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import peelabus.com.Alert.AlertActivity;
import peelabus.com.R;
import peelabus.com.authentication.LoginActivity;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.fragments.HomeFragment;
import peelabus.com.home.models.ParentInfo;
import peelabus.com.models.ChildInfoObj;
import peelabus.com.peelabus.Config;
import peelabus.com.profile.ProfileActivity;

public class PeelaBusHome extends BaseActivity implements OnHomeInteractionListener {

    private List<ChildInfoObj> mParentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        Fragment fragment = HomeFragment.newInstance(mParentDetails, "");
        addFragment(fragment, false,false,"HomeFragment");
    }

    @Override
    public void setTagName() {
        setTagName(PeelaBusHome.class.getName());
    }

    @Override
    public void goToNextFragment(int id) {
            switch (id) {
                case R.string.track_my_bus:
                    break;
                case R.string.alerts:
                    startActivity(new Intent(this, AlertActivity.class));
                    break;
                case R.string.profile:
                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivity(intent);
                    break;
                case R.string.share:
                    shareApp();
                    break;
                case R.string.help:
                    break;
                case R.string.logout:
                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                    //Creating editor to store values to shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                    editor.putString(Config.EMAIL_SHARED_PREF, "");
                    editor.putString(Config.RESPONSE, "");
                    ParentInfo.deleteInstance();
                    editor.apply();
                    Intent login = new Intent(this, LoginActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(login);
                    finish();
                    break;
            }
            //Toast.makeText(this, "onClick :"+ id, Toast.LENGTH_SHORT).show();
    }
    public void shareApp()
    {
        final String appPackageName = getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
