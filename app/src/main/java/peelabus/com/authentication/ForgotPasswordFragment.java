package peelabus.com.authentication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.Map;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseFragment;
import peelabus.com.baseclasses.NetworkBaseFragment;
import peelabus.com.baseclasses.PeelaBusAPI;
import peelabus.com.models.ParentLogin;
import peelabus.com.peelabus.Config;

public class ForgotPasswordFragment extends NetworkBaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnLoginInteractionListener mListener;
    private AppCompatEditText editText1;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }

    public static ForgotPasswordFragment newInstance(String param1, String param2) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        //return inflater.inflate(R.layout.fragment_forgot_password, container, false);

        // Inflate the layout for this fragment
        View view = super.onCreateView(inflater, container, savedInstanceState);
        View header = inflater.inflate(R.layout.login_header,null);
        mHeaderVIew.addView(header);
        View body = inflater.inflate(R.layout.fragment_forgot_password, null);

        body.findViewById(R.id.textInputLayout2).setVisibility(View.GONE);

        TextInputLayout textInputEditText = body.findViewById(R.id.textInputLayout1);
        editText1 = textInputEditText.findViewById(R.id.editText1);
        textInputEditText.setHint(getString(R.string.enter_mobile_number));
        mBodyVIew.addView(body);
        //Initializing views
        /*editTextEmail = (EditText) body.findViewById(R.id.editTextEmail);
        Button forgotPassword = (Button) body.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        editTextEmail.setText("7877006485");
        editTextPassword = (EditText) body.findViewById(R.id.editTextPassword);
        editTextPassword.setText("111111");*/
        AppCompatButton buttonLogin = body.findViewById(R.id.ok_button);

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
                    + " must implement OnLoginInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button :
                String temp = editText1.getText().toString().trim();
                try {
                    Map<String,String> params = new HashMap<>();
                    params.put(PeelaBusAPI.CheckMobileNumber.MOBILE_NUMBER_KEY,temp );
                    stringRequest(params, Request.Method.POST, PeelaBusAPI.CheckMobileNumber.URL_KEY);
                } catch ( Exception e) {

                }

                break;
        }
    }

    @Override
    public void onSuccessResponse(String response) {
        mListener.goToOTPScreen();
    }

    @Override
    public void onFailureResponse(String response, String exception) {
        Toast.makeText(getActivity(),"Please enter valid phone number", Toast.LENGTH_SHORT).show();
    }
}
