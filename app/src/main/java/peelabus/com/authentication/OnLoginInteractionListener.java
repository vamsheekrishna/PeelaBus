package peelabus.com.authentication;

interface OnLoginInteractionListener {
    void goToHomeScreen();
    void goToForgotPasswordScreen();
    void goToLoginScreen();
    void goToOTPScreen();
    void goToChangePasswordScreen();
}
