package peelabus.com.home.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import peelabus.com.R;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder>  {
    private View.OnClickListener mOnClickListener;
    private ArrayList<ModuleItemModel> mModuleItemModel;

    public ModuleAdapter(View.OnClickListener onClickListener, ArrayList<ModuleItemModel> moduleItemModel) {
        mOnClickListener = onClickListener;
        mModuleItemModel = moduleItemModel;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_module_item, parent, false);
        itemView.setOnClickListener(mOnClickListener);
        return new ModuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {

        holder.root.setTag(mModuleItemModel.get(position));
        holder.title.setText(mModuleItemModel.get(position).mTitle);
        holder.logo.setImageResource(mModuleItemModel.get(position).mImageID);
    }

    @Override
    public int getItemCount() {
        return mModuleItemModel.size();
    }

}
