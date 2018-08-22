package peelabus.com.track_my_bus.activitys;

import android.os.Bundle;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;

public class TrackMyBus extends BaseActivity implements OnTrackMyBusInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(BusTrackingFragment.newInstance("", ""),false, false, BusTrackingFragment.class.getName());
    }

    @Override
    public void setTagName() {
        setTagName(TrackMyBus.class.getName());
    }
}
