package peelabus.com.profile;

import android.os.Bundle;

import peelabus.com.Alert.AlertsFragment;
import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;

public class ProfileActivity extends BaseActivity implements OnProfileInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(ProfileFragment.newInstance("", ""), false, false, ProfileFragment.class.getName());
    }

    @Override
    public void setTagName() {
        setTagName(ProfileActivity.class.getName());
    }

    @Override
    public void goToChildView(int childID) {
        ChildInfoDialogFragment childInfoDialogFragment = ChildInfoDialogFragment.newInstance(false,"");
        childInfoDialogFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
    }
}
