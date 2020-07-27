/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.PerfilController;
import com.mvc.model.Perfil;
import com.mvc.view.util.OpcoesTabela;
import com.mvc.view.util.Tabela;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

/**
 *
 * @author pedro
 */
public class TelaGerenciarPerfis extends TelaGerenciarAbstrata {
    public TelaGerenciarPerfis () {
        super("Gerenciar Perfis", "perfil");
    }

    @Override
    protected Tabela getDadosCadastrados() {
        ArrayList<Perfil> perfis = new PerfilController().retornaTodos();
        
        if (perfis == null || perfis.isEmpty()) {
            return null;
        }
        else {
            Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            
            tabela.addColuna(cons, "PERFIL");
            
            cons = new ColumnConstraints();
            cons.setHalignment(HPos.CENTER);
            tabela.addColuna(new ColumnConstraints(), "OPÇÕES");
            
            perfis.forEach(perfil -> {
                OpcoesTabela opcoes = new OpcoesTabela();
                opcoes.setOnClickEditar(new TelaFormPerfil(perfil));
                
                EventHandler acaoRemover = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        boolean sucesso;
                        
                        Alert alerta = new Alert(Alert.AlertType.NONE);
                        alerta.initStyle(StageStyle.UTILITY);
                        alerta.setHeaderText(null);
                        alerta.setResult(ButtonType.CLOSE);
                        
                        try {
                            PerfilController perfilController = new PerfilController();
                            sucesso = perfilController.remover(perfil.getNome());
                            if (sucesso) {
                                App.abrirTela(new TelaGerenciarPerfis());
                                alerta.setAlertType(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Perfil removido");
                                alerta.setContentText("Perfil removido com sucesso.");
                            }
                            else {
                                alerta.setAlertType(Alert.AlertType.ERROR);
                                alerta.setTitle("Erro ao remover o prefil");
                                alerta.setHeaderText("Não é possível remover esse prefil.");
                                alerta.setContentText("Esse prefil está vinculado a algum usuário.");
                            }
                        } catch (Exception ex) {
                            alerta.setAlertType(Alert.AlertType.ERROR);
                            alerta.setTitle("Erro");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Não foi possível remover esse prefil. Tente novamente.");
                        }
                        alerta.showAndWait();
                    }
                
                };
                opcoes.setOnClickRemover(acaoRemover);
                tabela.addLinha(tabela.novaCelula(perfil.getNome()), tabela.novaCelula(opcoes));
            });
            
            return tabela;
        }
    }

    @Override
    protected TelaUtil getTelaAdicionar() {
        return new TelaFormPerfil();
    }
}
