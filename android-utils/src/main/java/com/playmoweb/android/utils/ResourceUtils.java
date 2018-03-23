package com.playmoweb.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * ResourceUtil
 *
 * @author Playmoweb
 */
public class ResourceUtils {
    public static Drawable getDrawableByName(final Context context, final String name) {
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        if (resourceId != 0) {
            return resources.getDrawable(resourceId);
        } else {
            return null;
        }
    }
}
