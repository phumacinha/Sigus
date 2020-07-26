/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.dao.CargoDAO;
import com.dao.ExceptionDAO;
import com.mvc.model.Cargo;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class CargoController {
    public boolean cadastrar (String nome) {
        if (nome != null && nome.length() > 0) {
            try {
                Cargo cargo = new Cargo(nome);
                cargo.cadastrar();
                return true;
            } catch (ExceptionDAO ex) {
               return false;
            }
        } 
        
        return false;
    }
    
    public boolean remover (String nome) {
        if (nome != null && nome.length() > 0) {
            try {
                Cargo cargo = new Cargo(nome);
                cargo.remover();
                return true;
            } catch (ExceptionDAO ex) {
               return false;
            }
        } 
        
        return false;
    }
    
    public boolean alterar (String nomeAntigo, String nomeNovo) {
        if (nomeAntigo != null && nomeAntigo.length() > 0
            && nomeNovo != null && nomeNovo.length() > 0) {
            try {
                Cargo antigo = new Cargo(nomeAntigo);
                Cargo novo = new Cargo(nomeNovo);
                antigo.alterar(novo);
                return true;
            } catch (ExceptionDAO ex) {
               return false;
            }
        } 
        
        return false;
    }
    
    public int quantidadeCargos () {
        try {
            return new CargoDAO().quantidadeCargos();
        } catch (ExceptionDAO ex) {
            return 0;
        }
    }
    
    public ArrayList<Cargo> retornaCargos () {
        try {
            return new CargoDAO().retornaCargos();
        } catch (ExceptionDAO ex) {
            return null;
        }
    }
    
    
}
