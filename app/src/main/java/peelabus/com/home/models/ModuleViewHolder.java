package peelabus.com.home.models;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import peelabus.com.R;

class ModuleViewHolder extends RecyclerView.ViewHolder {
    public AppCompatTextView title;
    public AppCompatImageView logo;
    public LinearLayout root;
    ModuleViewHolder(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.root);
        title = itemView.findViewById(R.id.textView);
        logo = itemView.findViewById(R.id.icon);
    }
}
