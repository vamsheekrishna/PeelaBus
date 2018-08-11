package peelabus.com.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import peelabus.com.R;
import peelabus.com.home.OnHomeInteractionListener;
import peelabus.com.home.models.ModuleAdapter;
import peelabus.com.home.models.ModuleItemModel;
import peelabus.com.home.models.ParentInfo;
import peelabus.com.models.ChildInfoObj;


public class HomeFragment extends HomeBaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ArrayList<ModuleItemModel> mModuleItemModel = new ArrayList<>();

    private List<ChildInfoObj> mParam1;
    private String mParam2;

    private OnHomeInteractionListener mListener;
    private RecyclerView recyclerView;
    private ModuleAdapter mAdapter;
    List<ChildInfoObj> ParentDetails = null;
    ChildInfoObj parentLogin;
    @Override
    protected View setHeaderView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home_header,null);
        ((AppCompatTextView)view.findViewById(R.id.parentname)).setText(parentLogin.parentname);
        ((AppCompatTextView)view.findViewById(R.id.parentlocation)).setText(parentLogin.address);
        return view;
    }

    @Override
    protected View setBodyView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home_body,null);

        mModuleItemModel.add(new ModuleItemModel(R.string.track_my_bus,R.mipmap.menu_track_bus));
        mModuleItemModel.add(new ModuleItemModel(R.string.alerts,R.mipmap.menu_alerts));
        mModuleItemModel.add(new ModuleItemModel(R.string.profile,R.mipmap.menu_profiles));
        mModuleItemModel.add(new ModuleItemModel(R.string.share,R.mipmap.menu_shareit));
        mModuleItemModel.add(new ModuleItemModel(R.string.help,R.mipmap.menu_help_feedback));
        mModuleItemModel.add(new ModuleItemModel(R.string.logout,R.mipmap.menu_logout));

        recyclerView = view.findViewById(R.id.module_views);
        mAdapter = new ModuleAdapter(this, mModuleItemModel);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
       return view;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(List<ChildInfoObj> param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ParentDetails = (List<ChildInfoObj>) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ParentInfo parentInfo = ParentInfo.getObject(getContext());
        parentLogin = parentInfo.mParentDetails.get(0);
       return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        /*JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(resp);
            Log.i("jsonObj", "jsonObj:" + jsonObject);
            JSONArray result = null;
            result = jsonObject.getJSONArray("Result");
            Log.i("jsonarray","jsonarray:" + result);
            JSONObject parentobject = null;
            parentobject = new JSONObject(result.getString(0));
            Log.i("name","name: " + parentobject);
            String pickuppoint = parentobject.getString("pickuppoint");
            Log.i("pickup","pickup=="+pickuppoint);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeInteractionListener) {
            mListener = (OnHomeInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        ModuleItemModel moduleItemModel = (ModuleItemModel) v.getTag();
        mListener.goToNextFragment(moduleItemModel.mTitle);
    }

    @Override
    public void onSuccessResponse(JSONArray response) {
        List<ChildInfoObj> posts = Arrays.asList(new GsonBuilder().create().fromJson(String.valueOf(response), ChildInfoObj[].class));
    }

    @Override
    public void onFailureResponse(String response, String exception) {

    }
}
