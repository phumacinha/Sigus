/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.UsuarioController;
import com.mvc.model.Cargo;
import com.mvc.model.Perfil;
import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.Campo;
import com.mvc.view.util.Formulario.CampoCheckBox;
import com.mvc.view.util.Formulario.CampoComboBox;
import com.mvc.view.util.Logotipo;
import java.util.ArrayList;
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
public class TelaFormUsuario extends TelaUtil {
    private Integer idUsuario = null;
    private String acao;
    
    public TelaFormUsuario (int id) {
        super("Editar usuário");
        idUsuario = id;
        acao = "Editar usuário";
        setTamanhoExato();
    }
    
    public TelaFormUsuario() {
        super("Cadastrar usuário");
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
        
        Campo nome = formulario.novoCampoTexto("Nome completo*");
        formulario.addCampo(nome);
        
        Campo cpf = formulario.novoCampoCpf("CPF*");
        formulario.addCampo(cpf);
        
        Campo dataNascimento = formulario.novoCampoData("Data de Nascimento");
        formulario.addCampo(dataNascimento);
        
        CampoComboBox sexo = formulario.novoCampoComboBox("Sexo", "Prefiro não informar");
        sexo.addItem("M");
        sexo.addItem("F");
        formulario.addCampo(sexo);        
        
        CampoComboBox cargo = formulario.novoCampoComboBox("Cargo*", "Selecione um cargo");
        cargo.addItem("Cargo 1");
        cargo.addItem("Cargo 2");
        cargo.addItem("Cargo 3");
        formulario.addCampo(cargo);
        
        CampoCheckBox perfis = formulario.novoCampoCheckBox("Perfis", "Ainda não há perfis cadastrados.");
        perfis.addOpcao("Teste");
        perfis.addOpcao("Teste 2");
        formulario.addCampo(perfis);
        
        
        Botao cadastrar = new Botao("Cadastrar", 15, 120, 40);
        cadastrar.setPreenchido();
        cadastrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String valNome = nome.getValue() == null ? null : (String) nome.getValue();
                String valCpf = cpf.getValue() == null ? null : (String) cpf.getValue();
                String valDataNascimento = dataNascimento.getValue() == null ? null : (String) dataNascimento.getValue();
                String valSexo = sexo.getValue() == null ? null : sexo.getValue();
                Cargo valCargo = cargo.getValue() == null ? null : new Cargo(cargo.getValue()); 
                ArrayList<String> valPerfisAux = perfis.getValue() == null ? null : (ArrayList<String>) perfis.getValue();
                ArrayList<Perfil> valPerfis = valPerfisAux == null ? null : new ArrayList<>();
                
                if (valPerfisAux != null && valPerfis != null) {
                    valPerfisAux.forEach(perfil -> {
                        valPerfis.add(new Perfil(perfil));
                    });
                }
                
                cadastrarUsuario(valNome, valCpf, valDataNascimento, valSexo, valCargo, valPerfis);
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
    
    private void cadastrarUsuario (String nome, String cpf, String dataNascimento, String sexo, Cargo cargo, ArrayList<Perfil> perfis) {
        boolean sucesso = false;
        
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.setHeaderText(null);
        alerta.setResult(ButtonType.CLOSE);
        
        try {
            UsuarioController cargoController = new UsuarioController();
            sucesso = cargoController.cadastrar(nome, cpf, dataNascimento, sexo, cargo, perfis);
                
            if (sucesso) {
                alerta.setAlertType(Alert.AlertType.INFORMATION);
                alerta.setTitle("Usuário cadastrado");
                alerta.setContentText("Usuário cadastrado com sucesso.");
                alerta.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent t) {
                        getStage().close();
                        App.abrirTela(new TelaGerenciarUsuarios());
                    }
                    
                });
            }
            else {
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setTitle("Erro ao cadastrar o usuário");
                alerta.setHeaderText("Erro ao cadastrar o usuário.");
                alerta.setContentText("Verifique os campos e tente novamente.");
            }
            
            
        }
        catch (Exception ex) {
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setTitle("Erro ao cadastrar o usuário");
            alerta.setHeaderText(null);
            alerta.setContentText("Houve um erro interno. Por favor, tente novamente.");
        }
        
        alerta.showAndWait();
    }
    
}
