/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dto;

/**
 *
 * @author joaoo
 */
public class TreinadorDTO {
    
    private int id_trein;
    
    private String cpf_trein, rg_trein, nome_trein;

    public String getNome_trein() {
        return nome_trein;
    }

    public void setNome_trein(String nome_trein) {
        this.nome_trein = nome_trein;
    }

    public int getId_trein() {
        return id_trein;
    }

    public void setId_trein(int id_trein) {
        this.id_trein = id_trein;
    }

    public String getCpf_trein() {
        return cpf_trein;
    }

    public void setCpf_trein(String cpf_trein) {
        this.cpf_trein = cpf_trein;
    }

    public String getRg_trein() {
        return rg_trein;
    }

    public void setRg_trein(String rg_trein) {
        this.rg_trein = rg_trein;
    }
    
}
