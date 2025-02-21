/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vendas_Supermercado.model;

import javax.swing.JComboBox;

/**
 *
 * @author miguel.sbaptista
 */
public class Cliente {
    
    private int idCliente;
    private String nomeCliente;
    private String cpfCliente;
    private int dataNascimentoCliente;
    private String sexoCliente;
    private String estadoCivilCliente;
    private int cepCliente;
    private String logradouroCliente;
    private int numCasaCliente;
    private String telefoneCliente;
    private String emailCliente;
    
    
    public Cliente(){       
    }
    
    public Cliente(String nomeCliente, String cpfCliente, int dataNascimentoCliente,
            String sexoCliente, String estadoCivilCliente, int cepCliente,
            String logradouroCliente, int numCasaCliente, String telefoneCliente,
            String emailCliente) {
        
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.sexoCliente = sexoCliente;
        this.estadoCivilCliente = estadoCivilCliente;
        this.cepCliente = cepCliente;
        this.logradouroCliente = logradouroCliente;
        this.numCasaCliente = numCasaCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        }
    
    public Cliente(int idCliente, String nomeCliente, String cpfCliente, int dataNascimentoCliente,
            String sexoCliente, String estadoCivilCliente, int cepCliente,
            String logradouroCliente, int numCasaCliente, String telefoneCliente,
            String emailCliente) {
        
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.dataNascimentoCliente = dataNascimentoCliente;
        this.sexoCliente = sexoCliente;
        this.estadoCivilCliente = estadoCivilCliente;
        this.cepCliente = cepCliente;
        this.logradouroCliente = logradouroCliente;
        this.numCasaCliente = numCasaCliente;
        this.telefoneCliente = telefoneCliente;
        this.emailCliente = emailCliente;
        }
    
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public String getCPFcliente() {
        return cpfCliente;
    }
    public void setCPFcliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    
    public int getDataNascimentoCliente() {
        return dataNascimentoCliente;
    }
    public void setDataNascimentoCliente(int dataNascimentoCliente) {
        this.dataNascimentoCliente = dataNascimentoCliente;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public String getEstadoCivilCliente() {
        return estadoCivilCliente;
    }

    public void setEstadoCivilCliente(String estadoCivilCliente) {
        this.estadoCivilCliente = estadoCivilCliente;
    }
    
    
    public int getCEPcliente() {
        return cepCliente;
    }
    public void setCEPcliente(int cepCliente) {
        this.cepCliente = cepCliente;
    }
    
    public String getLogradouroCliente() {
        return logradouroCliente;
    }
    public void setLogradouroCliente(String logradouroCliente) {
        this.logradouroCliente = logradouroCliente;
    }
    
    public int getNumCasaCliente() {
        return numCasaCliente;
    }
    public void setNumCasaCliente(int numCasaCliente) {
        this.numCasaCliente = numCasaCliente;
    }
    
    public String getTelefoneCliente() {
        return telefoneCliente;
    }
    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }
    
    public String getEmailCliente() {
        return emailCliente;
    }
    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return this.nomeCliente;
    }
    
    
    
}
