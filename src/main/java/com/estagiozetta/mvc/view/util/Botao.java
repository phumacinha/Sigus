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
    protected Color corPrincipal, corSecundaria;
    protected Color corPreenchimento;
    protected final int ARCO = 5;
    private boolean lightStyle = false;
    
    public Botao(String valor, double fontSize, double width, double height) {
        super(valor.toUpperCase());
        corPreenchimento = Atributo.ROXO_PRINCIPAL;
        
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
                BackgroundFill bg = new BackgroundFill(corPrincipal, new CornerRadii(ARCO), Insets.EMPTY);
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
        lightStyle = light;
        corPrincipal = light ? Color.WHITE : corPreenchimento;
        corSecundaria = light ? corPreenchimento: Color.WHITE;
        
        setTextFill(corPrincipal);
        setBorder(new Border(new BorderStroke(corPrincipal, BorderStrokeStyle.SOLID, new CornerRadii(ARCO), new BorderWidths(1))));
    }
    
    public final void setLightStyle () {
        setLightStyle(true);
    }
    
    public void setCorPreenchimento (Color cor) {
        corPreenchimento = cor;
        setLightStyle(lightStyle);
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
