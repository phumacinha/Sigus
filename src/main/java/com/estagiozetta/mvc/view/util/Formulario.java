/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import com.estagiozetta.mvc.view.util.Atributo.Estilo;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author pedro
 */
public class Formulario extends VBox {
    public Formulario (String titulo) {
        super();
        
        Label labelAcao = new Label(titulo);
        labelAcao.setFont(Atributo.getFonteRussoOne(30));
        labelAcao.setTextFill(Atributo.ROXO_PRINCIPAL);
        labelAcao.setPadding(new Insets(20, 0, 20, 0));
        StackPane acao = new StackPane(labelAcao);
        acao.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Atributo.CINZA_MEDIO, Color.TRANSPARENT,
            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        
        setSpacing(20);
        getChildren().add(acao);
    }
    
    public void addCampo (CampoTexto campo) {
        getChildren().add(campo);
    }
    
    public void addRodape (Botao... botoes) {
        HBox rodape = new HBox(botoes);
        rodape.setPadding(new Insets(20, 0, 0, 0));
        rodape.setBorder(new Border(new BorderStroke(Atributo.CINZA_MEDIO, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT,
            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        rodape.setSpacing(10);
        
        getChildren().add(rodape);
    }
    
    public CampoTexto novoCampo (String nome) {
        return new CampoTexto(nome);
    }
    
    
    public class CampoTexto extends VBox {
        private final TextField campo;

        public CampoTexto (String nome) {
            super();

            Label label = new Label(nome);
            label.setFont(Atributo.getUbuntu(16, Estilo.BOLD));
            label.setTextFill(Atributo.ROXO_PRINCIPAL);

            campo = new TextField();
            campo.setPrefHeight(40);
            campo.setFont(Atributo.getUbuntu(14));

            setSpacing(5);
            getChildren().addAll(label, campo);
        }

        public String getText () {
            return campo.getText();
        }

        public void setText (String texto) {
            campo.setText(texto);
        }
    }

}
