/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view.util;

import com.mvc.view.App;
import com.mvc.view.TelaUtil;
import com.mvc.view.util.Atributo.Estilo;
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
    private boolean lightStyle = false, propriedadePreencher = false, propriedadeBorder = true;
    
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
                preencher(!propriedadePreencher);
            }
        });
        
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                preencher(propriedadePreencher);
            }
        });
    }
    
    public void setLightStyle (boolean light) {
        lightStyle = light;
        corPrincipal = light ? Color.WHITE : corPreenchimento;
        corSecundaria = light ? corPreenchimento: Color.WHITE;
        
        propriedadePreencher = false;
        preencher(false);
    }
    
    public final void setLightStyle () {
        setLightStyle(true);
    }
    
    public void setCorPreenchimento (Color cor) {
        corPreenchimento = cor;
        setPreenchido();
    }
    
    public void setPreenchido() {
        propriedadePreencher = true;
        lightStyle = false;
        corPrincipal = corPreenchimento;
        corSecundaria = Color.WHITE;
        preencher(propriedadePreencher);
    }
    
    public void desativarBorda() {
        propriedadeBorder = false;
        preencher(propriedadePreencher);
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
    
    private void preencher(boolean preencher) {
        if (propriedadeBorder)
            setBorder(new Border(new BorderStroke(corPrincipal, BorderStrokeStyle.SOLID, new CornerRadii(ARCO), new BorderWidths(1))));
        else
            setBorder(Border.EMPTY);

        if (preencher) {
            setTextFill(corSecundaria);
            BackgroundFill bg = new BackgroundFill(corPrincipal, new CornerRadii(ARCO), Insets.EMPTY);
            setBackground(new Background(bg));
        }
        else {
            setTextFill(corPrincipal);
            setBackground(Background.EMPTY);
        }
    }
}
