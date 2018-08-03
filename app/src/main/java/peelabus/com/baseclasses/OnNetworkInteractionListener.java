package peelabus.com.baseclasses;

import org.json.JSONArray;

import java.util.Map;

interface OnNetworkInteractionListener {

    boolean isShowCancelButton = false;
    void showProgressDialog(boolean isShowCancelButton, String string);
    void hideProgressDialog();
    void stringRequest(Map<String,String> params, int method, String URL);
    void onSuccessResponse(JSONArray response);
    void onFailureResponse(String response, String exception);
}
