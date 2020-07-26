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
    
    public Perfil (String nome) {
        this.nome = nome;
    }
    
    public String getNome () {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void cadastrar () {
        
    }
}
