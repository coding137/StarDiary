package com.hello.myapp.ch04;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by Cloud on 2017-08-11.
 */

public class RenderViewTest extends Activity {
    class RenderView extends View {
        Random random = new Random();

        RenderView(Context context){
            super(context);

        }//
        protected void  onDraw(Canvas canvas){
            canvas.drawRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            invalidate();
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new RenderView(this));
    }
}
