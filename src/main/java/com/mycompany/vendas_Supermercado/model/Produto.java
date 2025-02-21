/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vendas_Supermercado.model;

/**
 *
 * @author miguel.sbaptista
 */
public class Produto {
    
    private int idProduto;
    private String nomeProduto;
    private double valorProduto;
    private int estoqueProduto;
    private int quantidadeProduto;
    
    public Produto() {
    }
    
    public Produto(String nomeProduto, double valorProduto, int estoqueProduto){
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.estoqueProduto = estoqueProduto;
    }
    
    public Produto(int idProduto, String nomeProduto, double valorProduto, int estoqueProduto){
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.estoqueProduto = estoqueProduto;
    }
    
    public int getIdProduto(){
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    public String getNomeProduto(){
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public double getValorProduto() {
        return valorProduto;
    }
    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
    
    public int getEstoqueProduto() {
        return estoqueProduto;
    }
    public void setEstoqueProduto(int estoqueProduto) {
        this.estoqueProduto = estoqueProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return this.getNomeProduto();
    }
    
    
    
    
}
