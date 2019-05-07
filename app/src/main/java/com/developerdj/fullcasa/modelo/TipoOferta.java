package com.developerdj.fullcasa.modelo;

import android.content.ContentValues;
import android.database.Cursor;

public class TipoOferta {

    private int id;
    private String nombre;

    public TipoOferta() {
    }

    public TipoOferta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public ContentValues getContentValues(String[] array){
        ContentValues valores = new ContentValues();
        valores.put(array[0], getId());
        valores.put(array[1], getNombre());
        return valores;
    }

    public void parceItem(Cursor c){
        setId(c.getInt(0));
        setNombre(c.getString(1));
    }
}