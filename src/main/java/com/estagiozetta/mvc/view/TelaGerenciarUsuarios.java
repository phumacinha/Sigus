/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.Navbar;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author pedro
 */
public class TelaGerenciarUsuarios extends TelaUtil {
    public TelaGerenciarUsuarios () {
        super("Gerenciar Usuários | Sigus");
    }
    
    @Override
    protected Node conteudoInicial() {
        Navbar navbar = new Navbar();
        
        VBox conteudo = new VBox(navbar);
        return conteudo;
    }
    
}
