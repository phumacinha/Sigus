/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.OpcoesTabela;
import com.estagiozetta.mvc.view.util.Tabela;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author pedro
 */
public class TelaGerenciarUsuarios extends TelaGerenciarAbstrata {
    public TelaGerenciarUsuarios () {
        super("Gerenciar Usuários", "usuario");
    }

    @Override
    protected Tabela getDadosCadastrados() {
        Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            
            tabela.addColuna(cons, "CARGO");
            
            cons = new ColumnConstraints();
            cons.setHalignment(HPos.CENTER);
            tabela.addColuna(new ColumnConstraints(), "OPCOES");
            
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            
            OpcoesTabela op = new OpcoesTabela();
            op.naoRemovivel();
            tabela.addLinha(tabela.novaCelula("Professor"), tabela.novaCelula(op));
            
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
        return tabela;
    }
    
    @Override
    protected TelaUtil getTelaAdicionar() {
        return new TelaFormUsuario();
    }
    
}
