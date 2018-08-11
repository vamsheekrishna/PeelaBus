package peelabus.com.profile;

import android.os.Bundle;

import java.util.List;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.models.ChildInfoObj;

public class ProfileActivity extends BaseActivity implements OnProfileInteractionListener {

    private List<ChildInfoObj> mParentDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(ParentProfileFragment.newInstance("", ""), false, false, ParentProfileFragment.class.getName());
    }

    @Override
    public void setTagName() {
        setTagName(ProfileActivity.class.getName());
    }

    @Override
    public void goToChildView(int childID) {
        ChildInfoDialogFragment childInfoDialogFragment = ChildInfoDialogFragment.newInstance(false,childID);
        childInfoDialogFragment.setCancelable(false);
        childInfoDialogFragment.show(getSupportFragmentManager().beginTransaction(), "dialog");
    }
}
