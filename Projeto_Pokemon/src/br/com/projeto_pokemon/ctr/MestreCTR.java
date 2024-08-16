/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.ctr;

import java.sql.ResultSet;
import br.com.projeto_pokemon.dto.MestreDTO;
import br.com.projeto_pokemon.dao.MestreDAO;
import br.com.projeto_pokemon.dao.ConexaoDAO;

/**
 *
 * @author joaoo
 */
public class MestreCTR {
    
    MestreDAO mestreDAO = new MestreDAO();
    
    public MestreCTR() {
    }
    
    public String inserirMestre(MestreDTO mestreDTO) {
        try {
            if (mestreDAO.inserirMestre(mestreDTO)) {
                return "Mestre cadastrado com sucesso!!!";
            } else {
                return "Mestre NÃO Cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Mestre NÃO Cadastrado!!!";
        }
    }
    
    public String alterarMestre(MestreDTO mestreDTO) {
        try {
            if (mestreDAO.alterarMestre(mestreDTO)) {
                return "Mestre Alterado com Sucesso!!!";
            } else {
                return "Mestre NÃO Alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Mestre NÃO Alterado!!!";
        }
    }
    
    public String excluirMestre(MestreDTO mestreDTO) {
        try {
            if (mestreDAO.excluirMestre(mestreDTO)) {
                return "Mestre Excluído com sucesso!!!";
            } else {
                return "Mestre NÃO Excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Mestre NÃO Excluído!!!";
        }
    }
    
    public ResultSet consultarMestre(MestreDTO mestreDTO, int opcao) {
        ResultSet rs = null;
        
        rs = mestreDAO.consultarMestre(mestreDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
