package peelabus.com.baseclasses;

import java.util.Map;

interface OnNetworkInteractionListener {
    void showProgressDialog();
    void hideProgressDialog();
    void stringRequest(Map<String,String> params, int method, String URL);
    void onSuccessResponse(String response);
    void onFailureResponse(String response, String exception);
}
