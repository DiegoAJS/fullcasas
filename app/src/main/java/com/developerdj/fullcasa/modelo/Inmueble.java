package com.developerdj.fullcasa.modelo;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by hp on 25/1/2017.
 */

public class Inmueble {

    private String titulo;
    private String descripcion;
    private String precio;
    private double area_terreno;
    private double superficie_construccion;
    private int cuartos;
    private int banios;
    private int salas;
    private int garajes;
    private String gas;
    private String agua;
    private String luz;
    private String modo_entrega;
    private int estado;
    private int patios;
    private String que_ofrece;
    private String ubicacion;
    private String id_ciudad;
    private String fecha;
    private String codigo;
    private int idPropiedad;


    public Inmueble() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public double getArea_terreno() {
        return area_terreno;
    }

    public void setArea_terreno(double area_terreno) {
        this.area_terreno = area_terreno;
    }

    public double getSuperficie_construccion() {
        return superficie_construccion;
    }

    public void setSuperficie_construccion(double superficie_construccion) {
        this.superficie_construccion = superficie_construccion;
    }

    public int getCuartos() {
        return cuartos;
    }

    public void setCuartos(int cuartos) {
        this.cuartos = cuartos;
    }

    public int getBanios() {
        return banios;
    }

    public void setBanios(int banios) {
        this.banios = banios;
    }

    public int getSalas() {
        return salas;
    }

    public void setSalas(int salas) {
        this.salas = salas;
    }

    public int getGarajes() {
        return garajes;
    }

    public void setGarajes(int garajes) {
        this.garajes = garajes;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getLuz() {
        return luz;
    }

    public void setLuz(String luz) {
        this.luz = luz;
    }

    public String getModo_entrega() {
        return modo_entrega;
    }

    public void setModo_entrega(String modo_entrega) {
        this.modo_entrega = modo_entrega;
    }

    public int getPatios() {
        return patios;
    }

    public void setPatios(int patios) {
        this.patios = patios;
    }

    public String getQue_ofrece() {
        return que_ofrece;
    }

    public void setQue_ofrece(String que_ofrece) {
        this.que_ofrece = que_ofrece;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(String id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDatosTecnicos(){
        String s=" ";
        if (cuartos>0)
            s+= cuartos+" dormitorios,";
        if(banios>0)
            s+= banios+" baños,";
        if (patios>0)
            s+= patios+" patios,";
        if (garajes>0)
            s+= garajes+" garaje,";
        if (area_terreno>0)
            s+= area_terreno+" metros cuadrados de superficie,";

        if (s.charAt(s.length()-1)==',')
            s=s.substring(1,s.length()-1);

        if ((cuartos+banios+patios+garajes)>0)
            s="El inmueble cuenta con "+ s;
        else
            s="no hay datos expecifico";

        return s;
    }


}
