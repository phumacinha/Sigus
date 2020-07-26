/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.model;

/**
 *
 * @author pedro
 */
public class PerfilUsuario {
    private String perfil;
    private String usuario;
    
    public PerfilUsuario(String perfil, String usuario) {
        this.perfil = perfil;
        this.usuario = usuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean relacionar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
