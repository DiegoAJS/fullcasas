package com.developerdj.fullcasa.modelo;

import java.util.ArrayList;

public class Propiedad extends Item {

    private Inmueble propiedad;
    private ArrayList<String> imagenes= new ArrayList<String>();
    private Empresa empresa;

    public Propiedad() {
    }

    public Inmueble getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Inmueble propiedad) {
        this.propiedad = propiedad;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
