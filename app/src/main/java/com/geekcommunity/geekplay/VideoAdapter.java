package com.geekcommunity.geekplay;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Modal> modals;
    List<String> localPath;

    public VideoAdapter(List<Modal> modals) {
        this.modals = modals;
        localPath = new ArrayList<>();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_video_card, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.videoName.setText(modals.get(position).getName());
        localPath.add(modals.get(position).getPath());
    }

    @Override
    public int getItemCount() {
        return modals.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        TextView videoName;
        LinearLayout videoPath;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoName = itemView.findViewById(R.id.video_name);
            videoPath = itemView.findViewById(R.id.linear_path);
            videoPath.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.getContext().startActivity(new Intent(view.getContext(), VideoPlayer.class).putExtra("video_path", localPath.get(getAdapterPosition())));
//                    Toast.makeText(view.getContext(), "Path: "+localPath.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
