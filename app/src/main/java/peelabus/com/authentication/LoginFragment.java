package peelabus.com.authentication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseFragment;
import peelabus.com.peelabus.Config;

public class LoginFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    //Defining views
    private EditText editTextEmail;
    private EditText editTextPassword;

    private OnLoginInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
//Initializing views
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        Button forgotPassword = (Button) view.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        editTextEmail.setText("7877006485");
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        editTextPassword.setText("111111");
        AppCompatButton buttonLogin = (AppCompatButton) view.findViewById(R.id.buttonLogin);

        //Adding click listener
        assert buttonLogin != null;
        buttonLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void login(){
        //Getting values from edit texts
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,

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
                            Log.i("jsonarray","jsonarray:" + result);
                            if (result != null && result.length()>0) {
                                if (!response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                                    //Creating a shared preference
                                    Log.i("resp", "resp: " + response);
                                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                    //Creating editor to store values to shared preferences
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    //Adding values to editor
                                    editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                    editor.putString(Config.EMAIL_SHARED_PREF, email);
                                    editor.putString(Config.RESPONSE, response);
                                    //Saving values to editor
                                    editor.commit();
                                    mListener.goToHomeScreen();
                                }
                            }else{
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        } finally {
                            pDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {

                        } catch (Exception e){

                        } finally {
                            pDialog.dismiss();
                        }
                        //You can handle error here if you want
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
//                params.put("Content-Type", "application/json");
                params.put(Config.KEY_EMAIL, email);
                params.put(Config.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        Log.i("req", "req" + requestQueue);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.forgot_password:
                mListener.goToForgotPasswordScreen();
                break;

            case R.id.buttonLogin:
                final String email = editTextEmail.getText().toString();
                Log.i("email","email==" + email);
                if ( !isValidMobile(email) ) {
                    editTextEmail.setError("Invalid Email or Mobile Number");
                }

                final String pass = editTextPassword.getText().toString();
                if (!isValidPassword(pass)) {
                    editTextPassword.setError("Invalid Password");
                }

                if ( isValidMobile(email) && isValidPassword(pass)   ) {
                    //Calling the login function
                    login();
                }
                break;
        }

//        login();
    }

    private boolean isValidMobile(String email) {
        if(email.length() != 10) {
            return false;
        } else {
            return true;
        }
    }

    //     validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        }
        return false;
    }


}
