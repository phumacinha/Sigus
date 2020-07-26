/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Formulario;
import com.mvc.view.util.Formulario.Campo;
import com.mvc.view.util.Logotipo;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author pedro
 */
public class TelaFormCargo extends TelaUtil {
    private Integer idCargo = null;
    private String acao;
    
    public TelaFormCargo(int id) {
        super("Editar cargo");
        idCargo = id;
        acao = "Editar cargo";
        setTamanhoExato();
    }
    
    public TelaFormCargo() {
        super("Adicionar novo cargo");
        acao = "Adicionar novo cargo";
        setTamanhoExato();
    }

    @Override
    protected Node conteudoInicial() {
        Logotipo logotipo = new Logotipo(18);
        BackgroundFill bg = new BackgroundFill(Atributo.ROXO_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
        logotipo.setBackground(new Background(bg));
        logotipo.setMinHeight(40);
        logotipo.setMaxHeight(40);
        
        Formulario formulario = new Formulario(acao);
        
        Campo nome = formulario.novoCampoTexto("Nome do cargo:");
        formulario.addCampo(nome);
        
        Botao cadastrar = new Botao("Cadastrar", 15, 120, 40);
        cadastrar.setPreenchido();
        
        Botao cancelar = new Botao("Cancelar", 15, 120, 40);
        cancelar.setCorPreenchimento(Color.RED);
        cancelar.setLightStyle(false);
        cancelar.desativarBorda();
        cancelar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                getStage().close();
            }
        });
        
        formulario.addRodape(cancelar, cadastrar);
        
        VBox conteudo = new VBox(formulario);
        conteudo.setPadding(new Insets(0, 40, 40, 40));
        conteudo.setSpacing(20);
        
        
        VBox tela = new VBox(logotipo, conteudo);
        tela.setAlignment(Pos.TOP_CENTER);
        
        return tela;
    }
    
}
