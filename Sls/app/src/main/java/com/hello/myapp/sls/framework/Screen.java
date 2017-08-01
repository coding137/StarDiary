package com.hello.myapp.sls.framework;

/**
 * Created by Cloud on 2017-07-26.
 */

public abstract class Screen {
    protected final Game game;

    public Screen(Game game){
        this.game =game;
    }

    public  abstract void update(float deltaTime);
    public  abstract void present(float detlaTime);
    public  abstract void pause();
    public  abstract void resume();
    public  abstract void dispose();

}
