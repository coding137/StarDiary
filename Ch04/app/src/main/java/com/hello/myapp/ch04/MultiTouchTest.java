package com.hello.myapp.ch04;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cloud on 2017-08-09.
 */

public class MultiTouchTest extends Activity implements View.OnTouchListener {
    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;
    float[] x = new float[10];
    float[] y = new float[10];
    boolean[] touched = new boolean[10];


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Touch and drag");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    private void updateTextView() {
        stringBuilder.setLength(0);

        for (int i = 0; i < 10; i++) {
            stringBuilder.append(touched[i] + " , ");
            stringBuilder.append(x[i] + " , ");
            stringBuilder.append(y[i] + "\n");

        }
        textView.setText(stringBuilder.toString());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;// ?
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);


        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                touched[pointerId] = true;
                x[pointerId] = (int) event.getX();
                y[pointerId] = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                touched[pointerId] = true;
                x[pointerId] = (int) event.getX();
                y[pointerId] = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount= event.getPointerCount();
                for(int i =0; i < pointerCount; i ++){
                    pointerIndex = i ;
                    pointerId = event.getPointerId(pointerIndex);
                    x[pointerId] = (int)event.getX();
                    y[pointerId]= (int)event.getY();
                }
                break;

        }
        updateTextView();
        return true;
    }
}
