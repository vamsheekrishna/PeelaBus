package peelabus.com.baseclasses;

interface OnNetworkChangeListener {

    void onNetworkConnected();
    void onNetworkChanging();
    void onNetworkDisConnected();
    void checkNetworkConnection();
    boolean isNetworkAvailable();
}
