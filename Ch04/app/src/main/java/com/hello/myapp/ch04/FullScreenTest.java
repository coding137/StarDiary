package com.hello.myapp.ch04;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Cloud on 2017-08-11.
 */

public class FullScreenTest extends  SingleTouchTest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
    }
}
