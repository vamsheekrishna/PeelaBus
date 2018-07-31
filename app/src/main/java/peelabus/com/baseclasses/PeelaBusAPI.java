package peelabus.com.baseclasses;

import peelabus.com.BuildConfig;

public class PeelaBusAPI {

    public static class ParentLogin {
        public static final String URL = getURL("parentlogin.asmx/ParentLogin");
        public static final String EMAIL_KEY = "username";
        public static final String PASSWORD_KEY = "password";
    }
    public static class CheckMobileNumber {
        public static final String URL_KEY = getURL("Registerno.asmx/CheckMobileno");
        public static final String MOBILE_NUMBER_KEY ="mobileno";
    }
    public static class ChangePassword {
        public static final String URL_KEY = getURL("ForgotPassword.asmx/forgotpassword");
        public static final String KEY_1_OF_3_User ="txtUser";
        public static final String KEY_2_OF_3_NEW_PASSWORD ="txtNewPass";
        public static final String KEY_3_OF_3_CONFORM ="txtConfirmNewPass";
    }
    private static String getURL(String url) {
        return BuildConfig.BASE_URL+url;
    }
}
