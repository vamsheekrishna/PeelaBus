package peelabus.com.peelabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import peelabus.com.R;


public class Settings extends AppCompatActivity implements View.OnClickListener {

    private Button pass;
    private Button notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        notif = (Button) findViewById(R.id.notificationset);
        pass = (Button) findViewById(R.id.change);

        notif.setOnClickListener(this);
        pass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.notificationset) {
            Intent intent = new Intent(this, NotificationSettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.change) {
            Intent intent = new Intent(this, ChangePassword.class);
            startActivity(intent);
        }
    }
}
