package peelabus.com.Alert;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.home.OnHomeInteractionListener;

public class AlertActivity extends BaseActivity implements OnAlertInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFragment(AlertsFragment.newInstance("", ""), false, false, AlertsFragment.class.getName());
    }

    @Override
    public void setTagName() {
        setTagName(AlertActivity.class.getName());
    }
}
