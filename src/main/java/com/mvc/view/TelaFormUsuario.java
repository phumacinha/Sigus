/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.CargoController;
import com.mvc.controller.PerfilController;
import com.mvc.controller.PerfilUsuarioController;
import com.mvc.controller.UsuarioController;
import com.mvc.model.Cargo;
import com.mvc.model.Perfil;
import com.mvc.model.Usuario;
import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.Campo;
import com.mvc.view.util.Formulario.CampoCheckBox;
import com.mvc.view.util.Formulario.CampoComboBox;
import com.mvc.view.util.Formulario.CampoCpf;
import com.mvc.view.util.Formulario.CampoData;
import com.mvc.view.util.Formulario.CampoTexto;
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
    private Usuario usuario = null;
    private String acao;
    
    public TelaFormUsuario (Usuario usuario) {
        super("Editar usuário");
        this.usuario = usuario;
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
        
        CampoTexto nome = formulario.novoCampoTexto("Nome completo*");
        if (usuario != null) { nome.setValue(usuario.getNome()); }
        formulario.addCampo(nome);
        
        CampoTexto cpf = formulario.novoCampoTexto("CPF*");
        if (usuario != null) { cpf.setValue(usuario.getCpf()); }
        formulario.addCampo(cpf);
        
        CampoData dataNascimento = formulario.novoCampoData("Data de Nascimento");
        if (usuario != null) { dataNascimento.setValue(usuario.getDataNascimento()); }
        formulario.addCampo(dataNascimento);
        
        CampoComboBox sexo = formulario.novoCampoComboBox("Sexo", "Prefiro não informar");
        sexo.addItem("M");
        sexo.addItem("F");
        if (usuario != null) { sexo.setValue(usuario.getSexo()); }
        formulario.addCampo(sexo);        
        
        CampoComboBox campoCargo = formulario.novoCampoComboBox("Cargo*", "Selecione um cargo");
        ArrayList<Cargo> cargos = new CargoController().retornaTodos();
        cargos.forEach(cargo -> {
            campoCargo.addItem(cargo.getNome());
        });
        if (usuario != null) { campoCargo.setValue(usuario.getCargo().getNome()); }
        formulario.addCampo(campoCargo);
        
        CampoCheckBox campoPerfil = formulario.novoCampoCheckBox("Perfis", "Ainda não há perfis cadastrados.");
        ArrayList<Perfil> perfis = new PerfilController().retornaTodos();
        ArrayList<Perfil> perfisUsuario = new ArrayList<>();
        ArrayList<String> relacoes = new ArrayList<>();
         
        if (usuario != null) {
            perfisUsuario = new PerfilUsuarioController().retornarPerfisUsuario(usuario);
            perfisUsuario.forEach(perfil -> {
                relacoes.add(perfil.getNome());
            });
        
        }
        
        for (Perfil perfil : perfis) {
            campoPerfil.addOpcao(perfil.getNome(), relacoes.contains(perfil.getNome()));
            
        }
        
        formulario.addCampo(campoPerfil);
        
        
        Botao cadastrar = new Botao("Cadastrar", 15, 120, 40);
        cadastrar.setPreenchido();
        cadastrar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                String valNome = nome.getValue();
                String valCpf = cpf.getValue();
                String valDataNascimento = dataNascimento.getValue();
                String valSexo = sexo.getValue();
                Cargo valCargo = new Cargo(campoCargo.getValue()); 
                ArrayList<String> valPerfisAux = campoPerfil.getValue();
                ArrayList<Perfil> valPerfis = new ArrayList<>();
                
                if (!valPerfisAux.isEmpty()) {
                    valPerfisAux.forEach(perfil -> {
                        valPerfis.add(new Perfil(perfil));
                    });
                }
                
                System.out.println(valNome);
                System.out.println(valCpf);
                System.out.println(valDataNascimento);
                System.out.println(valSexo);
                System.out.println(valCargo);
                System.out.println(valPerfis);
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
