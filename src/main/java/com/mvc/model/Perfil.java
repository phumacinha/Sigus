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
public class Perfil {
    private String nome;
    private Usuario usuario;
    
    public Perfil (String nome, Usuario usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }
    
    public Perfil (String nome) {
        this.nome = nome;
        this.usuario = null;
    }
    
    public String getNome () {
        return nome;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void cadastrar () {
        
    }
}
