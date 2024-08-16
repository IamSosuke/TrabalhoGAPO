/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.ctr;

import java.sql.ResultSet;
import br.com.projeto_pokemon.dto.FuncionarioDTO;
import br.com.projeto_pokemon.dao.FuncionarioDAO;
import br.com.projeto_pokemon.dao.ConexaoDAO;

/**
 *
 * @author joaoo
 */
public class FuncionarioCTR {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public FuncionarioCTR() {
    }
    
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Funcionário cadastrado com sucesso!!!";
            } else {
                return "Funcionário não cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario não cadastrado";
        }
    }
    
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) { 
        try {
            if (funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Funcionário alterado com sucesso!!!";
            } else {
                return "Funcionário não alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionario não alterado";
        }
    }
    
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            if (funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Funcionário excluído com sucesso!!!";
            } else {
                return "Funcionário não excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Funcionário não excluído";
        }
    }
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        ResultSet rs = null;
        
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        
        return rs;
    }
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
