package com.developerdj.fullcasa.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 2/5/2017.
 */

public class BDInmueble extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "inmueble.db";
    private Context context;

    public BDInmueble(Context context) {
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TCiudad.create);
        db.execSQL(TDepartamento.create);
        db.execSQL(TInmueble.create);
        db.execSQL(TOferta.create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TCiudad.TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TDepartamento.TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TInmueble.TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TOferta.TAG);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
