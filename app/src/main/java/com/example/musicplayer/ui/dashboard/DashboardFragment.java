package com.example.musicplayer.ui.dashboard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.musicplayer.R;
import com.example.musicplayer.ui.home.HomeFragment;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.io.IOException;
import java.net.URI;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    String url;
    Bundle args;
    String decider= "blank";
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPositioin =0;
    private PlaybackStateListener playbackStateListener;
    private static final String TAG = DashboardFragment.class.getName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
         args = this.getArguments();
        assert args != null;
        url = args.getString("url");
        playerView = root.findViewById(R.id.video_view);
        playbackStateListener = new PlaybackStateListener();

        BottomNavigationView navBar = getActivity().findViewById(R.id.nav_view);
        navBar.setVisibility(View.INVISIBLE);

//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

//           // if ()
//            try {
//
//              // initializedPlayer();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Util.SDK_INT>=24){
            try {
                initializedPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
     //   hideSystemUi();
        if((Util.SDK_INT<24||player==null)){
            try {
                initializedPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    @SuppressLint("InlineApi")
//    private void hideSystemUi() {
//        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }

    @Override
    public void onPause() {
        super.onPause();
        if(Util.SDK_INT<24){
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(Util.SDK_INT>=24){
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player!= null){
            playWhenReady = player.getPlayWhenReady();
            playbackPositioin = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.removeListener(playbackStateListener);
            player.release();
            player = null;
        }
    }

    public void initializedPlayer() throws IOException {

SimpleExoPlayer    player = new SimpleExoPlayer.Builder(getContext()).build();
        playerView.setPlayer(player);
    MediaItem mediaItem = MediaItem.fromUri(url);
    player.setMediaItem(mediaItem);

    player.prepare();
 //   MediaItem secondMediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3));
  //  player.addMediaItem(secondMediaItem);

//        MediaItem mediaItem = new MediaItem.Builder()
//                .setUri(url)
//               // .setMimeType(MimeTypes.APPLICATION_MPD)
//                .build();
//        player.setMediaItem(mediaItem);
//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow,playbackPositioin);
//        player.prepare();

    }
//        MediaController mediaController = new MediaController(getContext());
//        MediaPlayer mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioAttributes(
//                new AudioAttributes.Builder()
//                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                        .setUsage(AudioAttributes.USAGE_MEDIA)
//                        .build());
//
//        mediaPlayer.setDataSource(getContext(), Uri.parse(url));
//        mediaPlayer.prepare();
//   //     mediaController.setMediaPlayer((MediaController.MediaPlayerControl) mediaPlayer);
//        mediaPlayer.start();
    }

     class PlaybackStateListener implements Player.EventListener {

        @Override
        public void onPlaybackStateChanged(int state) {
            String stateString;
            switch (state) {
                case ExoPlayer
                        .STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE   -";
                    break;
                case ExoPlayer
                        .STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer
                        .STATE_READY:
                    stateString = "ExoPlayer.STATE_READY -";
                    break;
                case ExoPlayer
                        .STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED   -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE   -";
                    break;
            }
            Log.d("Changed", "changed state to " + stateString);
        }
    }
