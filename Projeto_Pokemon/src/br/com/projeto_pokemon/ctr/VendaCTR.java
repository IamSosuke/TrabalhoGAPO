/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.ctr;

import br.com.projeto_pokemon.dao.ConexaoDAO;
import br.com.projeto_pokemon.dao.VendaDAO;
import br.com.projeto_pokemon.dto.VendaDTO;
import br.com.projeto_pokemon.dto.TreinadorDTO;
import javax.swing.JTable;

/**
 *
 * @author joaoo
 */
public class VendaCTR {
    
    VendaDAO vendaDAO = new VendaDAO();
    
    public VendaCTR() {
    }
    
    public String inserirVenda(VendaDTO vendaDTO, TreinadorDTO treinadorDTO, JTable pokemons) {
        try {
            if (vendaDAO.inserirVenda(vendaDTO, treinadorDTO, pokemons)) {
                return "Venda cadastrada com sucesso!!!";
            } else {
                return "Venda NÃO cadastrada!!!";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Venda NÃO cadastrada";
        }
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
}
