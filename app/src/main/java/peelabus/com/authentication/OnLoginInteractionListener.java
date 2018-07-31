package peelabus.com.authentication;

interface OnLoginInteractionListener {
    void goToHomeScreen();
    void goToForgotPasswordScreen();
    void goToLoginScreen();
    void goToOTPScreen(String phoneNumber);
    void goToChangePasswordScreen(String phoneNumber);
}
