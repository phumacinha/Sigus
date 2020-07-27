/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.mvc.model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class PerfilDAO {
    public void cadastrar(Perfil perfil) throws ExceptionDAO {
        String sql = "INSERT INTO perfil (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, perfil.getNome());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao cadastrar perfil: " + ex);
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
    
    public void remover (Perfil cargo) throws ExceptionDAO {
        String sql = "DELETE FROM perfil WHERE nome = ?"; //"INSERT INTO cargo (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cargo.getNome());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao remover perfil: " + ex);
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
        
    public void alterar (Perfil antigo, Perfil novo) throws ExceptionDAO {
        String sql = "UPDATE perfil SET nome = ? WHERE nome = ?;"; //"INSERT INTO cargo (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, novo.getNome());
            pStatement.setString(2, antigo.getNome());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao alterar perfil: " + ex);
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
    
    public int quantidadeTotal() throws ExceptionDAO {
        String sql = "SELECT COUNT(nome) FROM perfil;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        int qtd = 0;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                qtd = rs.getInt(1);
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
        
        return qtd;
    }
    
    public ArrayList<Perfil> retornaTodos () throws ExceptionDAO {
        String sql = "SELECT * FROM perfil ORDER BY nome ASC;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        ArrayList<Perfil> lista = new ArrayList<>();
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                lista.add(new Perfil(rs.getString("nome")));
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
        
        return lista;
    }
}
