package peelabus.com.baseclasses;


import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public abstract class NetworkBaseFragment extends BaseFragment implements OnNetworkInteractionListener {
    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }
    @Override
    public void stringRequest(final Map<String,String> params, int method, String URL) {
        //Getting values from edit texts
        /*final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();*/

        /*final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();*/
        showDialog(false);
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
                            dismissDialog();
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
                            dismissDialog();
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
}
