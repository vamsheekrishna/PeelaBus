package peelabus.com.profile;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.toolbox.NetworkImageView;

class ImageViewHolder extends RecyclerView.ViewHolder {
    FrameLayout mRootView;
    NetworkImageView mChildPhoto;
    ImageViewHolder(View itemView) {
        super(itemView);
    }
}
