package com.example.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.data.SongListModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private final ArrayList<SongListModel> songsData;
    Context context;

    public SongListAdapter(ArrayList<SongListModel> songsData, Context context) {
        this.songsData = songsData;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);

        return new SongListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SongListModel songList = songsData.get(position);
        holder.serialNo.setText(songList.getSerialNo());
        holder.songName.setText(songList.getSongName());
        holder.duration.setText(songList.getDuration());

    }

    @Override
    public int getItemCount() {
        return songsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView serialNo;
        private final TextView songName;
        private final TextView duration;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            serialNo = itemView.findViewById(R.id.serialNo);
            songName = itemView.findViewById(R.id.songName);
            duration = itemView.findViewById(R.id.duration);
            imageView = itemView.findViewById(R.id.imageDots);
        }
    }
}
