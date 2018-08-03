package peelabus.com.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import peelabus.com.R;
import peelabus.com.baseclasses.NetworkBaseFragment;

public abstract class LoginBaseFragment extends NetworkBaseFragment {
    protected FrameLayout mHeaderVIew, mBodyVIew;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_base_fragment, container, false);
        mHeaderVIew = view.findViewById(R.id.fragment_root_header);
        mBodyVIew = view.findViewById(R.id.fragment_root_body);
        return view;
    }
}
