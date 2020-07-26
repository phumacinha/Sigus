/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import com.estagiozetta.mvc.view.App;
import com.estagiozetta.mvc.view.TelaUtil;
import com.estagiozetta.mvc.view.util.Atributo.Estilo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author pedro
 */
public class Botao extends Button {
    private Color corPrincipal, corSecundaria;
    private final int arco = 5;
    
    public Botao(String valor, double fontSize, double width, double height) {
        super(valor.toUpperCase());
        setPrefSize(width, height);
        setFont(Atributo.getUbuntu(fontSize, Estilo.BOLD));
        setCursor(Cursor.HAND);
        setBackground(Background.EMPTY);
        setLightStyle();
        setAlignment(Pos.CENTER);
        
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                setTextFill(corSecundaria);
                BackgroundFill bg = new BackgroundFill(corPrincipal, new CornerRadii(arco), Insets.EMPTY);
                setBackground(new Background(bg));
            }
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                setTextFill(corPrincipal);
                setBackground(Background.EMPTY);
            }
        });
    }
    
    public void setLightStyle (boolean light) {
        corPrincipal = light ? Color.WHITE : Atributo.ROXO_PRINCIPAL;
        corSecundaria = light ? Atributo.ROXO_PRINCIPAL: Color.WHITE;
        
        setTextFill(corPrincipal);
        setBorder(new Border(new BorderStroke(corPrincipal, BorderStrokeStyle.SOLID, new CornerRadii(arco), new BorderWidths(1))));
    }
    
    public final void setLightStyle () {
        setLightStyle(true);
    }
    
    public final void setOnMouseClicked(TelaUtil tela) {
        setOnMouseClicked(tela, false);
    }
    
    public final void setOnMouseClicked(TelaUtil tela, boolean novaJanela) {
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                App.abrirTela(tela, novaJanela);
            }
        });
    }
}
