package peelabus.com.home.models;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import peelabus.com.R;

class ModuleViewHolder extends RecyclerView.ViewHolder {
    private AppCompatTextView title;
    private AppCompatImageView logo;
    ModuleViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.textView);
        logo = itemView.findViewById(R.id.icon);
    }
}
