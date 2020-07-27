/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.CargoController;
import com.mvc.model.Cargo;
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
public class TelaGerenciarCargos extends TelaGerenciarAbstrata {
    public TelaGerenciarCargos () {
        super("Gerenciar Cargos", "cargo");
    }

    @Override
    protected Tabela getDadosCadastrados() {
        ArrayList<Cargo> cargos = new CargoController().retornaTodos();
        
        if (cargos == null || cargos.isEmpty()) {
            return null;
        }
        else {
            Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            
            tabela.addColuna(cons, "CARGO");
            
            cons = new ColumnConstraints();
            cons.setHalignment(HPos.CENTER);
            tabela.addColuna(new ColumnConstraints(), "OPÇÕES");
            
            cargos.forEach(cargo -> {
                OpcoesTabela opcoes = new OpcoesTabela();
                opcoes.setOnClickEditar(new TelaFormCargo(cargo));
                
                EventHandler acaoRemover = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        boolean sucesso;
                        
                        Alert alerta = new Alert(Alert.AlertType.NONE);
                        alerta.initStyle(StageStyle.UTILITY);
                        alerta.setHeaderText(null);
                        alerta.setResult(ButtonType.CLOSE);
                        
                        try {
                            CargoController cargoController = new CargoController();
                            sucesso = cargoController.remover(cargo.getNome());
                            if (sucesso) {
                                App.abrirTela(new TelaGerenciarCargos());
                                alerta.setAlertType(Alert.AlertType.INFORMATION);
                                alerta.setTitle("Cargo removido");
                                alerta.setContentText("Cargo removido com sucesso.");
                            }
                            else {
                                alerta.setAlertType(Alert.AlertType.ERROR);
                                alerta.setTitle("Erro ao remover o cargo");
                                alerta.setHeaderText("Não é possível remover esse cargo.");
                                alerta.setContentText("Esse cargo está vinculado a algum usuário.");
                            }
                        } catch (Exception ex) {
                            alerta.setAlertType(Alert.AlertType.ERROR);
                            alerta.setTitle("Erro");
                            alerta.setHeaderText(null);
                            alerta.setContentText("Não foi possível remover esse cargo. Tente novamente.");
                        }
                        alerta.showAndWait();
                    }
                
                };
                opcoes.setOnClickRemover(acaoRemover);
                tabela.addLinha(tabela.novaCelula(cargo.getNome()), tabela.novaCelula(opcoes));
            });
            
            return tabela;
        }
    }

    @Override
    protected TelaUtil getTelaAdicionar() {
        return new TelaFormCargo();
    }
    
    
}
