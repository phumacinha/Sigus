/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author pedro
 */
public class Logotipo extends StackPane {
    private final Text sigus;
    
    public Logotipo(double size) {
        super();
        
        sigus = new Text("sigus");
        sigus.setFont(Atributo.getFonteRussoOne(size));
        sigus.setFill(Color.WHITE);
        sigus.setTextAlignment(TextAlignment.CENTER);
        sigus.setBoundsType(TextBoundsType.VISUAL);
        
        getChildren().add(sigus);
    }
    
    public void setFill(Color cor) {
        sigus.setFill(cor);
    }
}
