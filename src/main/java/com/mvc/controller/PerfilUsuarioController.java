/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.dao.ExceptionDAO;
import com.dao.PerfilUsuarioDAO;
import com.mvc.model.Perfil;
import com.mvc.model.PerfilUsuario;
import com.mvc.model.Usuario;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PerfilUsuarioController {
    public boolean relacionar(String cpf, String nomePerfil) {
        if (cpf != null && cpf.length() > 0
            && nomePerfil != null && nomePerfil.length() > 0) {
            try {
                Perfil perfil = new Perfil(nomePerfil);
                Usuario usuario = new UsuarioController().selecionarUsuarioPorCpf(cpf);
                if (usuario != null) {
                    PerfilUsuario relacao = new PerfilUsuario(perfil, usuario);
                    relacao.salvarRelacionamento();
                    return true;
                }
            }
            catch (ExceptionDAO ex) {
                return false;
            }
        }
        return false;
    }
    
    public ArrayList<Perfil> retornarPerfisUsuario (Usuario usuario) {
        try {
            return new PerfilUsuarioDAO().retornarPerfisUsuario(usuario);
        } catch (ExceptionDAO ex) {
            return null;
        }
    }
}
