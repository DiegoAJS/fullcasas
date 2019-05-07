package com.developerdj.fullcasa.modelo;

import android.content.ContentValues;
import android.database.Cursor;

public class Ciudad {

    private int id;
    private String nombre;
    private int departamentos_id;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, int departamentos_id) {
        this.id = id;
        this.nombre = nombre;
        this.departamentos_id = departamentos_id;
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

    public int getDepartamentos_id() {
        return departamentos_id;
    }

    public void setDepartamentos_id(int departamentos_id) {
        this.departamentos_id = departamentos_id;
    }

    public ContentValues getContentValues(String[] array){
        ContentValues valores = new ContentValues();
        valores.put(array[0], getId());
        valores.put(array[1], getNombre());
        valores.put(array[2], getDepartamentos_id());
        return valores;
    }

    public void parceItem(Cursor c){
        setId(c.getInt(0));
        setNombre(c.getString(1));
        setDepartamentos_id(c.getInt(2));
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
