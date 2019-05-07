package com.developerdj.fullcasa.modelo;

import java.io.Serializable;

public class BuscarEmpresa  extends Item implements Serializable {

    private String nombre;
    private String departamento;
    private String ciudad;
    private int page=1;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void incementPage(){
        page++;
    }

    public void resetPage(){
        page=1;
    }
}
