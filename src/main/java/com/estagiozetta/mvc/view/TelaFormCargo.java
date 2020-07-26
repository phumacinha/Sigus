/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.Atributo;
import com.estagiozetta.mvc.view.util.Atributo.Estilo;
import com.estagiozetta.mvc.view.util.Botao;
import com.estagiozetta.mvc.view.util.CampoTexto;
import com.estagiozetta.mvc.view.util.Formulario;
import com.estagiozetta.mvc.view.util.Logotipo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author pedro
 */
public class TelaFormCargo extends TelaUtil {
    private Integer idCargo = null;
    private String acao;
    
    public TelaFormCargo(int id) {
        super("Editar cargo");
        idCargo = id;
        acao = "Editar cargo";
    }
    
    public TelaFormCargo() {
        super("Adicionar novo cargo", 600, 600);
        acao = "Adicionar novo cargo";
    }

    @Override
    protected Node conteudoInicial() {
        Logotipo logotipo = new Logotipo(18);
        BackgroundFill bg = new BackgroundFill(Atributo.ROXO_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
        logotipo.setBackground(new Background(bg));
        logotipo.setMinHeight(40);
        logotipo.setMaxHeight(40);
        
        Formulario formulario = new Formulario(acao);
        
        CampoTexto nomeCargo = new CampoTexto("Nome do cargo:");
        formulario.addCampo(nomeCargo);
        
        Botao teste = new Botao("Cadastrar", 10, 50, 60);
        teste.setCorPreenchimento(Color.RED);
        formulario.getChildren().add(teste);
        
        VBox conteudo = new VBox(formulario);
        conteudo.setPadding(new Insets(0, 40, 40, 40));
        conteudo.setSpacing(20);
        
        
        VBox tela = new VBox(logotipo, conteudo);
        tela.setAlignment(Pos.TOP_CENTER);
        
        return tela;
    }
    
}
