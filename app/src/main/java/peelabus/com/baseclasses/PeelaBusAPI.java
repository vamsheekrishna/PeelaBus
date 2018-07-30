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
    private static String getURL(String url) {
        return BuildConfig.BASE_URL+url;
    }
}
