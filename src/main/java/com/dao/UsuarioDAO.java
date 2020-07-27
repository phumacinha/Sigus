/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.mvc.model.Cargo;
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
public class UsuarioDAO {
    public void cadastrar (Usuario usuario) throws ExceptionDAO {
        String sql = "INSERT INTO usuario (nome, cpf, dataNascimento, sexo, cargo)"
                    + "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, usuario.getNome());
            pStatement.setString(2, usuario.getCpf());
            pStatement.setObject(3, usuario.getDataNascimento());
            pStatement.setObject(4, usuario.getSexo());
            pStatement.setString(5, usuario.getCargo().getNome());
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
    
    public void alterar (Usuario antigo, Usuario novo) throws ExceptionDAO {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, dataNascimento = ?, sexo = ?, cargo = ? WHERE cpf = ?;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, novo.getNome());
            pStatement.setString(2, novo.getCpf());
            pStatement.setObject(3, novo.getDataNascimento());
            pStatement.setObject(4, novo.getSexo());
            pStatement.setString(5, novo.getCargo().getNome());
            pStatement.setString(5, antigo.getCpf());
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
    
    public int quantidadeTotal() throws ExceptionDAO {
        String sql = "SELECT COUNT(cpf) FROM usuario;";
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
            throw new ExceptionDAO("Erro ao contar usuários: " + ex);
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
    
    public Usuario usuarioPorCpf (String cpf) throws ExceptionDAO {
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
                Cargo cargo = new Cargo(rs.getString(rs.getString("cargo")));
                usuario = new Usuario(rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNascimento"), rs.getString("sexo"), cargo);

            }
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao retornar usuários: " + ex);
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
    
    public ArrayList<Usuario> retornaTodos () throws ExceptionDAO {
        String sql = "SELECT * FROM usuario ORDER BY nome ASC;";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try {
            connection = new Conexao().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            while (rs.next()) {
                Cargo cargo = new Cargo(rs.getString("cargo"));
                Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("cpf"), rs.getString("dataNascimento"), rs.getString("sexo"), cargo);
                usuarios.add(usuario);
            }
            
            return usuarios;
        }
        catch (SQLException ex) {
            throw new ExceptionDAO("Erro ao retornar usuários: " + ex);
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
