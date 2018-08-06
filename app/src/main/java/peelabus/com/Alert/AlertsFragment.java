package peelabus.com.Alert;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

import peelabus.com.R;
import peelabus.com.home.fragments.HomeBaseFragment;
import peelabus.com.home.fragments.HomeFragment;

public class AlertsFragment extends HomeBaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static int CHOSEN_ALERT_TIME;

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
        View view = super.onCreateView(inflater, container, savedInstanceState);
        TextInputEditText pickDropAddress = view.findViewById(R.id.pick_or_drop_point_address);
        pickDropAddress.setText(""); //TODO: pass the address text upon receiving the data From the server.

        final AppCompatButton two_min_button = view.findViewById(R.id.two_min_button);
        final AppCompatButton five_min_button = view.findViewById(R.id.five_min_button);
        final AppCompatButton ten_min_button = view.findViewById(R.id.ten_min_button);

        two_min_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_min_button.setBackgroundColor(getResources().getColor(R.color.pickup_drop_card_header));
                five_min_button.setBackgroundColor(Color.WHITE);
                ten_min_button.setBackgroundColor(Color.WHITE);
                CHOSEN_ALERT_TIME = 2;
            }
        });

        five_min_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_min_button.setBackgroundColor(Color.WHITE);
                five_min_button.setBackgroundColor(getResources().getColor(R.color.pickup_drop_card_header));
                ten_min_button.setBackgroundColor(Color.WHITE);
                CHOSEN_ALERT_TIME = 5;
            }
        });

        ten_min_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                two_min_button.setBackgroundColor(Color.WHITE);
                five_min_button.setBackgroundColor(Color.WHITE);
                ten_min_button.setBackgroundColor(getResources().getColor(R.color.pickup_drop_card_header));
                CHOSEN_ALERT_TIME = 10;
            }
        });

        return view;
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
