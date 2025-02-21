/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vendas_Supermercado.model;

/**
 *
 * @author miguel.sbaptista
 */
public class Relatorio {
    
    private int periodoApuracao;
    
    public Relatorio(){
    }
    
    public Relatorio(int periodoApuracao) {
        this.periodoApuracao = periodoApuracao;
    }
    
    
    
    public int getPeriodoApuracao() {
        return periodoApuracao;
    }
    public void setPeriodoApuracao(int periodoApuracao) {
        this.periodoApuracao = periodoApuracao;
    }
    
}
