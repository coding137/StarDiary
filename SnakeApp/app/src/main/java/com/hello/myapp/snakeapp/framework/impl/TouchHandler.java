package com.hello.myapp.snakeapp.framework.impl;

import java.util.List;

import android.view.View.OnTouchListener;
import  com.hello.myapp.snakeapp.framework.Input.TouchEvent;
import  com.hello.myapp.snakeapp.framework.Pool;
import  com.hello.myapp.snakeapp.framework.Pool.PoolObjectFactory;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);
    
    public int getTouchX(int pointer);
    
    public int getTouchY(int pointer);
    
    public List<TouchEvent> getTouchEvents();
}
