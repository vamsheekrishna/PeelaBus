package peelabus.com.profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import peelabus.com.R;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>  {
    private View.OnClickListener mOnClickListener;
    private ArrayList<ImageItemModel> mModuleItemModel;

    ImageAdapter(View.OnClickListener onClickListener, ArrayList<ImageItemModel> moduleItemModel) {
        mOnClickListener = onClickListener;
        mModuleItemModel = moduleItemModel;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.childran_module_item, parent, false);
        itemView.setOnClickListener(mOnClickListener);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mModuleItemModel.size();
    }

}
