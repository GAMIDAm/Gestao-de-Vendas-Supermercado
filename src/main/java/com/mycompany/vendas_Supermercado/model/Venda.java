/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vendas_Supermercado.model;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class Venda {
    
    private int idVenda;
    private int clienteId;
    private String nomeCliente;
    private double valorTotal;
    private LocalDateTime dataHoraVenda;
    private String dataVendaFormatada;
    private String mesFiltro;
    private String anoFiltro;

    public Venda() {
    }
    
    public Venda(int clienteId, double valorTotal, LocalDateTime dataHoraVenda) {
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataHoraVenda = dataHoraVenda;
    }

    public Venda(int idVenda, int clienteId, double valorTotal, LocalDateTime dataHoraVenda) {
        this.idVenda = idVenda;
        this.clienteId = clienteId;
        this.valorTotal = valorTotal;
        this.dataHoraVenda = dataHoraVenda;
    }
    
    public Venda(int idVenda,  LocalDateTime dataHoraVenda, String nomeCliente, double valorTotal) {
        this.idVenda = idVenda;
        this.nomeCliente = nomeCliente;
        this.valorTotal = valorTotal;
        this.dataHoraVenda = dataHoraVenda;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public int getClienteId() {
        return clienteId;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataHoraVenda() {
        return dataHoraVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public void setClienteId(Cliente cliente) {
        this.clienteId = clienteId;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
        this.dataHoraVenda = dataHoraVenda;
    }

    public String getDataVendaFormatada() {
        return dataVendaFormatada;
    }

    public void setDataVendaFormatada(String dataVendaFormatada) {
        this.dataVendaFormatada = dataVendaFormatada;
    }

    public String getMesFiltro() {
        return mesFiltro;
    }

    public void setMesFiltro(String dataFiltro) {
        this.mesFiltro = dataFiltro;
    }

    public String getAnoFiltro() {
        return anoFiltro;
    }

    public void setAnoFiltro(String anoFiltro) {
        this.anoFiltro = anoFiltro;
    }
    
    
    
    
}
