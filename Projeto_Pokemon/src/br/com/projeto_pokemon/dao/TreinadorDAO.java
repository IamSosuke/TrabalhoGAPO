/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dao;

import java.sql.*;
import br.com.projeto_pokemon.dto.TreinadorDTO;

/**
 *
 * @author joaoo
 */
public class TreinadorDAO {
    
    public TreinadorDAO() {
    }
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirTreinador(TreinadorDTO treinadorDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into Treinador (nome_trein, rg_trein, cpf_trein) values ( "
                    + "'" + treinadorDTO.getNome_trein() + "', "
                    + "'" + treinadorDTO.getRg_trein() + "', "
                    + "'" + treinadorDTO.getCpf_trein() + "') ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean alterarTreinador(TreinadorDTO treinadorDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update treinador set "
                    + "nome_trein = '" + treinadorDTO.getNome_trein()+ "', "
                    + "rg_trein = '" + treinadorDTO.getRg_trein()+ "', "
                    + "cpf_trein = '" + treinadorDTO.getCpf_trein()+ "' "
                    + "where id_trein = " + treinadorDTO.getId_trein();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirTreinador(TreinadorDTO treinadorDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from treinador where id_trein = "
                                + treinadorDTO.getId_trein();
            
            stmt.execute(comando);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarTreinador(TreinadorDTO treinadorDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao) {
                case 1:
                    comando = "Select t.* "+
                                "from treinador t "+
                                "where nome_trein like '" + treinadorDTO.getNome_trein()+ "%' " +
                                "order by t.nome_trein";
                break;
                    
                case 2:
                    comando = "Select t.* "+
                                "from treinador t "+
                                "where t.id_trein = " + treinadorDTO.getId_trein();
                break;
                    
                case 3:
                    comando = "Select t.id_trein, t.nome_trein "+
                                "from treinador t ";
                break;    
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
}
