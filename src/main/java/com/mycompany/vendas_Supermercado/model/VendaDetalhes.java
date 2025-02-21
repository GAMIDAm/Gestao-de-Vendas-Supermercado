package com.mycompany.vendas_Supermercado.model;

/**
 *
 * @author user
 */
public class VendaDetalhes {
    
    private int idVenda;
    private int idProduto;
    private int quantidade;
    private double preçoProduto;
    private String nomeProdutoDetalhes;

    public VendaDetalhes() {
    }

    public VendaDetalhes(int idVenda, int idProduto, int quantidade) {
        this.idVenda = idVenda;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeProdutoDetalhes() {
        return nomeProdutoDetalhes;
    }

    public void setNomeProdutoDetalhes(String nomeProdutoDetalhes) {
        this.nomeProdutoDetalhes = nomeProdutoDetalhes;
    }

    public double getPreçoProduto() {
        return preçoProduto;
    }

    public void setPreçoProduto(double preçoProduto) {
        this.preçoProduto = preçoProduto;
    }
    
    
    
}
