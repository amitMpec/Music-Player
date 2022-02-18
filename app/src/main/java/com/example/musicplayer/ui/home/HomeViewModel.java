package com.example.musicplayer.ui.home;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.musicplayer.MainActivity;
import com.example.musicplayer.adapter.NewReleaseAdapter;
import com.example.musicplayer.data.NewReleaseModel;
import com.example.musicplayer.data.SongListModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<SongListModel>> songList;
    private NewReleaseAdapter newReleaseAdapter;

    Context context;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Changed");
    }

    public LiveData<List<SongListModel>> getAllSongs() {
        if (songList == null) {
            songList = new MutableLiveData<List<SongListModel>>();
         //   loadSongs();
        }
        return songList;
    }

    public LiveData<String> getText() {
        return mText;
    }


}