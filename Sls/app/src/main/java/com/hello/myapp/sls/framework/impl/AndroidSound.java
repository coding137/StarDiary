package com.hello.myapp.sls.framework.impl;

import android.media.SoundPool;

import com.hello.myapp.sls.framework.Sound;

/**
 * Created by Cloud on 2017-07-27.
 */

public class AndroidSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(int soundId, SoundPool soundPool) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0,0,1);

    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);

    }
}
