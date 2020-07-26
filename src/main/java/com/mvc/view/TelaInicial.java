/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.controller.CargoController;
import com.mvc.controller.UsuarioController;
import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Logotipo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author pedro
 */
public class TelaInicial extends TelaUtil {
    VBox menu, detalhes;
    
    public TelaInicial() {
        super("Sistema de Gerenciamento de Usuários de um Sistema");
    }
    
    private void inicializaMenu () {
        Logotipo logo = new Logotipo(96);
        logo.setAlignment(Pos.CENTER);
        logo.setPadding(new Insets(0, 0, 40, 0));
        
        Botao btnCargos = new Botao("Gerenciar Cargos", 20, 300, 60);
        btnCargos.setOnMouseClicked(new TelaGerenciarCargos());
        
        Botao btnPerfis = new Botao("Gerenciar Perfis", 20, 300, 60);
        btnPerfis.setOnMouseClicked(new TelaGerenciarPerfis());
        
        Botao btnUsuarios = new Botao("Gerenciar Usuários", 20, 300, 60);
        btnUsuarios.setOnMouseClicked(new TelaGerenciarUsuarios());
        
       
        
        menu = new VBox(logo, btnCargos, btnPerfis, btnUsuarios);
        menu.setSpacing(30);
        BackgroundFill bg = new BackgroundFill(Atributo.ROXO_GRADIENTE, CornerRadii.EMPTY, Insets.EMPTY);
        menu.setBackground(new Background(bg));
        menu.setPrefWidth(Atributo.SCENE_WIDTH/2);
        menu.setAlignment(Pos.CENTER);
    }
    
    private void inicializaDetalhes () {
        Text titulo = new Text("Boas-vindas!");
        titulo.setFont(Atributo.getFonteRussoOne(48));
        titulo.setFill(Atributo.ROXO_PRINCIPAL);
        
        Label texto = new Label("O Sistema de Gerenciamento de Usuários de um "
                + "Sistema (Sigus) é uma ferramenta capaz de administrar além "
                + "dos usuários de um sistema, seus respectivos perfis e "
                + "cargos.");
        texto.setWrapText(true);
        texto.setTextAlignment(TextAlignment.CENTER);
        texto.setMaxWidth(405);
        texto.setTextFill(Atributo.ROXO_PRINCIPAL);
        texto.setFont(Atributo.getUbuntu(20, Atributo.Estilo.LIGHT));
        
        VBox boasvindas = new VBox(titulo, texto);
        boasvindas.setAlignment(Pos.CENTER);
        
        
        
        Text tituloQuantidade = new Text("Quantidade de cadastros no Sigus");
        tituloQuantidade.setFont(Atributo.getUbuntu(20, Atributo.Estilo.BOLD));
        tituloQuantidade.setFill(Atributo.ROXO_PRINCIPAL);
        new UsuarioController().selecionarUsuarioPorCpf("11587306670");
        QuantidadeCadastro qtdCargos = new QuantidadeCadastro(new CargoController().quantidadeCargos(), "cargo", "cargos");
        QuantidadeCadastro qtdPerfis = new QuantidadeCadastro(3, "perfil", "perfis");
        QuantidadeCadastro qtdUsuarios = new QuantidadeCadastro(12, "usuário", "usuários");
        
        HBox categorias = new HBox(qtdCargos, qtdPerfis, qtdUsuarios);
        categorias.setAlignment(Pos.CENTER);
        categorias.setSpacing(40);
        
        VBox quantidades = new VBox(tituloQuantidade, categorias);
        quantidades.setAlignment(Pos.CENTER);
        quantidades.setSpacing(15);
        
        detalhes = new VBox(boasvindas, quantidades);
        detalhes.setSpacing(85);
        detalhes.setPrefWidth(Atributo.SCENE_WIDTH/2);
        detalhes.setAlignment(Pos.CENTER);
    }
    
    @Override
    protected Node conteudoInicial() {        
        inicializaMenu();
        inicializaDetalhes();
        
        HBox conteudo = new HBox(menu, detalhes);
        conteudo.setAlignment(Pos.CENTER);
        return conteudo;
    }
    
    
    private class QuantidadeCadastro extends StackPane {        
        public QuantidadeCadastro (int quantidade, String singular, String plural) {
            super();
            setPrefSize(105, 105);
            super.setAlignment(Pos.TOP_CENTER);
            
            Circle circulo = new Circle(52.5);
            circulo.setStroke(Atributo.ROXO_PRINCIPAL);
            circulo.setStrokeWidth(3);
            circulo.setStrokeType(StrokeType.INSIDE);
            circulo.setFill(Color.WHITE);
            
            Label numero = new Label(String.valueOf(quantidade));
            numero.setFont(Atributo.getFonteRussoOne(48));
            numero.setTextFill(Atributo.ROXO_PRINCIPAL);
            numero.setPadding(new Insets(20, 0, 5, 0));
            VBox.setVgrow(numero, Priority.ALWAYS);
            
            Label titulo = new Label(quantidade > 1 ? plural.toUpperCase() : singular.toUpperCase());
            titulo.setPadding(new Insets(5, 0, 5, 0));
            titulo.setAlignment(Pos.CENTER);
            titulo.setPrefWidth(105);
            titulo.setTextAlignment(TextAlignment.CENTER);
            titulo.setFont(Atributo.getUbuntu(14, Atributo.Estilo.BOLD));
            titulo.setTextFill(Atributo.ROXO_PRINCIPAL);
            BackgroundFill bg = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
            titulo.setBackground(new Background(bg));
            
            VBox informacoes = new VBox(numero, titulo);
            informacoes.setAlignment(Pos.CENTER);
            
            getChildren().addAll(circulo, informacoes);
        }
    }
    
}
