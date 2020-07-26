/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;

/**
 *
 * @author pedro
 */
public class Atributo {
    public static final int SCENE_WIDTH = 1024;
    public static final int SCENE_HEIGHT = 576;
    
    public static final Color ROXO_PRINCIPAL = Color.rgb(47, 58, 115);
    public static final Color ROXO_SECUNDARIO = Color.rgb(58, 47, 115);
    public static final LinearGradient ROXO_GRADIENTE = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
        new Stop[] {
            new Stop(0, Atributo.ROXO_SECUNDARIO),
            new Stop(1, Atributo.ROXO_PRINCIPAL)
        }
    ); 
    public static final Color CINZA_CLARO = Color.rgb(224, 224, 224);
    public static final Color CINZA_MEDIO = Color.rgb(175, 175, 175);
    public static final Color CINZA_ESCURO = Color.rgb(150, 150, 150);
    public static final LinearGradient CINZA_GRADIENTE = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
        new Stop[] {
            new Stop(0, Atributo.CINZA_CLARO),
            new Stop(1, Atributo.CINZA_MEDIO)
        }
    ); 
    
    public enum Estilo {REGULAR, BOLD, LIGHT};
    
    public static final Font getFonteRussoOne (double size) {
        return Font.loadFont("file:./resources/RussoOne-Regular.ttf", size);
    }
    
    public static final Font getUbuntu (double size, Estilo estilo) {
        switch (estilo) {
            case REGULAR:
                return Font.loadFont("file:./resources/Ubuntu-Regular.ttf", size);
            case BOLD:
                return Font.loadFont("file:./resources/Ubuntu-Bold.ttf", size);
            case LIGHT:
                return Font.loadFont("file:./resources/Ubuntu-Light.ttf", size);
        }
        return null;
    }
    
    public static final Font getUbuntu (double size) {
        return getUbuntu(size, Estilo.REGULAR);
    }
}
