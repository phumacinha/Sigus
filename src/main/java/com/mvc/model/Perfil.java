/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.model;

import com.dao.ExceptionDAO;
import com.dao.PerfilDAO;
import java.util.ArrayList;

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
    
    public void cadastrar () throws ExceptionDAO {
        new PerfilDAO().cadastrar(this);
    }
    
    public void remover () throws ExceptionDAO {
        new PerfilDAO().remover(this);
    }
    
    public void alterar (Perfil novo) throws ExceptionDAO {
        new PerfilDAO().alterar(this, novo);
    }
}
