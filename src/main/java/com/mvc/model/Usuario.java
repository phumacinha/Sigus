/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.model;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class Usuario {
    private String nome;
    private String cpf;
    private String dataNascimento = null;
    private String sexo = null;
    private Cargo cargo;
    
    public Usuario() {}
    
    public Usuario (String nome, String cpf, String dataNascimento, String sexo, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public Cargo getCargo() {
        return cargo;
    }  

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    public void cadastrar() {
        
    }
    
    
    
}
