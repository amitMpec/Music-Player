package com.example.musicplayer.data;

import android.graphics.Bitmap;
import android.net.Uri;

public class NewReleaseModel {


    private String songNames;
    private String singerNames;

    public int getImgIcons() {
        return imgIcons;
    }

    private int imgIcons;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String duration;

    public Uri getSongUri() {
        return songUri;
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri;
    }

    private Uri songUri;


    public NewReleaseModel() {

    }

    public NewReleaseModel(int imgIcons, String songNames, String singerNames) {
        this.imgIcons = imgIcons;
        this.songNames = songNames;
        this.singerNames = singerNames;
    }


    public void setImgIcons(int imgIcons) {
        this.imgIcons = imgIcons;
    }

    public String getSongNames() {
        return songNames;
    }

    public void setSongNames(String songNames) {
        this.songNames = songNames;
    }

    public String getSingerNames() {
        return singerNames;
    }

    public void setSingerNames(String singerNames) {
        this.singerNames = singerNames;
    }

}
