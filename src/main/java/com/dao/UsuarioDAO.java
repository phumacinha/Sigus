/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.mvc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class UsuarioDAO {
    public Usuario selecionarUsuarioPorCpf (String cpf) throws ExceptionDAO {
        String sql = "SELECT * FROM usuario WHERE cpf = ?;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        Usuario usuario = null;
        
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cpf);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                //usuario = new Usuario(rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNascimento"), rs.getString("sexo"), null, null);
            }
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar cargo: " + ex);
        }
        finally {
            try {
                if (pStatement != null) pStatement.close();
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar o Statement: " + ex);
            }
            
            
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                throw new ExceptionDAO("Erro ao fechar a conexão: " + ex);
            }
            
        }
        
        return usuario;
    }
}
