package com.paprbit.popularmovies.util;

import android.content.Context;

/**
 * Created by ankush38u on 3/17/2016.
 */
public class PixelDpConverter {

    public static int pxToDp(float px,Context context) {
        return (int) (px / context.getResources().getDisplayMetrics().density);
    }

    public static int dpToPx(float dp, Context context) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
