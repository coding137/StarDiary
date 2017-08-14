package com.hello.myapp.ch04;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Cloud on 2017-08-11.
 */

public class MediaPlayerTest extends Activity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        setContentView(textView);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();

        try {
            AssetManager assetManager = getAssets();
            AssetFileDescriptor descriptor = assetManager.openFd("music.ogg");
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);

        }catch (IOException e){
            textView.setText("Couldn't load music file, "+ e.getMessage());
            mediaPlayer= null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer !=null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer !=null){
            mediaPlayer.pause();
            if (isFinishing()){
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }
}
