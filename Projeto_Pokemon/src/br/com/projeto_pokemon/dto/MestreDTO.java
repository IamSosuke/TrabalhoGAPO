/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.dto;

import java.util.Date;

/**
 *
 * @author joaoo
 */
public class MestreDTO {
    
    private String nome_mestre, rg_mestre, cpf_mestre, fama_mestre, localizacao;
    
    private Date data_nasc;

    private int id_mestre;

    public String getNome_mestre() {
        return nome_mestre;
    }

    public void setNome_mestre(String nome_mestre) {
        this.nome_mestre = nome_mestre;
    }

    public String getRg_mestre() {
        return rg_mestre;
    }

    public void setRg_mestre(String rg_mestre) {
        this.rg_mestre = rg_mestre;
    }

    public String getCpf_mestre() {
        return cpf_mestre;
    }

    public void setCpf_mestre(String cpf_mestre) {
        this.cpf_mestre = cpf_mestre;
    }

    public String getFama_mestre() {
        return fama_mestre;
    }

    public void setFama_mestre(String fama_mestre) {
        this.fama_mestre = fama_mestre;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public int getId_mestre() {
        return id_mestre;
    }

    public void setId_mestre(int id_mestre) {
        this.id_mestre = id_mestre;
    }
    
}
