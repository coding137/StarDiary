package com.hello.myapp.ch04;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Cloud on 2017-08-10.
 */

public class KeyTest extends Activity implements View.OnKeyListener {


    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Press keys");
        textView.setOnKeyListener(this);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        setContentView(textView);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        stringBuilder.setLength(0);
        switch (event.getAction()){
            case  KeyEvent.ACTION_DOWN:
                stringBuilder.append("down, ");
                break;
            case  KeyEvent.ACTION_UP:
                stringBuilder.append("up, ");
                break;

        }
        stringBuilder.append(event.getKeyCode());
        stringBuilder.append(" , ");
        stringBuilder.append((char)event.getUnicodeChar());
        String text =stringBuilder.toString();
        textView.setText(text);

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            return false;
        else return true;
    }
}
