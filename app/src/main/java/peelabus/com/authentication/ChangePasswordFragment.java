package peelabus.com.authentication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import peelabus.com.R;
import peelabus.com.baseclasses.NetworkBaseFragment;
import peelabus.com.baseclasses.PeelaBusAPI;

public class ChangePasswordFragment extends LoginBaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mPhoneNumber;
    private String mParam2;

    private OnLoginInteractionListener mListener;
    private AppCompatEditText editText1;
    private AppCompatEditText editText2;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
            mPhoneNumber = getArguments().getString(ARG_PARAM1);
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
        View body = inflater.inflate(R.layout.fragment_change_password, null);
        mBodyVIew.addView(body);

        body.findViewById(R.id.textInputLayout2).setVisibility(View.VISIBLE);

        editText1 = view.findViewById(R.id.editText1);

        editText2 = view.findViewById(R.id.editText2);

        AppCompatTextView loginBodyViewHeader = body.findViewById(R.id.login_body_view_header);
        loginBodyViewHeader.setText(getText(R.string.reset_password_text));
        //Initializing views
        /*editTextEmail = (EditText) body.findViewById(R.id.editTextEmail);
        Button forgotPassword = (Button) body.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        editTextEmail.setText("7877006485");
        editTextPassword = (EditText) body.findViewById(R.id.editTextPassword);
        editTextPassword.setText("111111");*/
        AppCompatButton buttonLogin = body.findViewById(R.id.ok_button);
        buttonLogin.setText(getText(R.string.done));
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
                if(!temp.isEmpty() && temp.equals(editText2.getText().toString().trim())){
                    try {
                        Map<String,String> params = new HashMap<>();
                        params.put(PeelaBusAPI.ChangePassword.KEY_1_OF_3_User,mPhoneNumber );
                        params.put(PeelaBusAPI.ChangePassword.KEY_2_OF_3_NEW_PASSWORD,temp );
                        params.put(PeelaBusAPI.ChangePassword.KEY_3_OF_3_CONFORM,temp );
                        stringRequest(params, Request.Method.POST, PeelaBusAPI.ChangePassword.URL_KEY);
                    } catch ( Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter valid Phone Number", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccessResponse(JSONArray response) {
        /*if( response.length()>0 ) {*/
            mListener.goToLoginScreen();
        /*} else {
            Toast.makeText(getActivity(), "Please enter valid number.", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onFailureResponse(String response, String exception) {
        Toast.makeText(getActivity(), "Please enter valid number.", Toast.LENGTH_SHORT).show();
    }
}
