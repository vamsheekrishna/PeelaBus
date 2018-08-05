package peelabus.com.Alert;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import peelabus.com.R;
import peelabus.com.home.fragments.HomeBaseFragment;

public class AlertsFragment extends HomeBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnAlertInteractionListener mListener;

    @Override
    protected View setHeaderView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_alerts_header, null);
    }

    @Override
    protected View setBodyView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_alerts_body, null);
    }

    public AlertsFragment() {
        // Required empty public constructor
    }

    public static AlertsFragment newInstance(String param1, String param2) {
        AlertsFragment fragment = new AlertsFragment();
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAlertInteractionListener) {
            mListener = (OnAlertInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAlertInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSuccessResponse(JSONArray response) {

    }

    @Override
    public void onFailureResponse(String response, String exception) {

    }
}
