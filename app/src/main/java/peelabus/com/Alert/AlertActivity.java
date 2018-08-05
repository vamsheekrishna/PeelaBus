package peelabus.com.Alert;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;

public class AlertActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.alerts);
        setSupportActionBar(toolbar)*/;
    }

    @Override
    public void setTagName() {
        setTagName(AlertActivity.class.getName());
    }
}
