/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.ctr;

import java.sql.ResultSet;
import br.com.projeto_pokemon.dto.TreinadorDTO;
import br.com.projeto_pokemon.dao.TreinadorDAO;
import br.com.projeto_pokemon.dao.ConexaoDAO;

/**
 *
 * @author joaoo
 */
public class TreinadorCTR {
    
    TreinadorDAO treinadorDAO = new TreinadorDAO();
    
    public TreinadorCTR() {
    }
    
    public String inserirTreinador(TreinadorDTO treinadorDTO) {
        try {
            if (treinadorDAO.inserirTreinador(treinadorDTO)) {
                return "Treinador cadastrado com sucesso!!!";
            } else {
                return "Treinador não cadastrado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Treinador NÃO cadastrado";
        }
    }
    
    public String alterarTreinador(TreinadorDTO treinadorDTO) {
        try {
            if (treinadorDAO.alterarTreinador(treinadorDTO)) {
                return "Treinador alterado com sucesso!!!";
            } else {
                return "Treinador não alterado!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Treinador NÃO alterado";
        }
    }
    
    public String excluirTreinador(TreinadorDTO treinadorDTO) {
        try {
            if (treinadorDAO.excluirTreinador(treinadorDTO)) {
                return "Treinador excluído com sucesso!!!";
            } else {
                return "Treinador não excluído!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Treinador NÃO excluído";
        }
    }
    
    public ResultSet consultarTreinador(TreinadorDTO treinadorDTO, int opcao) {
        ResultSet rs = null;
        
        rs = treinadorDAO.consultarTreinador(treinadorDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
