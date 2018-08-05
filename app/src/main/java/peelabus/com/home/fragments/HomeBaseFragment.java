package peelabus.com.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.json.JSONArray;

import peelabus.com.R;
import peelabus.com.baseclasses.NetworkBaseFragment;
import peelabus.com.home.OnHomeInteractionListener;

public abstract class HomeBaseFragment extends NetworkBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    protected abstract View setHeaderView(LayoutInflater inflater);
    protected abstract View setBodyView(LayoutInflater inflater);

    public HomeBaseFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_base_home, container, false);
        FrameLayout header = view.findViewById(R.id.fragment_root_header);
        FrameLayout body = view.findViewById(R.id.fragment_root_body);
        header.addView(setHeaderView(inflater));;
        body.addView(setBodyView(inflater));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
