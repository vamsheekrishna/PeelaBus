package peelabus.com.baseclasses;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;

import peelabus.com.R;

public class CustomDialogFragment extends DialogFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String DEFAULT = "";
    private boolean mDialogType;
    private String mInfoText;

    public CustomDialogFragment() {
        // Required empty public constructor
    }

    public static CustomDialogFragment newInstance(boolean isShowCancelButton, String param2) {
        CustomDialogFragment fragment = new CustomDialogFragment();
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
        View v= inflater.inflate(R.layout.fragment_custom, container, false);
        ProgressBar pb = v.findViewById(R.id.network_progress_bar);
        AppCompatButton cancel = v.findViewById(R.id.app_close_button);
        AppCompatTextView infoTextView = v.findViewById(R.id.network_info_text);
        if(!mInfoText.equals(DEFAULT)) {
            infoTextView.setText(mInfoText);
        }
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        pb.setVisibility(View.VISIBLE);
        if(mDialogType) {
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.exit(0);
                }
            });
        } else {
            cancel.setVisibility(View.GONE);
        }

        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBaseAppListener) {
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    enum DialogType {
        NO_BUTTON(0), SINGLE_BUTTON(1), TWO_BUTTONS(0);

        DialogType(int i) {

        }
    }
}
