package peelabus.com.baseclasses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import peelabus.com.R;

public class BaseFragment extends Fragment{
    protected FrameLayout mHeaderVIew, mBodyVIew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.root_two_views_fragment, container, false);
        mHeaderVIew = view.findViewById(R.id.fragment_root_header);
        mBodyVIew = view.findViewById(R.id.fragment_root_body);
//Initializing views
        /*editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        Button forgotPassword = (Button) view.findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(this);
        editTextEmail.setText("7877006485");
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        editTextPassword.setText("111111");
        AppCompatButton buttonLogin = (AppCompatButton) view.findViewById(R.id.buttonLogin);

        //Adding click listener
        assert buttonLogin != null;
        buttonLogin.setOnClickListener(this);*/
        return view;
    }
}
