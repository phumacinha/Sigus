/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mvc.model.Cargo;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class CargoDAO {
    public void cadastrar(Cargo cargo) throws ExceptionDAO {
        String sql = "INSERT INTO cargo (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cargo.getNome());
            pStatement.execute();
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
    }
    
    public void remover (Cargo cargo) throws ExceptionDAO {
        String sql = "DELETE FROM cargo WHERE nome = ?"; //"INSERT INTO cargo (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cargo.getNome());
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao remover cargo: " + ex);
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
        
    public void alterar (Cargo antigo, Cargo novo) throws ExceptionDAO {
        String sql = "UPDATE cargo SET nome = ? WHERE nome = ?;"; //"INSERT INTO cargo (nome) VALUES (?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, novo.getNome());
            pStatement.setString(2, antigo.getNome());
            System.out.println("UPDATE cargo SET nome = '"+novo.getNome()+"' WHERE nome = '"+antigo.getNome()+"';");
            pStatement.execute();
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao alterar cargo: " + ex);
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
    
    public int quantidadeCargos() throws ExceptionDAO {
        String sql = "SELECT COUNT(nome) FROM cargo;";
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
    
    public ArrayList<Cargo> retornaCargos () throws ExceptionDAO {
        String sql = "SELECT * FROM cargo ORDER BY nome ASC;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        ArrayList<Cargo> lista = new ArrayList<>();
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                lista.add(new Cargo(rs.getString("nome")));
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
