/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.ctr;

/**
 *
 * @author joaoo
 */
import br.com.projeto_pokemon.dao.ConexaoDAO;
import java.sql.ResultSet;
import br.com.projeto_pokemon.dto.PokemonDTO;
import br.com.projeto_pokemon.dao.PokemonDAO;
import br.com.projeto_pokemon.dto.MestreDTO;

public class PokemonCTR {
    
    PokemonDAO pokemonDAO = new PokemonDAO();
    
    public PokemonCTR(){
    }
    
    public String inserirPokemon(PokemonDTO pokemonDTO, MestreDTO mestreDTO){
        try {
            
            System.out.println(mestreDTO.getId_mestre());
            
            if (pokemonDAO.inserirPokemon(pokemonDTO, mestreDTO)) {
                return "Pokemon registrado com sucesso!!!";
            } else {
                return "Pokemon não registrado!!!";
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Pokemon não registrado!!!";
        }
    }
    
    public ResultSet consultarPokemon(PokemonDTO pokemonDTO, int opcao){
        ResultSet rs = null;
        
        rs = pokemonDAO.consultarPokemon(pokemonDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
    public String alterarPokemon(PokemonDTO pokemonDTO, MestreDTO mestreDTO){
        try {
            if (pokemonDAO.aterarPokemon(pokemonDTO, mestreDTO)) {
                return "Pokemon alterado com sucesso!!!";
            } else {
                return "Pokemon não alterado!!!";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Pokemon não alterado!!!";
        }
    }
    
    public String excluirPokemon(PokemonDTO pokemonDTO) {
        try {
            if (pokemonDAO.excluirPokemon(pokemonDTO)) {
                return "Pokemon excluído com sucesso!!!";
            } else {
                return "Pokemon não excluído!!!";
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return "Pokemon não excluído!!!";
        }
    }  
}
