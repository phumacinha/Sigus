/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.UsuarioController;
import com.mvc.model.Usuario;
import com.mvc.view.util.OpcoesTabela;
import com.mvc.view.util.Tabela;
import com.mvc.view.util.Tabela.Celula;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author pedro
 */
public class TelaGerenciarUsuarios extends TelaGerenciarAbstrata {
    public TelaGerenciarUsuarios () {
        super("Gerenciar Usuários", "usuário");
    }

    @Override
    protected Tabela getDadosCadastrados() {
        ArrayList<Usuario> usuarios = new UsuarioController().retornaTodos();
        
        if (usuarios == null || usuarios.isEmpty()){
            System.out.println("oi");
            return null;
        }
        else {
            Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            tabela.addColuna(cons, "NOME");
            
            tabela.addColuna(new ColumnConstraints(), "CPF");
            
            tabela.addColuna(new ColumnConstraints(), "DATA NASCIMENTO");
            
            tabela.addColuna(new ColumnConstraints(), "SEXO");
            
            cons = new ColumnConstraints();
            cons.setHgrow(Priority.SOMETIMES);
            tabela.addColuna(cons, "CARGO");
            
            tabela.addColuna(new ColumnConstraints(), "OPÇÃO");
            
            usuarios.forEach(usuario -> {
                OpcoesTabela opcoes = new OpcoesTabela();
                opcoes.naoRemovivel();
                opcoes.setOnClickEditar(new TelaFormUsuario(usuario));
                
                Celula nome = tabela.novaCelula(usuario.getNome());
                Celula cpf = tabela.novaCelula(usuario.getCpf());
                Celula dataNascimento = tabela.novaCelula(usuario.getDataNascimento());
                dataNascimento.setAlignment(Pos.CENTER);
                Celula sexo = tabela.novaCelula(usuario.getSexo());
                sexo.setAlignment(Pos.CENTER);
                Celula cargo = tabela.novaCelula(usuario.getCargo().getNome());
                tabela.addLinha(nome, cpf, dataNascimento, sexo, cargo, tabela.novaCelula(opcoes));
            });
            
            return tabela;
        }
    }
    
    @Override
    protected TelaUtil getTelaAdicionar() {
        return new TelaFormUsuario();
    }
    
}
