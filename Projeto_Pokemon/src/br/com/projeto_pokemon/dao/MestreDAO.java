/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dao;

import java.sql.*;
import br.com.projeto_pokemon.dto.MestreDTO;
import java.text.SimpleDateFormat;

/**
 *
 * @author joaoo
 */
public class MestreDAO {
    
    public MestreDAO() {
    }
    
    SimpleDateFormat data_format = new SimpleDateFormat("dd/mm/yyyy");
    
    private ResultSet rs = null;
    
    private Statement stmt = null;
    
    public boolean inserirMestre(MestreDTO mestreDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into mestre (nome_mestre, rg_mestre, "
                    + "cpf_mestre, fama_mestre, localizacao, data_nasc) values ("
                    + "'" + mestreDTO.getNome_mestre() + "', "
                    + "'" + mestreDTO.getRg_mestre() + "', "
                    + "'" + mestreDTO.getCpf_mestre() + "', "
                    + "'" + mestreDTO.getFama_mestre() + "', "
                    + "'" + mestreDTO.getLocalizacao() + "', "
                    + "to_date('" + data_format.format(mestreDTO.getData_nasc()) + "','dd/mm/yyyy')) ";
            
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
    
    public boolean alterarMestre(MestreDTO mestreDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update mestre set "
                    + "nome_mestre = '" + mestreDTO.getNome_mestre()+ "', "
                    + "rg_mestre = '" + mestreDTO.getRg_mestre()+ "', "
                    + "cpf_mestre = '" + mestreDTO.getCpf_mestre()+ "', "
                    + "fama_mestre = '" + mestreDTO.getFama_mestre()+ "', "
                    + "localizacao = '" + mestreDTO.getLocalizacao()+ "', "
                    + "data_nasc = to_date ('" + data_format.format(mestreDTO.getData_nasc())+"','dd/mm/yyyy') "
                    + "where id_mestre = " + mestreDTO.getId_mestre();
            
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
    
    public boolean excluirMestre(MestreDTO mestreDTO) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from mestre where id_mestre  = "
                    + mestreDTO.getId_mestre();
            
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
    
    public ResultSet consultarMestre(MestreDTO mestreDTO, int opcao) {
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao) {
                case 1:
                    comando = "Select f.id_mestre, f.nome_mestre " +
                            "from mestre f " +
                            "where f.nome_mestre ilike '" + mestreDTO.getNome_mestre()+ "%' " +
                            "order by f.nome_mestre";
                break;
                case 2:
                    comando = "Select f.nome_mestre, f.rg_mestre, f.cpf_mestre, " +
                            "f.fama_mestre, f.localizacao, " +
                            "to_char(f.data_nasc, 'dd/mm/yyyy') as data_nasc " +
                            "from mestre f " +
                            "where f.id_mestre = " + mestreDTO.getId_mestre();
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
