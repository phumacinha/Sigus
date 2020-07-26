/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.view.util.Atributo;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author pedro
 */
public abstract class TelaUtil {
    private final String titulo;
    private Scene scene;
    private double width = Atributo.SCENE_WIDTH;
    private double height = Atributo.SCENE_HEIGHT;
    private boolean tamanhoExato = false;
    
    public TelaUtil (String titulo) {
        this.titulo = titulo;
    }
    
    public TelaUtil (String titulo, double width, double height) {
        this.titulo = titulo;
        this.width = width;
        this.height = height;
    }
    
    protected void setTamanhoExato() {
        tamanhoExato = true;
    }
    
    public final Scene getScene() {
        Node conteudo = conteudoInicial();
        StackPane root = new StackPane(conteudo);
        root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        scene = tamanhoExato ? new Scene(root) : new Scene(root, width, height);
        scene.setFill(Color.WHITE);
        
        return scene;
    }
    
    public final String getTitulo () {
        return titulo + " | Sigus";
    }
    
    protected final Stage getStage() {
        return (Stage) scene.getWindow();
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="metodos abstratos">    
    protected abstract Node conteudoInicial();
}
