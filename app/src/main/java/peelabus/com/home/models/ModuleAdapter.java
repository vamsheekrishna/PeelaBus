package peelabus.com.home.models;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import peelabus.com.R;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleViewHolder> implements View.OnClickListener {

    public ModuleAdapter() {

    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_module_item, parent, false);

        itemView.setOnClickListener(this);
        return new ModuleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public void onClick(View v) {
        Log.d("onClick", "onClick");
    }
}
