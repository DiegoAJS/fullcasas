package com.developerdj.fullcasa.modelo;

/**
 * Created by hp on 30/1/2017.
 */

public class Usuario {

    private String usename;
    private String password;
    private String email;
    private int idEmpresa;

    public Usuario() {
    }
    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

}
