package com.hello.myapp.ch04;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Cloud on 2017-08-09.
 */

public class LifeCycleTest extends Activity {


    StringBuilder stringBuilder = new StringBuilder();
    TextView textView;

    private void log(String string) {
        Log.d("LifeCycleTest", string);
        stringBuilder.append(string);
        stringBuilder.append('\n');
        textView.setText(stringBuilder.toString());
        setContentView(textView);

    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText(stringBuilder.toString());
        setContentView(textView);
        log("Created");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log("onPause");

        if(isFinishing()){
            log("finishing");
        }
    }
}
