/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.mvc.model.Perfil;
import com.mvc.model.Usuario;

/**
 *
 * @author pedro
 */
public class PerfilController {
    public boolean cadastrar (String nome, Usuario usuario) {
        if (nome != null && nome.length() > 0) {
            Perfil perfil = new Perfil(nome, usuario);
            perfil.cadastrar();
            return true;
        } 
        
        return false;
    }
}