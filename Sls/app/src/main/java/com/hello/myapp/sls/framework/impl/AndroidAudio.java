package com.hello.myapp.sls.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.hello.myapp.sls.framework.Audio;
import com.hello.myapp.sls.framework.Music;
import com.hello.myapp.sls.framework.Sound;

import java.io.IOException;

/**
 * Created by Cloud on 2017-07-27.
 * 인스턴스는 AssetManager와  SoundPool 이 있다.
 * AssetManager는 AndroidAudio.newSound()를 호추할때 지원 파일로부터 효과음 로드 후에 SoundPool로 넣는다.
 *
 */

public class AndroidAudio implements Audio {

    AssetManager assets;
    SoundPool soundPool;
    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC , 0 );


    }

    @Override
    public Music newMusic(String filename) {//
        try {
            AssetFileDescriptor assetFileDescriptor =assets.openFd(filename);
//            return new AndroidMusic()
        }catch (IOException e){
            throw  new RuntimeException("Load music fail :"+ filename);
        }

        return null;
    }

    @Override
    public Sound newSound(String filename) {
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
            int soundId= soundPool.load(assetFileDescriptor, 0 );
//            return  new AndroidSound(soundPool, soundId);
        }catch (IOException e){
            throw new RuntimeException("Load sound problem : "+filename );

        }
        return  null;

    }
}
