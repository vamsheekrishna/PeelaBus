package peelabus.com.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import peelabus.com.R;

public class ChildInfoDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String DEFAULT = "";
    private boolean mDialogType;
    private String mInfoText;

    public ChildInfoDialogFragment() {
        // Required empty public constructor
    }

    public static ChildInfoDialogFragment newInstance(boolean isShowCancelButton, String param2) {
        ChildInfoDialogFragment fragment = new ChildInfoDialogFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, isShowCancelButton);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDialogType = getArguments().getBoolean(ARG_PARAM1);
            mInfoText = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_child_info, container, false);
        View back = v.findViewById(R.id.ok_button);
        back.setOnClickListener(this);
        return v;
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
