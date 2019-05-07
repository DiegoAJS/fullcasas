package com.developerdj.fullcasa.modelo;

/**
 * Created by hp on 25/1/2017.
 */

public class Aviso {

    private int id;
    private int idUsuario;
    private String descripcion;
    private String empresa;
    private String fecha;

    public Aviso() {
    }

    public Aviso(String descripcion, String empresa, String fecha) {
        this.descripcion = descripcion;
        this.empresa = empresa;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
