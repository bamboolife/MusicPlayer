package com.sundy.music.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import com.sundy.music.R;

import java.io.IOException;

public class MusicService extends Service {
    private MediaPlayer player;
    private int playStatus;
    int  playTime;
    boolean isPause=false;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
     return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (player==null) {
            player = MediaPlayer.create(this, R.raw.music);
            player.setLooping(false);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle=intent.getExtras();
        playStatus=bundle.getInt("status");
        switch (playStatus){
            case 0:
                startMusic();
                break;
            case 1:
                pauseMusic();
                break;
            case 2:
                stopMusic();
                break;
            case 3:
                loopMusic();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void startMusic(){
        if (player!=null){

                player.start();

        }
    }

    public void stopMusic(){
        if (player!=null){
            player.stop();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic(){
        if (player!=null&&player.isPlaying()){
            player.pause();
        }
    }
    public void loopMusic(){
        if (player!=null&&player.isPlaying()){
            if (player.isLooping()){
                player.setLooping(false);
            }else {
                player.setLooping(true);
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.stop();
            player.release();
        }
    }
}
