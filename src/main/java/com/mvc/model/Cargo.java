/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.model;

import com.dao.CargoDAO;
import com.dao.ExceptionDAO;

/**
 *
 * @author pedro
 */
public class Cargo {
    private String nome;
    private Usuario usuario;
    
    public Cargo (String nome, Usuario usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }
    
    public Cargo (String nome) {
        this.nome = nome;
        this.usuario = null;
    }
    
    public String getNome () {
        return nome;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getCpfUsuario () {
        return usuario == null ? null : usuario.getCpf();
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void cadastrar (Cargo cargo) throws ExceptionDAO {
        new CargoDAO().cadastrar(cargo);
    }
}
