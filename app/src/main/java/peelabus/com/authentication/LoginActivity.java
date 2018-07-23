package peelabus.com.authentication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseActivity;
import peelabus.com.peelabus.MainActivity;

public class LoginActivity extends BaseActivity implements OnLoginInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTagName();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment = LoginFragment.newInstance("", "");
        addFragment(loginFragment, false, false, "LoginFragment");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
    }

    @Override
    public void goToHomeScreen() {
        //Starting profile activity
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void goToForgotPasswordScreen() {
        ForgotPasswordFragment forgotPasswordFragment = ForgotPasswordFragment.newInstance("", "");
        addFragment(forgotPasswordFragment, true, true, "Forgot Password Fragment");

    }

    @Override
    public void goToLoginScreen() {
        /*LoginFragment loginFragment = LoginFragment.newInstance("", "");
        addFragment(loginFragment, true, true, "LoginFragment");*/

    }

    @Override
    public void setTagName() {
        setTagName(getLocalClassName());
    }
}
