package com.hello.myapp.snakeapp.framework;

import com.hello.myapp.snakeapp.framework.Game;
import com.hello.myapp.snakeapp.framework.Graphics;
import com.hello.myapp.snakeapp.framework.Screen;
import  com.hello.myapp.snakeapp.framework.Input.TouchEvent;
import  com.hello.myapp.snakeapp.framework.Pixmap;
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
