/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view.util;

import com.mvc.view.util.Atributo;
import com.mvc.view.util.Atributo.Estilo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author pedro
 */
public class Tabela extends GridPane {
    private int qtdColunas;
    private int qtdLinhas;
    private final int ALTURA_LINHA = 36;
    
    public Tabela () {
        super();
        qtdColunas = 0;
        qtdLinhas = 0;
    }
    
    public void addColuna(ColumnConstraints constraints, String titulo) {
        getColumnConstraints().add(constraints);
        Celula cabecalho = new Celula(titulo);
        cabecalho.estiloCabecalho();
        addColumn(qtdColunas++, cabecalho);
    }
    
    public void addLinha (Celula... celulas) {
        addRow(++qtdLinhas, celulas);
    }
    
    public Celula novaCelula (Node conteudo) {
        return new Celula(conteudo);
    }
    
    public Celula novaCelula (String conteudo) {
        return new Celula(conteudo);
    }
    
    
    public class Celula extends StackPane {
        Node conteudo;
        
        public Celula (String texto) {
            super();
            Label conteudo = new Label(texto);
            conteudo.setFont(Atributo.getUbuntu(12));
            conteudo.setTextFill(Atributo.ROXO_PRINCIPAL);
            conteudo.setTextAlignment(TextAlignment.LEFT);
            conteudo.setAlignment(Pos.CENTER_LEFT);
            
            this.conteudo = conteudo;
            setAlignment(Pos.CENTER_LEFT);
            inicializaCelula();
        }
        
        public Celula (Node conteudo) {
            super();
            this.conteudo = conteudo;
            
            setAlignment(Pos.CENTER);
            inicializaCelula();
        }
        
        private void inicializaCelula () {
            setPadding(new Insets(0, 10, 0, 10));
            setPrefHeight(ALTURA_LINHA);
            setBorder(new Border(new BorderStroke(Atributo.CINZA_ESCURO,
            BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, new BorderWidths(1), new Insets(-1, -1, 0, 0))));
            getChildren().add(conteudo);
            HBox.setHgrow(this, Priority.ALWAYS);
            VBox.setVgrow(this, Priority.ALWAYS);
        }
        
        public void estiloCabecalho () {
            BackgroundFill bg = new BackgroundFill(Atributo.CINZA_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
            setBackground(new Background(bg));
            
            String titulo = ((Label) conteudo).getText().toUpperCase();
            ((Label) conteudo).setFont(Atributo.getUbuntu(12, Estilo.BOLD));
            ((Label) conteudo).setText(titulo);
        }
    }
}
