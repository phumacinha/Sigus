/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.CargoController;
import com.mvc.model.Cargo;
import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.CampoTexto;
import com.mvc.view.util.Logotipo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 *
 * @author pedro
 */
public class TelaFormCargo extends TelaUtil {
    private Cargo cargo = null;
    private final String acao;
    
    public TelaFormCargo(Cargo cargo) {
        super("Editar cargo");
        this.cargo = cargo;
        acao = "Editar cargo";
        setTamanhoExato();
    }
    
    public TelaFormCargo() {
        super("Adicionar novo cargo");
        acao = "Adicionar novo cargo";
        setTamanhoExato();
    }

    @Override
    protected Node conteudoInicial() {
        Logotipo logotipo = new Logotipo(18);
        BackgroundFill bg = new BackgroundFill(Atributo.ROXO_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
        logotipo.setBackground(new Background(bg));
        logotipo.setMinHeight(40);
        logotipo.setMaxHeight(40);
        
        Formulario formulario = new Formulario(acao);
        
        CampoTexto nome = formulario.novoCampoTexto("Nome do cargo");
        if (cargo != null) {nome.setValue(cargo.getNome()); }
        formulario.addCampo(nome);
        
        Botao confirmar = new Botao("Confirmar", 15, 120, 40);
        confirmar.setPreenchido();
        confirmar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String valNome = nome.getValue() == null ? "" : (String) nome.getValue();
                if (cargo != null) alterarCargo(cargo.getNome(), valNome);
                else cadastrarCargo(valNome);
            }
        });
        
        Botao cancelar = new Botao("Cancelar", 15, 120, 40);
        cancelar.setCorPreenchimento(Color.RED);
        cancelar.setLightStyle(false);
        cancelar.desativarBorda();
        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                getStage().close();
            }
        });
        
        formulario.addRodape(cancelar, confirmar);
        
        VBox conteudo = new VBox(formulario);
        conteudo.setPadding(new Insets(0, 40, 40, 40));
        conteudo.setSpacing(20);
        
        
        VBox tela = new VBox(logotipo, conteudo);
        tela.setAlignment(Pos.TOP_CENTER);
        
        return tela;
    }
    
    private void cadastrarCargo (String nome) {
        boolean sucesso;
        
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setHeaderText(null);
        alerta.setResult(ButtonType.CLOSE);
        
        try {
            CargoController cargoController = new CargoController();
            sucesso = cargoController.cadastrar(nome);
                
            if (sucesso) {
                alerta.setAlertType(Alert.AlertType.INFORMATION);
                alerta.setTitle("Cargo cadastrado");
                alerta.setContentText("Cargo cadastrado com sucesso.");
                alerta.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        getStage().close();
                        App.abrirTela(new TelaGerenciarCargos());
                    }
                    
                });
            }
            else {
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao cadastrar o cargo");
                alerta.setHeaderText("Erro ao cadastrar o cargo.");
                alerta.setContentText("Verifique o nome e tente novamente.");
            }
            
            
        }
        catch (Exception ex) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao cadastrar o cargo");
            alerta.setHeaderText(null);
            alerta.setContentText("Houve um erro interno. Por favor, tente novamente.");
        }
        
        alerta.showAndWait();
    }
    
    private void alterarCargo (String nomeAntigo, String nomeNovo) {
        boolean sucesso;
        
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setHeaderText(null);
        alerta.setResult(ButtonType.CLOSE);
        
        try {
            CargoController cargoController = new CargoController();
            sucesso = cargoController.alterar(nomeAntigo, nomeNovo);
                
            if (sucesso) {
                alerta.setAlertType(Alert.AlertType.INFORMATION);
                alerta.setTitle("Cargo alterado");
                alerta.setContentText("Cargo alterado com sucesso.");
                alerta.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        getStage().close();
                        App.abrirTela(new TelaGerenciarCargos());
                    }
                    
                });
            }
            else {
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao alterar o cargo");
                alerta.setHeaderText("Erro ao alterar o cargo.");
                alerta.setContentText("Verifique o nome e tente novamente.");
            }
            
            
        }
        catch (Exception ex) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao alterar o cargo");
            alerta.setHeaderText(null);
            alerta.setContentText("Houve um erro interno. Por favor, tente novamente.");
        }
        
        alerta.showAndWait();
    }
    
}
