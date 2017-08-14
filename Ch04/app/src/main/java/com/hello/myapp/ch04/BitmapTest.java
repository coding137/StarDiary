package com.hello.myapp.ch04;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Cloud on 2017-08-13.
 */

public class BitmapTest extends Activity {

    class RenderView extends View{
        Bitmap bob565;
        Bitmap bob4444;
        Rect dst = new Rect();

        public RenderView(Context context) {
            super(context);

            try {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("bobrgb888.png");
                bob565 = BitmapFactory.decodeStream(inputStream);
                inputStream.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
