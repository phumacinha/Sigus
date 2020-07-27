/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.controller;

import com.dao.ExceptionDAO;
import com.dao.PerfilUsuarioDAO;
import com.dao.UsuarioDAO;
import com.mvc.model.Cargo;
import com.mvc.model.Perfil;
import com.mvc.model.PerfilUsuario;
import com.mvc.model.Usuario;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author pedro
 */
public class UsuarioController {
    public boolean cadastrar (String nome, String cpf, String dataNascimento, String sexo, Cargo cargo, ArrayList<Perfil> perfis) {
        if (nome != null && nome.length() > 0
            && cpf != null && validaCpf(cpf)
            && (sexo == null || sexo.equals('M') || sexo.equals('F'))   
            && cargo != null) {
            
            String data = dataNascimento;
            try {
                Usuario usuario = new Usuario(nome, cpf, data, sexo, cargo);
                usuario.cadastrar();
                
                for (Perfil perfil : perfis) {
                    PerfilUsuario relacionamento = new PerfilUsuario(perfil, usuario);
                    relacionamento.salvarRelacionamento();
                }
                
                return true;
            }
            catch (ExceptionDAO ex) {
                return false;
            }
        }
        return false;
    }
    
    public boolean alterar(Usuario antigo, Usuario novo, ArrayList<Perfil> perfis) {
        try {
            new PerfilUsuarioDAO().removerPerfisUsuario(antigo);
            antigo.alterar(novo);
            for (Perfil perfil : perfis) {
                PerfilUsuario relacionamento = new PerfilUsuario(perfil, novo);
                relacionamento.salvarRelacionamento();
            }
            return true;
        } catch (ExceptionDAO ex) {
           return false;
        }
    }

    public int quantidadeTotal() {
        try {
            return new UsuarioDAO().quantidadeTotal();
        } catch (ExceptionDAO ex) {
            return 0;
        }
    }

    public ArrayList<Usuario> retornaTodos() {
        try {
            return new UsuarioDAO().retornaTodos();
        } catch (ExceptionDAO ex) {
            return null;
        }
    }
    
    public Usuario selecionarUsuarioPorCpf (String cpf) {
        try {
            return new UsuarioDAO().usuarioPorCpf(cpf);
        } catch (ExceptionDAO ex) {
            return null;
        }
    }
    
    private boolean validaCpf(String cpf) {
        // Autor Omero
        // Código disponível em https://www.devmedia.com.br/validando-o-cpf-em-uma-aplicacao-java/22097
        
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do CPF em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
            num = (int)(cpf.charAt(i) - 48); 
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                 return(true);
            else return(false);
                } catch (InputMismatchException erro) {
                return(false);
            }
    }
}
