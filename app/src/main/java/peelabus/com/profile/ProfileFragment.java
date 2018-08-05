package peelabus.com.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import peelabus.com.R;
import peelabus.com.home.fragments.HomeBaseFragment;

public class ProfileFragment extends HomeBaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnProfileInteractionListener mListener;
    private ArrayList<ImageItemModel> mChildernIDs;

    @Override
    protected View setHeaderView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_alerts_header, null);
        ImageView bg = view.findViewById(R.id.header_image);
        bg.setImageResource(R.drawable.profile_bg);
        return view;
    }

    @Override
    protected View setBodyView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_profile_body, null);
        RecyclerView recyclerView = view.findViewById(R.id.child_phots);
        mChildernIDs = new ArrayList<>();
        mChildernIDs.add(new ImageItemModel("url1"));
        mChildernIDs.add(new ImageItemModel("url2"));
        ImageAdapter mAdapter = new ImageAdapter(this, mChildernIDs);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        if (context instanceof OnProfileInteractionListener) {
            mListener = (OnProfileInteractionListener) context;
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

    @Override
    public void onClick(View v) {
        mListener.goToChildView(102);
    }
}
