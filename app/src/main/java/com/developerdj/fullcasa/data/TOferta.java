package com.developerdj.fullcasa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developerdj.fullcasa.modelo.TipoOferta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2/5/2017.
 */

public class TOferta extends BDInmueble{

    public static final String TAG = TOferta.class.getSimpleName().toLowerCase();

    public static final String[] array={"id","nombre"};

    public static final String create= "CREATE TABLE "+TAG+" ( "+array[0]+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +array[1]+" TEXT NOT NULL);";

    private static TOferta INSTANCE = null;

    public TOferta(Context context) {
        super(context);
    }

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TOferta(context);
        }
    }

    public static TOferta getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }


    public void insertar(TipoOferta oferta){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.insert(TAG, null, oferta.getContentValues(array));
            Log.d(TAG, "Inserto correctamente "+TAG);
        }
        db.close();
    }

    public TipoOferta leer(int id) {
        TipoOferta item = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, array[0]+"=" + id, null, null, null, null, null);

        if((c != null)&& c.moveToFirst()) {
            item = new TipoOferta();
            item.parceItem(c);
            Log.d(TAG, "Recupero correctamente "+TAG);
        }
        db.close();
        c.close();
        return item;
    }

    public void modificar(TipoOferta oferta){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.update(TAG, oferta.getContentValues(array), array[0]+"=" + oferta.getId(), null);
            Log.d(TAG, "Modifico correctamente "+TAG);
        }
        db.close();
    }

    public void eliminar(TipoOferta oferta) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.delete(TAG, array[0]+"=" + oferta.getId(), null);
            Log.d(TAG, "Elimino correctamente "+TAG);
        }
        db.close();
    }

    public void lista(List<TipoOferta> lista) {
        lista.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, null, null, null, null, array[1]+" ASC", null);
        if((c != null)&& c.moveToFirst()) {
            do {
                TipoOferta item= new TipoOferta();
                item.parceItem(c);
                lista.add(item);
            } while (c.moveToNext());
            Log.d(TAG, "Lista recuperada correctamente "+TAG);
        }
        db.close();
        c.close();
    }

    public void deleteAll(){
        List<TipoOferta> lista = new ArrayList<TipoOferta>();
        lista(lista);
        for (TipoOferta oferta: lista) {
            eliminar(oferta);
        }
        Log.d(TAG, "Eliminado todo correctamente "+TAG);
        lista.clear();
    }

    public boolean isEmpty(){
        List<TipoOferta> lista= new ArrayList<TipoOferta>();
        lista(lista);
        boolean result=lista.isEmpty();
        lista.clear();
        return result;
    }
}
