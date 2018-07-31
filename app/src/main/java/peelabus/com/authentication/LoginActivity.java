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
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //finish();
    }

    @Override
    public void goToForgotPasswordScreen() {
        ForgotPasswordFragment forgotPasswordFragment = ForgotPasswordFragment.newInstance("", "");
        addFragment(forgotPasswordFragment, true, true, "Forgot Password Fragment");
        //goToOTPScreen("7799495240");
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
    public void goToOTPScreen(String phoneNumber) {
        ValidateOTPFragment validateOTPFragment = ValidateOTPFragment.newInstance(phoneNumber, "");
        addFragment(validateOTPFragment, true, false, "ValidateOTPFragment");
    }

    @Override
    public void goToChangePasswordScreen(String phoneNumber) {
        ChangePasswordFragment changePasswordFragment = ChangePasswordFragment.newInstance(phoneNumber, "");
        addFragment(changePasswordFragment, true, false, "ChangePasswordFragment");
    }

    @Override
    public void setTagName() {
        setTagName(getLocalClassName());
    }
}
