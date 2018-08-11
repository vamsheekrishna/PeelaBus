package peelabus.com.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import peelabus.com.BuildConfig;
import peelabus.com.R;
import peelabus.com.baseclasses.CustomVolleyRequestQueue;
import peelabus.com.home.models.ParentInfo;
import peelabus.com.models.ChildInfoObj;

public class ChildInfoDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String DEFAULT = "";
    private boolean mDialogType;
    private int mChildId;
    private ParentInfo parentInfo;
    private NetworkImageView mNetworkImageView;

    public ChildInfoDialogFragment() {
        // Required empty public constructor
    }

    public static ChildInfoDialogFragment newInstance(boolean isShowCancelButton, int param2) {
        ChildInfoDialogFragment fragment = new ChildInfoDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isShowCancelButton);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDialogType = getArguments().getBoolean(ARG_PARAM1);
            mChildId = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentInfo = ParentInfo.getObject(getContext());
        ChildInfoObj childInfoObj = parentInfo.mParentDetails.get(mChildId);
        View v= inflater.inflate(R.layout.fragment_child_info, container, false);
        View back = v.findViewById(R.id.ok_button);

        mNetworkImageView = v.findViewById(R.id.child_photo);

        setImageURL(childInfoObj.imagepath, mNetworkImageView);

        AppCompatEditText childName = v.findViewById(R.id.child_name);
        childName.setText(childInfoObj.studentname);

        AppCompatEditText childClass = v.findViewById(R.id.email);
        childClass.setText(childInfoObj.studentclass);

        AppCompatEditText mobileNumber = v.findViewById(R.id.section);
        mobileNumber.setText(childInfoObj.section);

        AppCompatEditText alternativeMobileNumber = v.findViewById(R.id.buss_number);
        alternativeMobileNumber.setText(childInfoObj.busno);

        back.setOnClickListener(this);
        return v;
    }

    void setImageURL(String path, NetworkImageView networkImageView) {
        ImageLoader mImageLoader = CustomVolleyRequestQueue.getInstance(getActivity()).getImageLoader();
        final String url = BuildConfig.IMAGE_BASE_URL+path;
        mImageLoader.get(url, ImageLoader.getImageListener(networkImageView, R.mipmap.menu_alerts, android.R.drawable.ic_dialog_alert));
        networkImageView.setImageUrl(url, mImageLoader);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
