package peelabus.com.authentication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        //this will clear the back stack and displays no animation on the screen
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void goToOTPScreen() {
        ValidateOTPFragment validateOTPFragment = ValidateOTPFragment.newInstance("", "");
        addFragment(validateOTPFragment, true, true, "ValidateOTPFragment");
    }

    @Override
    public void goToChangePasswordScreen() {
        ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.newInstance("", "");
        addFragment(changePasswordFragment, true, true, "ChangePasswordFragment");
    }

    @Override
    public void setTagName() {
        setTagName(getLocalClassName());
    }
}
