/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author pedro
 */
public class App extends Application {
    private static Stage primaryStage;
    
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNIFIED);
        abrirTela(new TelaInicial());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void abrirTela (TelaUtil tela) {
        abrirTela(tela, false);
    }
    
    public static void abrirTela (TelaUtil tela, boolean novaJanela) {
        if (novaJanela) {
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNIFIED);
            stage.setTitle(tela.getTitulo());
            stage.setScene(tela.getScene());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            stage.setX(primaryStage.getX() + 200);
            stage.setY(primaryStage.getY() + 100);
            stage.show();
        }
        else {
            primaryStage.setTitle(tela.getTitulo());
            primaryStage.setScene(tela.getScene());
        }
    }
}
