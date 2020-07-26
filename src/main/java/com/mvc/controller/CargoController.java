/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.dao.ExceptionDAO;
import com.mvc.model.Cargo;
import com.mvc.model.Usuario;

/**
 *
 * @author pedro
 */
public class CargoController {
    public boolean cadastrar (String nome, Usuario usuario) {
        if (nome != null && nome.length() > 0) {
            try {
                Cargo cargo = new Cargo(nome, usuario);
                cargo.cadastrar(cargo);
                return true;
            } catch (ExceptionDAO ex) {
               return false;
            }
        } 
        
        return false;
    }
}
