/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.model;

import com.dao.ExceptionDAO;
import com.dao.PerfilUsuarioDAO;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PerfilUsuario {
    private Perfil perfil;
    private Usuario usuario;
    
    public PerfilUsuario(Perfil perfil, Usuario usuario) {
        this.perfil = perfil;
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void salvarRelacionamento() throws ExceptionDAO {
        new PerfilUsuarioDAO().salvarRelacionamento(usuario, perfil);
    }    
}
