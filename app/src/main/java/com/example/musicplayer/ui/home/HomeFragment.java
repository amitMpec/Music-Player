package com.example.musicplayer.ui.home;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapter.MostLovedAdapter;
import com.example.musicplayer.adapter.NewReleaseAdapter;
import com.example.musicplayer.adapter.RecommendedAdapter;
import com.example.musicplayer.data.NewReleaseModel;

import java.io.IOException;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView newRecyclerView,recommendedrecyclerView,lovedRecyclerView;
    NewReleaseAdapter newReleaseAdapter;
    RecommendedAdapter recommendedAdapter;
    MostLovedAdapter mostLovedAdapter;
    ArrayList<NewReleaseModel> newData;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        newRecyclerView = root.findViewById(R.id.recyclerview);
        recommendedrecyclerView =root.findViewById(R.id.recyclerview1);
        lovedRecyclerView = root.findViewById(R.id.recyclerview2);
        newData = new ArrayList<>();

//        newData = new ArrayList<>();
//        for(int i=0;i<=5;i++) {
//            newData.add(new NewReleaseModel(R.drawable.scenery, "Ben", "By Dianeny"));
//        }
        newReleaseAdapter = new NewReleaseAdapter(getContext(),newData,getParentFragment());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        newRecyclerView.setLayoutManager(linearLayoutManager);
     //  recyclerView.setItemAnimator(new DefaultItemAnimator());
      //  recyclerView.setAdapter(newReleaseAdapter);
        try {
            loadSongs();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recommendedAdapter = new RecommendedAdapter(getContext(),newData);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recommendedrecyclerView.setLayoutManager(linearLayoutManager1);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recommendedrecyclerView.setAdapter(recommendedAdapter);

        mostLovedAdapter = new MostLovedAdapter(getContext(),newData);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        lovedRecyclerView.setLayoutManager(linearLayoutManager2);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        lovedRecyclerView.setAdapter(mostLovedAdapter);


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
     void loadSongs() throws IOException {
        //fetch the audio files from storage
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
//                String albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
//                String albumCoverPath = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART));


                NewReleaseModel  modelAudio = new NewReleaseModel();
                modelAudio.setSongNames(title);
                modelAudio.setSongUri(Uri.parse(url));
                modelAudio.setDuration(duration);
                modelAudio.setSingerNames(artist);
                modelAudio.setImgIcons(R.drawable.scenery);
                newData.add(modelAudio);

//                    modelAudio.setaudioTitle(title);
//                    modelAudio.setaudioArtist(artist);
//                    modelAudio.setaudioUri(Uri.parse(url));
//                    modelAudio.setaudioDuration(duration);
//                    audioArrayList.add(modelAudio);

            } while (cursor.moveToNext());
        }

        newReleaseAdapter = new NewReleaseAdapter(context, newData,getParentFragment());
        newRecyclerView.setAdapter(newReleaseAdapter);
    }

}