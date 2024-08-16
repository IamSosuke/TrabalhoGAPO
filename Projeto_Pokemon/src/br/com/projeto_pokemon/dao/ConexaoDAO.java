/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dao;

import java.sql.*;

/**
 *
 * @author joaoo
 */
public class ConexaoDAO {
    
    public static Connection con = null;
    
    public ConexaoDAO(){
    }
    
    public static void ConectDB(){
        try {
            
            String dsn = "projeto_pokemon";
            String user = "postgres";
            String senha = "postdba";
            
            DriverManager.registerDriver(new org.postgresql.Driver());
            
            String url = "jdbc:postgresql://localhost:5432/" + dsn;
            
            con = DriverManager.getConnection(url, user, senha);
            
            con.setAutoCommit(false);
            if (con == null){
                System.out.println("erro ao abrir o banco");
            }   
        }
        catch(Exception e) {
            System.out.println("Problema ao abrir a base de dados! " + e.getMessage());
        }
    }
    
    public static void CloseDB(){
        try {
            con.close();
        }
        catch(Exception e) {
            System.out.println("Problema ao fechar a base de dados! " + e.getMessage());
        }
    }
}
