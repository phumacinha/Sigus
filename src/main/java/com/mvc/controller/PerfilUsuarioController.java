/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.mvc.model.Perfil;
import com.mvc.model.PerfilUsuario;
import com.mvc.model.Usuario;

/**
 *
 * @author pedro
 */
public class PerfilUsuarioController {
    public boolean relacionarPerfilUsuario(String perfil, String usuario) {
        PerfilUsuario relacaoPerfilUsuario = new PerfilUsuario(perfil, usuario);
        return relacaoPerfilUsuario.relacionar();
    }
}
