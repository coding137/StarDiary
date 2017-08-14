package com.hello.myapp.ch04;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Cloud on 2017-08-13.
 */

public class ShapeTest extends Activity {

    class RenderView extends View {
        Paint paint;

        public RenderView(Context context) {
            super(context);
            paint = new Paint();

        }

        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255, 255, 255);
            paint.setColor(Color.RED);
            canvas.drawLine(0, 0, canvas.getWidth()-1, canvas.getHeight()-1 , paint);


            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xff00ff00);
            canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 40, paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0x770000FF);
            canvas.drawRect(100,100,200,200,paint);
        }


    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new RenderView(this));
    }
}
