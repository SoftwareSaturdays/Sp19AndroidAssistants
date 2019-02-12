package com.purdueieee.android.lesson4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class LogoView extends View {

    public LogoView(Context context) {
        super(context);
    }

    public LogoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LogoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int mX = 0;
    int mY = 0;
    int dX = 3;
    int dY = 3;
    final int mImageWidth = 176;
    final int mImageHeight = 96;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bgColor = ((ColorDrawable) getBackground()).getColor();
        canvas.drawColor(bgColor);

        if (mX < 0 || mX + mImageWidth > getWidth())
            dX *= -1;
        if (mY < 0 || mY + mImageHeight > getHeight())
            dY *= -1;

        mX += dX;
        mY += dY;

        Drawable d = ContextCompat.getDrawable(getContext(),
                R.drawable.purdue_boilermakers_logo);
        d.setBounds(mX, mY, mX + mImageWidth, mY + mImageHeight);
        d.draw(canvas);

        postInvalidateDelayed(1000 / 60);
    }
}
