package com.hello.myapp.snakeapp;

import java.util.List;

import com.hello.myapp.snakeapp.framework.Game;
import com.hello.myapp.snakeapp.framework.Graphics;
import com.hello.myapp.snakeapp.framework.Screen;
import com.hello.myapp.snakeapp.framework.Input.TouchEvent;
import com.hello.myapp.snakeapp.framework.Pixmap;


public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                }
                if (inBounds(event, 270, 128, 192, 42)) {
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if (inBounds(event, 64, 270 + 128, 192, 42)) {
                    game.setScreen(new HighscoreScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if (inBounds(event, 270, 128 + 84, 192, 42)) {
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
          g.drawPixmap(Assets.firstpage, 0,0);
//        g.drawPixmap(Assets.landscape, 0, 0);
        g.drawPixmap(Assets.logo, 16, 32);
        g.drawPixmap(Assets.mainMenu, 270, 128);
        g.drawPixmap(Assets.headLeft, 64, 192);
        g.drawPixmap(Assets.tail, 32, 224);
        g.drawPixmap(Assets.tail, 64, 224);
        g.drawPixmap(Assets.tail, 96, 224);
        g.drawPixmap(Assets.tail, 128, 224);
        g.drawPixmap(Assets.tail, 160, 224);

        if (Settings.soundEnabled)
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        else
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
