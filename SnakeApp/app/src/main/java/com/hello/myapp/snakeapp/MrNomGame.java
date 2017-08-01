package com.hello.myapp.snakeapp;
import  com.hello.myapp.snakeapp.framework.Screen;
import  com.hello.myapp.snakeapp.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}