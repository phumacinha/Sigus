/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.dao.ExceptionDAO;
import com.dao.PerfilDAO;
import com.mvc.model.Perfil;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PerfilController {
    public boolean cadastrar (String nome) {
        if (nome != null && nome.length() > 0) {
            try {
                Perfil cargo = new Perfil(nome);
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
                Perfil cargo = new Perfil(nome);
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
                Perfil antigo = new Perfil(nomeAntigo);
                Perfil novo = new Perfil(nomeNovo);
                antigo.alterar(novo);
                return true;
            } catch (ExceptionDAO ex) {
               return false;
            }
        } 
        
        return false;
    }
    
    public int quantidadeTotal () {
        try {
            return new PerfilDAO().quantidadeTotal();
        } catch (ExceptionDAO ex) {
            return 0;
        }
    }
    
    public ArrayList<Perfil> retornaTodos () {
        try {
            return new PerfilDAO().retornaTodos();
        } catch (ExceptionDAO ex) {
            return null;
        }
    }
}
