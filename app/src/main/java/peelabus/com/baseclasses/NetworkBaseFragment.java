package peelabus.com.baseclasses;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import peelabus.com.R;

public abstract class NetworkBaseFragment extends BaseFragment implements OnNetworkInteractionListener {

    private CustomDialogFragment customFragment;

    @Override
    public void showProgressDialog(boolean isShowCancelButton, String string) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        customFragment = CustomDialogFragment.newInstance(isShowCancelButton, string);
        customFragment.show(ft, "dialog");
    }

    @Override
    public void hideProgressDialog() {
        if(null != customFragment && customFragment.isVisible()) {
            customFragment.dismiss();
        }
    }
    @Override
    public void stringRequest(final Map<String,String> params, int method, String URL) {
        showProgressDialog(false, getString(R.string.validating_data));
        //Creating a string request
        StringRequest stringRequest = new StringRequest(method, URL,

                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.i("resp1", "resp1: " + response);
                            //If we are getting success from server
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.i("jsonObj", "jsonObj:" + jsonObject);
                            JSONArray result = null;
                            try {
                                result = jsonObject.getJSONArray("Result");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            onSuccessResponse(result);
                        } catch (Exception e) {
                            onFailureResponse(response, e.getMessage());
                        } finally {
                            hideProgressDialog();
                            //pDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            onFailureResponse(error.getMessage(), "");
                        } catch (Exception e){
                            onFailureResponse(error.getMessage(), error.getMessage());
                        } finally {
                            hideProgressDialog();
                        }
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        Log.i("req", "req" + requestQueue);
    }


/*    public void showDialog(boolean isShowCancelButton) {

    }

    public void dismissDialog() {

    }*/
}
