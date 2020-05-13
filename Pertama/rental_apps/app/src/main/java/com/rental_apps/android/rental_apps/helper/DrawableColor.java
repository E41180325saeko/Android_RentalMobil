package com.rental_apps.android.rental_apps.helper;

import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;



public class DrawableColor {

    public static Drawable setColor(Drawable drawable, int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        return drawable;
    }

}
