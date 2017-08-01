package com.hello.myapp.myrecycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Cloud on 2017-06-16.
 */

public class ItemDeco extends RecyclerView.ItemDecoration {

    private final int dividerHeight;
    private Drawable divider;

    public ItemDeco(Context context) {
        final TypedArray a = context.obtainStyledAttributes(new int[] {android.R.attr.listDivider});
        divider = a.getDrawable(0);

        assert divider != null;
        dividerHeight = divider.getIntrinsicHeight();
        a.recycle();

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final  int lineLeft = parent.getPaddingLeft();
        final  int lineRight= parent.getWidth()-parent.getPaddingRight();

        final  int childCount= parent.getChildCount();

        for(int i = 0 ; i < childCount ; i ++){
            final  View child = parent.getChildAt(i);
            final  RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int childTransitionY = Math.round(ViewCompat.getTranslationY(child));
            final  int top = child.getBottom() +params.bottomMargin+childTransitionY;
            final  int bottom = top+dividerHeight;

            divider.setBounds(lineLeft,top,lineRight,bottom);
            divider.draw(c);


        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        super.onDrawOver(c, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(0,0,0,dividerHeight);
    }
}
