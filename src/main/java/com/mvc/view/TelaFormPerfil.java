/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.PerfilController;
import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.Campo;
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
public class TelaFormPerfil extends TelaUtil {
    private Integer idPerfil = null;
    private String acao;
    
    public TelaFormPerfil(int id) {
        super("Editar perfil");
        idPerfil = id;
        acao = "Editar perfil";
        setTamanhoExato();
    }
    
    public TelaFormPerfil() {
        super("Cadastrar perfil");
        acao = "Cadastrar perfil";
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
        
        Campo nome = formulario.novoCampoTexto("Nome do perfil");
        formulario.addCampo(nome);
        
        Botao cadastrar = new Botao("Cadastrar", 15, 120, 40);
        cadastrar.setPreenchido();
        cadastrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String valNome = nome.getValue() == null ? null : (String) nome.getValue();
                cadastrarPerfil(valNome);
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
        
        formulario.addRodape(cancelar, cadastrar);
        
        VBox conteudo = new VBox(formulario);
        conteudo.setPadding(new Insets(0, 40, 40, 40));
        conteudo.setSpacing(20);
        
        
        VBox tela = new VBox(logotipo, conteudo);
        tela.setAlignment(Pos.TOP_CENTER);
        
        return tela;
    }
    
    private void cadastrarPerfil (String nome) {
        boolean sucesso;
        
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setHeaderText(null);
        alerta.setResult(ButtonType.CLOSE);
        
        try {
            PerfilController perfilController = new PerfilController();
            sucesso = perfilController.cadastrarPerfil(nome);
                
            if (sucesso) {
                alerta.setAlertType(Alert.AlertType.INFORMATION);
                alerta.setTitle("Perfil cadastrado");
                alerta.setContentText("Perfil cadastrado com sucesso.");
                alerta.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        getStage().close();
                        App.abrirTela(new TelaGerenciarPerfis());
                    }
                    
                });
            }
            else {
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao cadastrar o perfil");
                alerta.setHeaderText("Erro ao cadastrar o perfil.");
                alerta.setContentText("Verifique o nome e tente novamente.");
            }
            
            
        }
        catch (Exception ex) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao cadastrar o perfil");
            alerta.setHeaderText(null);
            alerta.setContentText("Houve um erro interno. Por favor, tente novamente.");
        }
        
        alerta.showAndWait();
    }
    
}
