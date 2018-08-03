package peelabus.com.authentication;

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

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import peelabus.com.BuildConfig;
import peelabus.com.R;
import peelabus.com.baseclasses.NetworkBaseFragment;
import peelabus.com.baseclasses.PeelaBusAPI;
import peelabus.com.peelabus.Config;

public class LoginFragment extends LoginBaseFragment implements View.OnClickListener {
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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View header = inflater.inflate(R.layout.login_header,null);
        mHeaderVIew.addView(header);
        View body = inflater.inflate(R.layout.login_body, null);
        mBodyVIew.addView(body);
        //Initializing views
        editTextEmail = (EditText) body.findViewById(R.id.editTextEmail);
        Button forgotPassword = (Button) body.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        editTextPassword = (EditText) body.findViewById(R.id.editTextPassword);
        AppCompatButton buttonLogin = (AppCompatButton) body.findViewById(R.id.ok_button);

        if(BuildConfig.IS_DEBUG) {
            editTextEmail.setText("7877006485");
            editTextPassword.setText("121212");
        }
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

        Map<String,String> params = new HashMap<>();
        params.put(PeelaBusAPI.ParentLogin.EMAIL_KEY, email);
        params.put(PeelaBusAPI.ParentLogin.PASSWORD_KEY, password);
        stringRequest(params, Request.Method.POST, PeelaBusAPI.ParentLogin.URL);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.forgot_password:
                mListener.goToForgotPasswordScreen();
                break;

            case R.id.ok_button:
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


    @Override
    public void onSuccessResponse(JSONArray result) {

        if (result != null && result.length()>0) {
            //Log.i("resp", "resp: " + response);
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

            //Creating editor to store values to shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            /*Gson gson = new GsonBuilder().create();
            ParentLogin parentInfo = gson.fromJson(response, ParentLogin.class);*/
            //Adding values to editor
            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
            editor.putString(Config.EMAIL_SHARED_PREF, editTextEmail.getText().toString().trim());
            editor.putString(Config.RESPONSE, result.toString());
            //Saving values to editor
            editor.apply();
            mListener.goToHomeScreen();
        }else{
            //If the server response is not success
            //Displaying an error message on toast
            Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailureResponse(String response, String exception) {

    }
}
