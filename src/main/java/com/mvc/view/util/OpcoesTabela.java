/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view.util;

import com.mvc.view.TelaUtil;
import com.mvc.view.util.Atributo.Estilo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author pedro
 */
public class OpcoesTabela extends HBox {
    private Botao editar, remover;
    
    public OpcoesTabela () {
        super();
        editar = new BotaoTabela("Editar");
        remover = new BotaoTabela("Remover", Color.RED);
        
        setSpacing(10);
        getChildren().addAll(editar, remover);
        setAlignment(Pos.CENTER);
    }
    
    public void naoRemovivel () {
        getChildren().remove(remover);
    }
    
    public void setOnClickEditar(TelaUtil tela) {
        editar.setOnMouseClicked(tela, true);
    }
    
    public void setOnClickRemover(EventHandler<MouseEvent> event) {
        remover.setOnMouseClicked(event);
    }
    
    private class BotaoTabela extends Botao {
        public BotaoTabela(String texto) {
            super(texto, 12, 65, 24);
            definirCor(Atributo.ROXO_PRINCIPAL);
        }
        
        public BotaoTabela(String texto, Color cor) {
            super(texto, 12, 65, 24);
            definirCor(cor);
        }
        
        private void definirCor (Color cor) {
            Background bg = new Background(new BackgroundFill(cor, new CornerRadii(ARCO), Insets.EMPTY));
            setBorder(new Border(new BorderStroke(cor, BorderStrokeStyle.SOLID, new CornerRadii(ARCO), new BorderWidths(1))));
            setBackground(bg);
            setFont(Atributo.getUbuntu(12, Estilo.LIGHT));
            setTextFill(Color.WHITE);
            setPadding(Insets.EMPTY);
            
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    setTextFill(cor);
                    setBackground(Background.EMPTY);
                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    setTextFill(Color.WHITE);
                    setBackground(bg);
                }
            });
        }
    }
}
