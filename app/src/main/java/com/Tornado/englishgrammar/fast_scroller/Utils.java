package com.Tornado.englishgrammar.fast_scroller;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

class Utils {

    static float getViewRawY(View view) {
        int[] location = new int[2];
        location[0] = 0;
        location[1] = (int) view.getY();
        ((View)view.getParent()).getLocationInWindow(location);
        return location[1];
    }

    static float getViewRawX(View view) {
        int[] location = new int[2];
        location[0] = (int) view.getX();
        location[1] = 0;
        ((View)view.getParent()).getLocationInWindow(location);
        return location[0];
    }

    static float getValueInRange(float min, float max, float value) {
        float minimum = Math.max(min, value);
        return Math.min(minimum, max);
    }

    static void setBackground(View view, Drawable drawable){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

}
