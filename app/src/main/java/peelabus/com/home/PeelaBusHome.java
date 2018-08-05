package peelabus.com.home;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import peelabus.com.Alert.AlertActivity;
import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.fragments.HomeFragment;
import peelabus.com.home.models.ModuleItemModel;

public class PeelaBusHome extends BaseActivity implements OnHomeInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peela_bus_home);

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
                    Intent intent = new Intent(this, AlertActivity.class);
                    startActivity(intent);
                    break;
                case R.string.profile:
                    break;
                case R.string.share:
                    break;
                case R.string.help:
                    break;
                case R.string.logout:
                    break;
            }
            Toast.makeText(this, "onClick :"+ id, Toast.LENGTH_SHORT).show();
    }
}
