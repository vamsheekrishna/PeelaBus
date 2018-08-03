package peelabus.com.home;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.fragments.HomeFragment;

public class PeelaBusHome extends BaseActivity implements OnHomeInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peela_bus_home);

        Fragment fragment = HomeFragment.newInstance("","");
        addFragment(fragment, false,true,"HomeFragment");
    }

    @Override
    public void setTagName() {
        setTagName(PeelaBusHome.class.getName());
    }
}
