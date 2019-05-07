package com.developerdj.fullcasa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developerdj.fullcasa.modelo.TipoInmueble;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2/5/2017.
 */

public class TInmueble extends BDInmueble{

    public static final String TAG = TInmueble.class.getSimpleName().toLowerCase();

    public static final String[] array={"id","nombre"};

    public static final String create= "CREATE TABLE "+TAG+" ( "+array[0]+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +array[1]+" TEXT NOT NULL);";

    private static TInmueble INSTANCE = null;

    public TInmueble(Context context) {
            super(context);
        }

        // creador sincronizado para protegerse de posibles problemas  multi-hilo
        // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TInmueble(context);
        }
    }

    public static TInmueble getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }



    public void insertar(TipoInmueble inmueble){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.insert(TAG, null, inmueble.getContentValues(array));
            Log.d(TAG, "Inserto correctamente "+TAG);
        }
        db.close();
    }

    public TipoInmueble leer(int id) {
        TipoInmueble item = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, array[0]+"=" + id, null, null, null, null, null);

        if((c != null)&& c.moveToFirst()) {
            item = new TipoInmueble();
            item.parceItem(c);
            Log.d(TAG, "Recupero correctamente "+TAG);
        }
        db.close();
        c.close();
        return item;
    }

    public void modificar(TipoInmueble inmueble){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.update(TAG, inmueble.getContentValues(array), array[0]+"=" + inmueble.getId(), null);
            Log.d(TAG, "Modifico correctamente "+TAG);
        }
        db.close();
    }

    public void eliminar(TipoInmueble inmueble) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.delete(TAG, array[0]+"=" + inmueble.getId(), null);
            Log.d(TAG, "Elimino correctamente "+TAG);
        }
        db.close();
    }

    public void lista(List<TipoInmueble> lista) {
        lista.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, null, null, null, null, array[1]+" ASC", null);
        if((c != null)&& c.moveToFirst()) {
            do {
                TipoInmueble item= new TipoInmueble();
                item.parceItem(c);
                lista.add(item);
            } while (c.moveToNext());
            Log.d(TAG, "Lista recuperada correctamente "+TAG);
        }
        db.close();
        c.close();
    }

    public void deleteAll(){
        List<TipoInmueble> lista = new ArrayList<TipoInmueble>();
        lista(lista);
        for (TipoInmueble inmueble: lista) {
            eliminar(inmueble);
        }
        Log.d(TAG, "Eliminado todo correctamente "+TAG);
        lista.clear();
    }

    public boolean isEmpty(){
        List<TipoInmueble> lista= new ArrayList<TipoInmueble>();
        lista(lista);
        boolean result=lista.isEmpty();
        lista.clear();
        return result;
    }
}
