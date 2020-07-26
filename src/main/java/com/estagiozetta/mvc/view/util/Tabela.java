/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estagiozetta.mvc.view.util;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author pedro
 */
public class Tabela extends GridPane {
    public Tabela (ArrayList<String> cabecalho) {
        super();
        add(new Text("oi"), 0, 0);
        add(new Text("oi"), 1, 0);
        add(new Text("oi"), 2, 0);
        add(new Text("oi"), 3, 0);
    }
}
