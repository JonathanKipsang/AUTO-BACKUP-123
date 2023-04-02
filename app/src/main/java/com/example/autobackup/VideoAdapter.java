package com.example.autobackup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private Context mContext;
    private List<Upload> mUploads;

    public VideoAdapter(Context context, List<Upload> uploads){
        mContext= context;
        mUploads= uploads;
    }
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Upload uploadCurrent= mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Picasso.with(mContext)
                .load(uploadCurrent.getFileUrl());

    }

    @Override
    public int getItemCount() {

        return mUploads.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName;
        public ImageView videoView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName= itemView.findViewById(R.id.text_view_name);
            videoView= itemView.findViewById(R.id.video_view_upload);
        }
    }
}
