package com.developerdj.fullcasa.modelo;

/**
 * Created by hp on 25/4/2017.
 */

public class Noticia extends Item {

    private int id;
    private String titulo;
    private String contenido;
    private String fecha;
    private String imagen;
    private String by;
    private int id_empresa;

    public Noticia (){

    }

    public Noticia (String titulo, String contenido, String fecha, String by, String imagen){
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.by = by;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }
}
