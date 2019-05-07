package com.developerdj.fullcasa.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.developerdj.fullcasa.modelo.Departamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2/5/2017.
 */

public class TDepartamento extends BDInmueble{

    public static final String TAG = TDepartamento.class.getSimpleName().toLowerCase();

    public static final String[] array={"id","nombre"};

    public static final String create= "CREATE TABLE "+TAG+" ( "+array[0]+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +array[1]+" TEXT NOT NULL);";

    private static TDepartamento INSTANCE = null;

    public TDepartamento(Context context) {
        super(context);
    }

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private synchronized static void createInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TDepartamento(context);
        }
    }

    public static TDepartamento getInstance(Context context) {
        if (INSTANCE == null) createInstance(context);
        return INSTANCE;
    }

    public void insertar(Departamento departamento){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.insert(TAG, null, departamento.getContentValues(array));
            Log.d(TAG, "Inserto correctamente "+TAG);
        }
        db.close();
    }

    public Departamento leer(int id) {
        Departamento item = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, array[0]+"=" + id, null, null, null, null, null);

        if((c != null)&& c.moveToFirst()) {
            item = new Departamento();
            item.parceItem(c);
            Log.d(TAG, "Recupero correctamente "+TAG);
        }
        db.close();
        c.close();
        return item;
    }

    public void modificar(Departamento departamento){
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.update(TAG, departamento.getContentValues(array), array[0]+"=" + departamento.getId(), null);
            Log.d(TAG, "Modifico correctamente "+TAG);
        }
        db.close();
    }

    public void eliminar(Departamento departamento) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.delete(TAG, array[0]+"=" + departamento.getId(), null);
            Log.d(TAG, "Elimino correctamente "+TAG);
        }
        db.close();
    }

    public void lista(List<Departamento> lista) {
        lista.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TAG, array, null, null, null, null, array[1]+" ASC", null);
        if((c != null)&& c.moveToFirst()) {
            do {
                Departamento item= new Departamento();
                item.parceItem(c);
                lista.add(item);
            } while (c.moveToNext());
            Log.d(TAG, "Lista recuperada correctamente "+TAG);
        }
        db.close();
        c.close();
    }

    public void deleteAll(){
        List<Departamento> lista = new ArrayList<Departamento>();
        lista(lista);
        for (Departamento departamento: lista) {
            eliminar(departamento);
        }
        Log.d(TAG, "Eliminado todo correctamente "+TAG);
        lista.clear();
    }

    public boolean isEmpty(){
        List<Departamento> lista= new ArrayList<Departamento>();
        lista(lista);
        boolean result=lista.isEmpty();
        lista.clear();
        return result;
    }
}
