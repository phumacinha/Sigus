/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.Tabela;

/**
 *
 * @author pedro
 */
public class TelaGerenciarCargos extends TelaGerenciarAbstrata {
    public TelaGerenciarCargos () {
        super("Gerenciar Cargos", "cargo");
    }

    @Override
    protected Tabela getDadosCadastrados() {
        return null;
    }

    @Override
    protected TelaUtil getTelaAdicionar() {
        return new TelaFormCargo();
    }
    
    
}
