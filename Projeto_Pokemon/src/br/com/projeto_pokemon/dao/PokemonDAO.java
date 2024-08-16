/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dao;

/**
 *
 * @author joaoo
 */
import br.com.projeto_pokemon.dto.MestreDTO;
import java.sql.*;
import br.com.projeto_pokemon.dto.PokemonDTO;

public class PokemonDAO {
    
    public PokemonDAO(){ 
    }
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirPokemon(PokemonDTO pokemonDTO, MestreDTO mestreDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into pokemon (nome_poke, tipo_poke, geracao_poke, "
                    + "qtdevo_poke, ataque_poke, ataqueesp_poke, defesa_poke, defesaesp_poke, "
                    + "altura_poke, peso_poke, id_mestre, valor_poke) values ( "
                    + "'" + pokemonDTO.getNome_poke() + "', "
                    + "'" + pokemonDTO.getTipo_poke() + "', "
                    + "'" + pokemonDTO.getGeracao_poke() + "', "
                    + "'" + pokemonDTO.getQtdEvo_poke() + "', "
                    + "'" + pokemonDTO.getAtaque_poke() + "', "
                    + "'" + pokemonDTO.getAtaqueEsp_poke() + "', "
                    + "'" + pokemonDTO.getDefesa_poke() + "', "
                    + "'" + pokemonDTO.getDefesaEsp_poke() + "', "
                    + "'" + pokemonDTO.getAltura_poke() + "', "
                    + "'" + pokemonDTO.getPeso_poke() + "', "
                    + mestreDTO.getId_mestre() + ", "
                    + pokemonDTO.getValor_poke() + ")";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarPokemon(PokemonDTO pokemonDTO, int opcao){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao) {
                
                case 1:
                    comando = "Select c.* "+
                              "from pokemon c "+
                              "where c.nome_poke ilike '" + pokemonDTO.getNome_poke()+ "%' " +
                              "order by c.nome_poke";
                break;
                case 2:
                    comando = "Select c. *, f.nome_mestre, f.id_mestre "+
                              "from pokemon c, mestre f " +
                              "where c.id_mestre = f.id_mestre and " +
                              "c.id_poke = " + pokemonDTO.getId_poke();
                break;
                /*case 3:
                    comando = "Select c.id_poke, c.nome_poke "+
                              "from poke c ";
                break;*/    
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean aterarPokemon(PokemonDTO pokemonDTO, MestreDTO mestreDTO){
        try {           
            ConexaoDAO.ConectDB();
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update pokemon set "
                    + "nome_poke = '" + pokemonDTO.getNome_poke()+ "', "
                    + "tipo_poke = '" + pokemonDTO.getTipo_poke()+ "', "
                    + "geracao_poke = '" + pokemonDTO.getGeracao_poke()+ "', "
                    + "qtdevo_poke = '" + pokemonDTO.getQtdEvo_poke()+ "', "
                    + "ataque_poke = '" + pokemonDTO.getAtaque_poke()+ "', "
                    + "ataqueesp_poke = '" + pokemonDTO.getAtaqueEsp_poke()+ "', "
                    + "defesa_poke = '" + pokemonDTO.getDefesa_poke()+ "', "
                    + "defesaesp_poke = '" + pokemonDTO.getDefesaEsp_poke()+ "', "
                    + "altura_poke = '" + pokemonDTO.getAltura_poke()+ "', "
                    + "peso_poke = '" + pokemonDTO.getPeso_poke()+ "', "
                    + "valor_poke = " + pokemonDTO.getValor_poke() + ", "
                    + "id_mestre = " + mestreDTO.getId_mestre() + " "
                    + "where id_poke = " + pokemonDTO.getId_poke();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirPokemon(PokemonDTO pokemonDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from pokemon where id_poke = "
                             + pokemonDTO.getId_poke();
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
}
