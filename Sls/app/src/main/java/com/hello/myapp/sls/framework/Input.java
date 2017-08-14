package com.hello.myapp.sls.framework;

/**
 * Created by Cloud on 2017-07-25.
 */
import java.util.List;

public interface Input {
    public static class KeyEvent{
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public int keyChar;

    }

    public static class TouchEvent{
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;

        public int type;
        public int x,y;
        public char keyChar;
    }

    public boolean isKeyPressed(int keyCode);//터치가 됬는지의 여부 판단
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public float getAccelX();//각축의 가속도계측정
    public float getAccelY();//상동
    public float getAccelZ();//상동동
    public List<KeyEvent> getKeyEvents();
    public List<TouchEvent> getTouchEvents();

}
