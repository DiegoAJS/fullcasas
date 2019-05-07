package com.developerdj.fullcasa.modelo;

import java.io.Serializable;

/**
 * Created by hp on 2/2/2017.
 */

public class Buscar extends Item implements Serializable {


    private String titulo;
    private String tipos_ofertas;
    private String tipos_inmuebles;
    private String departamento;
    private String ciudad;
    private String banios;
    private String cuartos;
    private String area;
    private String superficie;
    private String pmin;
    private String pmax;
    private int page=1;

    private String nombre;

    public Buscar() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipos_ofertas() {
        return tipos_ofertas;
    }

    public void setTipos_ofertas(String tipos_ofertas) {
        this.tipos_ofertas = tipos_ofertas;
    }

    public String getTipos_inmuebles() {
        return tipos_inmuebles;
    }

    public void setTipos_inmuebles(String tipos_inmuebles) {
        this.tipos_inmuebles = tipos_inmuebles;
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

    public String getBanios() {
        return banios;
    }

    public void setBanios(String banios) {
        this.banios = banios;
    }

    public String getCuartos() {
        return cuartos;
    }

    public void setCuartos(String cuartos) {
        this.cuartos = cuartos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSuperficie() {
        return superficie;
    }

    public void setSuperficie(String superficie) {
        this.superficie = superficie;
    }

    public String getPmin() {
        return pmin;
    }

    public void setPmin(String pmin) {
        this.pmin = pmin;
    }

    public String getPmax() {
        return pmax;
    }

    public void setPmax(String pmax) {
        this.pmax = pmax;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void incrementPage(){
        page++;
    }

    public void resetPager(){
        page=1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Buscar{" +
                "titulo='" + titulo + '\'' +
                ", tipos_ofertas='" + tipos_ofertas + '\'' +
                ", tipos_inmuebles='" + tipos_inmuebles + '\'' +
                ", departamento='" + departamento + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", banios='" + banios + '\'' +
                ", cuartos='" + cuartos + '\'' +
                ", area='" + area + '\'' +
                ", superficie='" + superficie + '\'' +
                ", pmin='" + pmin + '\'' +
                ", pmax='" + pmax + '\'' +
                ", page=" + page +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
