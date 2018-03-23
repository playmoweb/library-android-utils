package com.playmoweb.android.utils;

import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * URLDrawable
 *
 * @author Playmoweb
 */
public class URLDrawable extends BitmapDrawable {
    public Drawable drawable;

    @Override
    public void draw(final Canvas canvas) {
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }
}
