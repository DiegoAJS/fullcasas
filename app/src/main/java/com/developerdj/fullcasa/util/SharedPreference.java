package com.developerdj.fullcasa.util;

import android.content.Context;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.developerdj.fullcasa.R;

/**
 * Created by hp on 15/2/2017.
 */

public class SharedPreference {

    public static boolean isEmpresaActivity(AppCompatActivity context){
        SharedPreferences sharedPref = context.getSharedPreferences("Referencia",Context.MODE_PRIVATE);
        return  sharedPref.getBoolean(context.getResources().getString(R.string.save_empresa), false);
    }

    public static void setEmpresaActivity(AppCompatActivity context,boolean activity){

        SharedPreferences sharedPref = context.getSharedPreferences("Referencia",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(context.getResources().getString(R.string.save_empresa), activity);
        editor.commit();

    }
}
