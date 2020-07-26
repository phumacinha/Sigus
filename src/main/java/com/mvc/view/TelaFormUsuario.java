/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.Campo;
import com.mvc.view.util.Formulario.CampoCheckBox;
import com.mvc.view.util.Formulario.CampoComboBox;
import com.mvc.view.util.Logotipo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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
        
        CampoComboBox sexo = formulario.novoCampoComboBox("Sexo");
        sexo.addItem("Prefiro não informar");
        sexo.addItem("M");
        sexo.addItem("F");
        formulario.addCampo(sexo);
        
        CampoCheckBox perfis = formulario.novoCampoCheckBox("Perfis", "Ainda não há perfis cadastrados.");
        formulario.addCampo(perfis);
        
        
        Botao cadastrar = new Botao("Cadastrar", 15, 120, 40);
        cadastrar.setPreenchido();
        
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
    
}
