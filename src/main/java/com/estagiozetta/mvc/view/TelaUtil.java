/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.Atributo;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Window;

/**
 *
 * @author pedro
 */
public abstract class TelaUtil {
    private final StackPane root = new StackPane();
    private final String titulo;
    private Scene scene;
    private int width = Atributo.SCENE_WIDTH;
    private int height = Atributo.SCENE_HEIGHT;
    
    public TelaUtil (String titulo) {
        this.titulo = titulo;
    }
    
    public TelaUtil (String titulo, int width, int height) {
        this.width = width;
        this.height = height;
        this.titulo = titulo;
    }
    
    public final Scene getScene() {
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(conteudoInicial());
        
        scene = new Scene(root, width, height);
        scene.setFill(Color.WHITE);
        
        return scene;
    }
    
    public final String getTitulo () {
        return titulo;
    }
    
    public final Window getWindow() {
        return scene.getWindow();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="metodos abstratos">    
    protected abstract Node conteudoInicial();
}
