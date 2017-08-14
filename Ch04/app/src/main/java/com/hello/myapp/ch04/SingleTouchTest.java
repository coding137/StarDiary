package com.hello.myapp.ch04;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * Created by Cloud on 2017-08-09.
 */

public class SingleTouchTest extends Activity implements OnTouchListener {

    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Touch and Drag (Only one finger)");
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        stringBuilder.setLength(0);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                stringBuilder.append("down ");
                break;
            case MotionEvent.ACTION_MOVE:
                stringBuilder.append("move ");
                break;
            case MotionEvent.ACTION_CANCEL:
                stringBuilder.append("Cancel ");
                break;
            case MotionEvent.ACTION_UP:
                stringBuilder.append("Up ");
                break;

        }
        stringBuilder.append(event.getX());
        stringBuilder.append(", ");
        stringBuilder.append(event.getY());
        String text = stringBuilder.toString();
        Log.d("Single Touch Test", text);
        textView.setText(text);
        return true;
    }
}
