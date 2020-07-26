/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.view.util;

import com.mvc.view.util.Atributo.Estilo;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author pedro
 */
public class Formulario extends VBox {
    private final ArrayList<Campo> campos;
    
    public Formulario (String titulo) {
        super();
        campos = new ArrayList<>();
        
        Label labelAcao = new Label(titulo);
        labelAcao.setFont(Atributo.getFonteRussoOne(30));
        labelAcao.setTextFill(Atributo.ROXO_PRINCIPAL);
        labelAcao.setPadding(new Insets(20, 0, 20, 0));
        StackPane acao = new StackPane(labelAcao);
        acao.setBorder(new Border(new BorderStroke(Color.TRANSPARENT, Color.TRANSPARENT, Atributo.CINZA_MEDIO, Color.TRANSPARENT,
            BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        
        setSpacing(20);
        setPrefWidth(512);
        getChildren().add(acao);
    }
    
    public final void addCampo (Campo campo) {
        getChildren().add(campo);
        campos.add(campo);
    }
    
    public void addRodape (Botao... botoes) {
        HBox rodape = new HBox(botoes);
        rodape.setPadding(new Insets(20, 0, 0, 0));
        rodape.setBorder(new Border(new BorderStroke(Atributo.CINZA_MEDIO, Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT,
            BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.NONE,
            CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
        rodape.setSpacing(10);
        rodape.setAlignment(Pos.CENTER_RIGHT);
        
        getChildren().add(rodape);
    }
    
    public CampoTexto novoCampoTexto (String nome) {
        return new CampoTexto(nome);
    }
    
    public CampoData novoCampoData (String nome) {
        return new CampoData(nome);
    }
    
    public CampoCpf novoCampoCpf (String nome) {
        return new CampoCpf(nome);
    }
    
    public CampoComboBox novoCampoComboBox (String nome, String opcaoNula) {
        return new CampoComboBox(nome, opcaoNula);
    }
    
    public CampoCheckBox novoCampoCheckBox (String nome, String textoPadrao) {
        return new CampoCheckBox(nome, textoPadrao);
    }
    
    public abstract class Campo extends VBox {
        protected final Font fontCampo = Atributo.getUbuntu(14);
        
        public Campo (String nome) {
            super();
            Label label = new Label(nome);
            label.setFont(Atributo.getUbuntu(16, Estilo.BOLD));
            label.setTextFill(Atributo.ROXO_PRINCIPAL);
            
            setSpacing(5);
            getChildren().addAll(label);
        }
        
        public void addCampo (Node campo) {
            getChildren().add(campo);
        }
        
        public abstract Object getValue ();
    }
    
    public class CampoTexto extends Campo {
        private final TextField campo;

        public CampoTexto (String nome) {
            super(nome);

            campo = new TextField();
            campo.setPrefHeight(40);
            campo.setFont(fontCampo);

            addCampo(campo);
        }

        @Override
        public String getValue () {
            String valor = campo.getText().trim();
            return valor.length() > 0 ? valor : null;
        }

        public void setValue (String texto) {
            campo.setText(texto.trim());
        }
    }
    
    public class CampoData extends Campo {
        private final MaskedTextField campo;
        
        public CampoData (String nome) {
            super(nome);
            
            campo = new MaskedTextField("##/##/####");
            campo.setPrefHeight(40);
            campo.setFont(fontCampo);
            
            addCampo(campo);
        }
        
        @Override
        public String getValue () {
            return campo.getPlainText().trim().length() == 0 ? null : campo.getText().trim();
        }
    }
    
    public class CampoCpf extends Campo {
        private final MaskedTextField campo;
        
        public CampoCpf (String nome) {
            super(nome);
            
            campo = new MaskedTextField("###.###.###-##");
            campo.setPrefHeight(40);
            campo.setFont(fontCampo);
            
            addCampo(campo);
        }
        
        @Override
        public String getValue () {
            return campo.getPlainText().trim().length() == 0
                    ? null
                    : campo.getPlainText().trim();
        }
    }
    
    public class CampoComboBox extends Campo {
        private final ComboBox combobox;
        private final String opcaoNula;
        
        public CampoComboBox (String nome, String opcaoNula) {
            super(nome);
            this.opcaoNula = opcaoNula;
            
            combobox = new ComboBox();
            combobox.setPrefHeight(40);
            combobox.getEditor().setFont(fontCampo);
            addItem(opcaoNula);
            combobox.setValue(opcaoNula);
            addCampo(combobox);
        }
        
        public void addItem (String item) {
            combobox.getItems().add(item);
        }
        
        @Override
        public String getValue() {
            Object selecionado = combobox.getSelectionModel().getSelectedItem();
            return (selecionado == null || selecionado.toString().equals(opcaoNula))
                    ? null
                    : selecionado.toString();
        }
    }

    public class CampoCheckBox extends Campo {
        private final ScrollPane campo;
        private final FlowPane opcoes;
        private final ArrayList<CheckBox> lista;
        private final Label texto;
        
        public CampoCheckBox(String nome, String textoPadrao) {
            super(nome);
            lista = new ArrayList<>();
            
            texto = new Label(textoPadrao);
            texto.setFont(fontCampo);
            
            opcoes = new FlowPane(texto);
            opcoes.setHgap(40);
            opcoes.setVgap(40);
            opcoes.setPadding(new Insets(10));
            
            campo = new ScrollPane(opcoes);
            campo.setPadding(Insets.EMPTY);
            campo.pannableProperty().set(true);
            campo.fitToWidthProperty().set(true);
            campo.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
            campo.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            campo.setMaxHeight(200);
            
            addCampo(campo);
        }
        
        public void addOpcao (String opcao) {
            CheckBox checkbox = new CheckBox(opcao);
            checkbox.setFont(fontCampo);
            checkbox.setPrefHeight(40);
            checkbox.isSelected();
            
            lista.add(checkbox);
            opcoes.getChildren().remove(texto);
            opcoes.getChildren().add(checkbox);
        }
        
        @Override
        public Object getValue () {
            ArrayList<String> selecionados = new ArrayList<>();
            
            lista.forEach(opcao -> {
                if (((CheckBox) opcao).isSelected())
                    selecionados.add(((CheckBox) opcao).getText());
            });
            
            return selecionados.isEmpty() ? null : selecionados;
        }
    }
}
