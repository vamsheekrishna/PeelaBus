package peelabus.com.authentication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import peelabus.com.R;
import peelabus.com.baseclasses.BaseFragment;

public class ForgotPasswordFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnLoginInteractionListener mListener;
    private AppCompatEditText editText1;
    private TextInputLayout txtl_1;

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
        mBodyVIew.addView(body);
        editText1 = view.findViewById(R.id.editText1);
        txtl_1 = view.findViewById(R.id.textInputLayout1);
        txtl_1.setHint(getString(R.string.enter_mobile_number));
//        editText1.setHint(R.string.enter_mobile_number);
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
                    mListener.goToOTPScreen();
                break;
        }
    }
}
