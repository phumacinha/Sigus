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
    
    public Cargo (String nome) {
        this.nome = nome;
    }
    
    public String getNome () {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void cadastrar () throws ExceptionDAO {
        new CargoDAO().cadastrar(this);
    }
    
    public void remover () throws ExceptionDAO {
        new CargoDAO().remover(this);
    }
    
    public void alterar (Cargo novo) throws ExceptionDAO {
        new CargoDAO().alterar(this, novo);
    }
}
