package com.developerdj.fullcasa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developerdj.fullcasa.modelo.Ciudad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2/5/2017.
 */

public class TCiudad extends BDInmueble{

    public static final String TAG = TCiudad.class.getSimpleName().toLowerCase();

    public static final String[] array={"id","nombre","iddepartamento"};

    public static final String create= "CREATE TABLE "+TAG+" ( "+array[0]+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +array[1]+" TEXT NOT NULL, "+array[2]+" INTEGER );";


    private static TCiudad INSTANCE = null;

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TCiudad(context);
        }
    }

    public static TCiudad getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }

    public TCiudad(Context context) {
        super(context);
    }



    public void insertar(Ciudad ciudad){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.insert(TAG, null, ciudad.getContentValues(array));
            Log.d(TAG, "Inserto correctamente "+TAG);
        }
        db.close();
    }

    public Ciudad leer(int id) {
        Ciudad item = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, array[0]+"=" + id, null, null, null, null, null);

        if((c != null)&& c.moveToFirst()) {
            item = new Ciudad();
            item.parceItem(c);
            Log.d(TAG, "Recupero correctamente "+TAG);
        }
        db.close();
        c.close();
        return item;
    }

    public void modificar(Ciudad ciudad){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.update(TAG, ciudad.getContentValues(array), array[0]+"=" + ciudad.getId(), null);
            Log.d(TAG, "Modifico correctamente "+TAG);
        }
        db.close();
    }

    public void eliminar(Ciudad ciudad) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.delete(TAG, array[0]+"=" + ciudad.getId(), null);
            Log.d(TAG, "Elimino correctamente "+TAG);
        }
        db.close();
    }

    public void lista(List<Ciudad> lista) {
        lista.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, null, null, null, null, array[1]+" ASC", null);
        if((c != null)&& c.moveToFirst()) {
            do {
                Ciudad item= new Ciudad();
                item.parceItem(c);
                lista.add(item);
            } while (c.moveToNext());
            Log.d(TAG, "Lista recuperada correctamente "+TAG);
        }
        db.close();
        c.close();
    }

    public void lista(List<Ciudad> lista, int idDeparatamento) {
        lista.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, array[2]+"="+idDeparatamento, null, null, null, array[1]+" ASC", null);
        if((c != null)&& c.moveToFirst()) {
            do {
                Ciudad item= new Ciudad();
                item.parceItem(c);
                lista.add(item);
            } while (c.moveToNext());
            Log.d(TAG, "Lista recuperada correctamente "+TAG);
        }
        db.close();
        c.close();
    }

    public void deleteAll(){
        List<Ciudad> lista = new ArrayList<Ciudad>();
        lista(lista);
        for (Ciudad ciudad: lista) {
            eliminar(ciudad);
        }
        Log.d(TAG, "Eliminado todo correctamente "+TAG);
        lista.clear();
    }

    public boolean isEmpty(){
        List<Ciudad> lista= new ArrayList<Ciudad>();
        lista(lista);
        boolean result=lista.isEmpty();
        lista.clear();
        return result;
    }
}
