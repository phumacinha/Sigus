/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view;

import com.estagiozetta.mvc.view.util.Atributo;
import com.estagiozetta.mvc.view.util.Botao;
import com.estagiozetta.mvc.view.util.Navbar;
import com.estagiozetta.mvc.view.util.OpcoesTabela;
import com.estagiozetta.mvc.view.util.Tabela;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

/**
 *
 * @author pedro
 */
public class TelaGerenciarPerfis extends TelaUtil {
    public TelaGerenciarPerfis () {
        super("Gerenciamento de Perfis | Sigus");
    }
    
    @Override
    protected Node conteudoInicial() {        
        //<editor-fold defaultstate="collapsed" desc="cabecalho">
        Text tituloLabel = new Text("Gerenciamento de Perfis");
        tituloLabel.setFont(Atributo.getFonteRussoOne(24));
        tituloLabel.setFill(Atributo.ROXO_PRINCIPAL);
        tituloLabel.setTextAlignment(TextAlignment.LEFT);
        tituloLabel.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        
        HBox titulo = new HBox(tituloLabel);
        titulo.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(titulo, Priority.ALWAYS);
        
        Botao btnAdicionar = new Botao("+ Adicionar novo perfil", 12, 200, 40);
        btnAdicionar.setLightStyle(false);
        
        HBox cabecalho = new HBox(titulo, btnAdicionar);
        cabecalho.setPadding(new Insets(20, 0, 20, 0));
        cabecalho.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Atributo.CINZA_MEDIO, Color.TRANSPARENT,
            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="dados">
        //VBox dados = new VBox();
        ScrollPane dados = new ScrollPane();
        dados.pannableProperty().set(true);
        dados.fitToWidthProperty().set(true);
        dados.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        dados.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        dados.setBorder(Border.EMPTY);
        dados.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        int qtd = 1;
        if (qtd == 0) {
            Label nenhumResultado = new Label("Nenhum perfil cadastrado.");
            nenhumResultado.setTextFill(Atributo.ROXO_PRINCIPAL);
            nenhumResultado.setFont(Atributo.getUbuntu(14));
            nenhumResultado.setPadding(new Insets(20, 0, 0, 0));
            
            StackPane mensagem = new StackPane(nenhumResultado);
            mensagem.setAlignment(Pos.CENTER_LEFT);
            mensagem.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            dados.setContent(mensagem);
        }
        else {
            Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            
            tabela.addColuna(cons, "Perfil");
            
            cons = new ColumnConstraints();
            cons.setHalignment(HPos.CENTER);
            tabela.addColuna(new ColumnConstraints(), "OPCOES");
            
            tabela.addLinha(tabela.novaCelula("Pedro"), tabela.novaCelula(new OpcoesTabela()));
            
            dados.setContent(tabela);

        }
        
        //</editor-fold>
        
        VBox conteudo = new VBox(cabecalho, dados);
        conteudo.setPadding(new Insets(0, 40, 40, 40));
        HBox.setHgrow(conteudo, Priority.ALWAYS);
        
        VBox tela = new VBox(new Navbar(), conteudo);
        VBox.setVgrow(tela, Priority.ALWAYS);
        HBox.setHgrow(tela, Priority.ALWAYS);
        return tela;
    }
}
