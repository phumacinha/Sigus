/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import com.estagiozetta.mvc.view.TelaGerenciarCargos;
import com.estagiozetta.mvc.view.TelaGerenciarUsuarios;
import com.estagiozetta.mvc.view.TelaInicial;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author pedro
 */
public class Navbar extends HBox {
    public Navbar() {
        super();
        
        Logotipo logo = new Logotipo(24);
        logo.setPadding(new Insets(0, 10, 0, 0));
        HBox.setHgrow(logo, Priority.ALWAYS);
        
        Botao btnCargos = new Botao("Gerenciar Cargos", 12, 180, 40);
        btnCargos.setOnMouseClicked(new TelaGerenciarCargos());
        
        Botao btnPerfis = new Botao("Gerenciar Perfis", 12, 180, 40);
        
        Botao btnUsuarios = new Botao("Gerenciar Usuários", 12, 180, 40);
        btnUsuarios.setOnMouseClicked(new TelaGerenciarUsuarios());
        
        Botao btnVoltar = new Botao("Voltar para o início", 12, 180, 40);
        btnVoltar.setOnMouseClicked(new TelaInicial());
        
        BackgroundFill bg = new BackgroundFill(Atributo.ROXO_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
        setBackground(new Background(bg));
        setAlignment(Pos.CENTER);
        setPrefHeight(64);
        setSpacing(30);
        setPadding(new Insets(0, 40, 0, 40));
        getChildren().addAll(logo, btnCargos, btnPerfis, btnUsuarios, btnVoltar);
        VBox.setVgrow(this, Priority.NEVER);
    }
}
