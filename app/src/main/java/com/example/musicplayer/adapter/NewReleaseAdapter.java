package com.example.musicplayer.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.data.NewReleaseModel;
import com.example.musicplayer.ui.dashboard.DashboardFragment;
import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;
import java.util.ArrayList;

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.ViewHolder> {

    private final ArrayList<NewReleaseModel> data;
    Context context;
    Fragment currentFragment;
    // DashboardFragment dashboardFragment = new DashboardFragment();

    public NewReleaseAdapter(Context context, ArrayList<NewReleaseModel> data, Fragment currentFragment) {
        this.context = context;
        this.data = data;
        this.currentFragment = currentFragment;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_release_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewReleaseModel newReleaseData = data.get(position);
        holder.imgIcon.setImageResource(newReleaseData.getImgIcons());
        holder.songName.setText(newReleaseData.getSongNames());
        holder.singer.setText(newReleaseData.getSingerNames());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = newReleaseData.getSongUri();
                Bundle bundle = new Bundle();
                bundle.putString("url", String.valueOf(uri));
                DashboardFragment fragment = new DashboardFragment();
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.homeFragment, fragment).addToBackStack(null).setReorderingAllowed(true).commit();

//
//                fragment.setArguments(bundle);
//                FragmentManager fragmentManager = currentFragment.getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.hide(currentFragment);
//                fragmentTransaction.replace(R.id.navigation_home, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

//                try {
//                    dashboardFragment.playSong(context,uri);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView imgIcon;
        private final TextView songName;
        private final TextView singer;
        private final ImageButton favorite;
        private final CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgIcon = itemView.findViewById(R.id.iconImg);
            songName = itemView.findViewById(R.id.songName);
            singer = itemView.findViewById(R.id.singerName);
            favorite = itemView.findViewById(R.id.favorite);
            cardView = itemView.findViewById(R.id.newReleaseId);
        }
    }
}
