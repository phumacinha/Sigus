/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.mvc.model.Cargo;
import com.mvc.model.Perfil;
import com.mvc.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PerfilUsuarioDAO {
    public void salvarRelacionamento (Usuario usuario, Perfil perfil) throws ExceptionDAO {
        String sql = "INSERT INTO perfil_usuario (cpf, perfil) VALUES (?, ?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getCpf());
            pStatement.setString(1, perfil.getNome());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao relacionar Perfil e Usuário: " + ex);
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
    }
    
    public ArrayList<Perfil> retornarPerfisUsuario (Usuario usuario) throws ExceptionDAO {
        String sql = "SELECT perfil FROM perfil_usuario WHERE cpf = ?;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        ArrayList<Perfil> perfis = new ArrayList<>();
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getCpf());
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                perfis.add(new Perfil(rs.getString("perfil")));
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
        
        return perfis;
    }
    
    public void removerPerfisUsuario (Usuario usuario) throws ExceptionDAO {
        String sql = "DELETE FROM perfil_usuario WHERE cpf = ?";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getCpf());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao remover relações: " + ex);
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
    }
}
