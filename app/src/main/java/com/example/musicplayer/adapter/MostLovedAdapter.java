package com.example.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.data.NewReleaseModel;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MostLovedAdapter extends RecyclerView.Adapter<MostLovedAdapter.ViewHolder> {

    private ArrayList<NewReleaseModel> data;
    Context context;

    public MostLovedAdapter(Context context, ArrayList<NewReleaseModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MostLovedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_loved_items, parent, false);
        return new MostLovedAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MostLovedAdapter.ViewHolder holder, int position) {
        NewReleaseModel newReleaseData = data.get(position);
        holder.imgIcon.setImageResource(newReleaseData.getImgIcons());
        holder.songName.setText(newReleaseData.getSongNames());
        holder.singer.setText(newReleaseData.getSingerNames());

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView imgIcon;
        private final TextView songName;
        private final TextView singer;
        private final ImageButton favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.iconImg);
            songName = itemView.findViewById(R.id.songName);
            singer = itemView.findViewById(R.id.singerName);
            favorite = itemView.findViewById(R.id.favorite);


        }
    }
}
