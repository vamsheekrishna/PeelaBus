package peelabus.com.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import peelabus.com.BuildConfig;
import peelabus.com.R;
import peelabus.com.baseclasses.CustomVolleyRequestQueue;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder>  {
    private View.OnClickListener mOnClickListener;
    private ArrayList<ImageItemModel> mModuleItemModel;
    private Context mContext;

    ImageAdapter(View.OnClickListener onClickListener, ArrayList<ImageItemModel> moduleItemModel, Context context) {
        mOnClickListener = onClickListener;
        mModuleItemModel = moduleItemModel;
        mContext = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.childran_module_item, parent, false);
        ImageViewHolder imageViewHolder =  new ImageViewHolder(itemView);
        imageViewHolder.mRootView = itemView.findViewById(R.id.root_view);
        imageViewHolder.mRootView.setOnClickListener(mOnClickListener);
        imageViewHolder.mChildPhoto = itemView.findViewById(R.id.child_photo);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.mRootView.setTag(position);
        setImageURL(mModuleItemModel.get(position).mURL, holder.mChildPhoto);
    }
    void setImageURL(String path, NetworkImageView networkImageView) {
        ImageLoader mImageLoader = CustomVolleyRequestQueue.getInstance(mContext).getImageLoader();
        mImageLoader.get(path, ImageLoader.getImageListener(networkImageView, R.mipmap.menu_alerts, android.R.drawable.ic_dialog_alert));
        networkImageView.setImageUrl(path, mImageLoader);
    }
    @Override
    public int getItemCount() {
        return mModuleItemModel.size();
    }

}
