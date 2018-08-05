package peelabus.com.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Toast;

import peelabus.com.Alert.AlertActivity;
import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.fragments.HomeFragment;
import peelabus.com.profile.ProfileActivity;

public class PeelaBusHome extends BaseActivity implements OnHomeInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);

        Fragment fragment = HomeFragment.newInstance("", "");
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
                    startActivity(new Intent(this, ProfileActivity.class));
                    break;
                case R.string.share:
                    shareApp();
                    break;
                case R.string.help:
                    break;
                case R.string.logout:
                    break;
            }
            Toast.makeText(this, "onClick :"+ id, Toast.LENGTH_SHORT).show();
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
