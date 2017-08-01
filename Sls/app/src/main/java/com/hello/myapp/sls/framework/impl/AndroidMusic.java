package com.hello.myapp.sls.framework.impl;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.hello.myapp.sls.framework.Music;

/**
 * Created by Cloud on 2017-07-27.
 * isPrepared 플래그는 mediaplayer의 현재 실행중인지를 확인하고 실행 중일경우 실행을 멈춘다.
 */

public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isPrepared =false;

    public AndroidMusic(AssetFileDescriptor assetFileDescriptor) {
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mediaPlayer.prepare();
            isPrepared=true;
            mediaPlayer.setOnCompletionListener(this);
        }catch (Exception e){
            throw  new RuntimeException("Coudn't load music ");

        }
    }

    @Override
    public void play() {
      if(mediaPlayer.isPlaying())
          return;
        try {
            synchronized (this){
                if(!isPrepared)
                    mediaPlayer.prepare();;
                mediaPlayer.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
        synchronized (this){
            isPrepared=false;
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void setLooping(boolean looping) {
    mediaPlayer.setLooping(isLooping());
    }

    @Override
    public void setVolume(float volume) {
    mediaPlayer.setVolume(volume,volume);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !isPrepared;
    }

    @Override
    public boolean isLooping() {
        return mediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if(mediaPlayer.isPlaying())
            mediaPlayer.stop();
        mediaPlayer.release();// 현재 실행중이 아닐때 release를 호출하면 Runtime Exception이 발생한다.


    }

    @Override
    public void onCompletion(MediaPlayer mp) {//isPrepared 플래그를 설정햇 ㅓ다른 메서드가 갑자기 예외를 발생시키지 못하게 한다.
        synchronized (this){
            isPrepared=false;
        }

    }
}
