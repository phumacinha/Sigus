/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view;

import com.mvc.view.util.Atributo;
import com.mvc.view.util.Botao;
import com.mvc.view.util.Navbar;
import com.mvc.view.util.Tabela;
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
public abstract class TelaGerenciarAbstrata extends TelaUtil {
    String tituloTela;
    String categoria;
    Node dadosCadastrados;
    
    public TelaGerenciarAbstrata (String tituloTela, String categoria) {
        super(tituloTela);
        this.tituloTela = tituloTela;
        this.categoria = categoria;
    }
    
    @Override
    protected Node conteudoInicial() {
        //<editor-fold defaultstate="collapsed" desc="cabecalho">
        Text tituloLabel = new Text(tituloTela);
        tituloLabel.setFont(Atributo.getFonteRussoOne(24));
        tituloLabel.setFill(Atributo.ROXO_PRINCIPAL);
        tituloLabel.setTextAlignment(TextAlignment.LEFT);
        tituloLabel.setBoundsType(TextBoundsType.LOGICAL_VERTICAL_CENTER);
        
        HBox titulo = new HBox(tituloLabel);
        titulo.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(titulo, Priority.ALWAYS);
        
        Botao btnAdicionar = new Botao("+ Adicionar novo "+categoria, 12, 200, 40);
        btnAdicionar.setLightStyle(false);
        btnAdicionar.setOnMouseClicked(getTelaAdicionar(), true);
        
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
        dados.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        
        Tabela dadosCadastrados  = getDadosCadastrados();
        
        if (dadosCadastrados == null) {
            Label nenhumResultado = new Label("Nenhum "+categoria+" cadastrado.");
            nenhumResultado.setTextFill(Atributo.ROXO_PRINCIPAL);
            nenhumResultado.setFont(Atributo.getUbuntu(14));
            nenhumResultado.setPadding(new Insets(20, 0, 0, 0));
            
            StackPane mensagem = new StackPane(nenhumResultado);
            mensagem.setAlignment(Pos.CENTER_LEFT);
            mensagem.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            dados.setContent(mensagem);
        }
        else {
            //<editor-fold defaultstate="collapsed" desc="apagar depois">
            /*Tabela tabela = new Tabela();
            HBox.setHgrow(tabela, Priority.ALWAYS);
            
            ColumnConstraints cons = new ColumnConstraints();
            cons.setFillWidth(true);
            cons.setHgrow(Priority.ALWAYS);
            
            tabela.addColuna(cons, "CARGO");
            
            cons = new ColumnConstraints();
            cons.setHalignment(HPos.CENTER);
            tabela.addColuna(new ColumnConstraints(), "OPCOES");
            
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            
            OpcoesTabela op = new OpcoesTabela();
            op.naoRemovivel();
            tabela.addLinha(tabela.novaCelula("Professor"), tabela.novaCelula(op));
            
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));
            tabela.addLinha(tabela.novaCelula("Estudante"), tabela.novaCelula(new OpcoesTabela()));*/
//</editor-fold>
            dados.setContent(dadosCadastrados);
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
    
    protected abstract Tabela getDadosCadastrados();
    
    protected abstract TelaUtil getTelaAdicionar();
}
