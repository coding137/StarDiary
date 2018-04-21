package com.hello.myapp.snakeapp;
import com.hello.myapp.snakeapp.framework.Game;
import com.hello.myapp.snakeapp.framework.Graphics;
import com.hello.myapp.snakeapp.framework.Screen;
import  com.hello.myapp.snakeapp.framework.Graphics.PixmapFormat;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.headUp = g.newPixmap("fox3.png", PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("fox2.png", PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("fox1.png", PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("fox1.png", PixmapFormat.ARGB4444);
        Assets.jump = g.newPixmap("fox4.png",PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.stain1 = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.stain2 = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.stain3 = g.newPixmap("tail.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
        Assets.landscape = g.newPixmap("firstPage2.png", PixmapFormat.RGB565);
        Assets.firstpage =g.newPixmap("landscapeSeam.png",PixmapFormat.RGB565);
        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}