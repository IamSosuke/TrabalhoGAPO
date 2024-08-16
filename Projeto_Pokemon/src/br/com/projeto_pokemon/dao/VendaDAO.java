/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dao;

import br.com.projeto_pokemon.dto.TreinadorDTO;
import br.com.projeto_pokemon.dto.VendaDTO;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JTable;

/**
 *
 * @author joaoo
 */
public class VendaDAO {
    
    public VendaDAO() {
    }
    
    private ResultSet rs = null;
    
    Statement stmt = null;
    Statement stmt1 = null;
    SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    
    public boolean inserirVenda(VendaDTO vendaDTO, TreinadorDTO treinadorDTO, JTable pokemons) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stmt1 = ConexaoDAO.con.createStatement();
            
            String comando1 = "Insert into venda (dat_vend, val_vend, id_trein) values ( "
                    + "to_date('" + date.format(vendaDTO.getDat_vend()) + "', 'DD/MM/YYYY'), "
                    + vendaDTO.getVal_vend() + ", "
                    + treinadorDTO.getId_trein()+ ")";
            
            stmt.execute(comando1.toUpperCase(), Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            rs.next();
            
            for(int cont = 0; cont < pokemons.getRowCount(); cont++) {
                String comando2 = "Insert into pokemon_venda (id_vend, id_poke, "
                        + "val_poke, qtd_poke) values ( "
                        + rs.getInt("id_vend") + ", "
                        + pokemons.getValueAt(cont, 0) + ", "
                        + pokemons.getValueAt(cont, 2) + ", "
                        + pokemons.getValueAt(cont, 3) + "); ";
                
                stmt1.execute(comando2);
            }
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            stmt1.close();
            rs.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
}
