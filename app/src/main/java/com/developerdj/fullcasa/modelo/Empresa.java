package com.developerdj.fullcasa.modelo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 25/1/2017.
 */

public class Empresa extends Item {

    /*private int id;
    private String nombre;
    //private String ubicacion;
    private String direccion;
    private String telefono="";
    private String celular="";
    private String email;
    private String logo;

    private String pagina;
    private String facebook;
    private String twitter;
    private String google;
    private String youtube;*/


    @SerializedName("id")
    private int id;
    @SerializedName("idEmpresa")
    private int idEmpresa;

    private String razon_social;
    private String telefono;
    private String celular;
    private String representante;
    private String logo;
    private String direccion;
    private String email;
    private String fax;
    private String facebook;
    private String twitter;
    private String youtube;
    private String googleplus;
    private String departamento;
    private String ciudad;

    public Empresa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getGoogleplus() {
        return googleplus;
    }

    public void setGoogleplus(String googleplus) {
        this.googleplus = googleplus;
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

    public String getTelefonos(){
        String s = " ";
        if (telefono.length()>0)
            s+="Cel:" + telefono+ " y";
        if (celular.length()>0)
            s+=" Tel:"+celular;
        else if(s.charAt(s.length()-1)=='y')
            s=s.substring(1,s.length()-1);
        return s;
    }
    public void updateId(){
        if (idEmpresa==0)
            this.idEmpresa=this.id;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", idEmpresa=" + idEmpresa +
                ", razon_social='" + razon_social + '\'' +
                ", telefono='" + telefono + '\'' +
                ", celular='" + celular + '\'' +
                ", representante='" + representante + '\'' +
                ", logo='" + logo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", fax='" + fax + '\'' +
                ", facebook='" + facebook + '\'' +
                ", twitter='" + twitter + '\'' +
                ", youtube='" + youtube + '\'' +
                ", googleplus='" + googleplus + '\'' +
                ", departamento='" + departamento + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
