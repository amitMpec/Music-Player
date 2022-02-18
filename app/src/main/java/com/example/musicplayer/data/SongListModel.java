package com.example.musicplayer.data;

public class SongListModel {

    private String serialNo;
    private String songName;
    private String duration;

    public SongListModel() {
    }

    public SongListModel(String serialNo, String songName, String duration) {
        this.serialNo = serialNo;
        this.songName = songName;
        this.duration = duration;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

}
