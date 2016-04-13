package com.paprbit.popularmovies.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ankush38u on 4/7/2016.
 */
public class UserInteraction {
    public static void makeToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
    public static void makeSnack(View parent, String text){
        Snackbar.make(parent,text, Snackbar.LENGTH_LONG).show();
    }

    public static void makeSnackOk(View parent, String text){
        Snackbar.make(parent,text, Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
