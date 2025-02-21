/*
 * The MIT License
 *
 * Copyright 2023 user.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mycompany.vendas_Supermercado.dao;

import com.mycompany.vendas_Supermercado.model.VendaDetalhes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * Classe responsável por lidar com detalhes das vendas, ou seja, os produtos vendidos.
 * @author user
 */
public class VendaDetalhesDAO {

    /** URL JDBC para o banco de dados. */
    static String url = "jdbc:mysql://localhost:3306/baseappvendas";
    /** Nome de usuario no banco de dados. */
    static String login = "root";
    /** A senha de login do banco de dados. */
    static String senha = "1234";
    //P@$$w0rd

    /**
     * Para cada produto em uma venda. registra os detalhes com o preço o identificador da venda, do produto e a quantidade vendida
     * @param novaVendaDetalhes O objeto com os detalhes a serem salvos.
     * @return true se a operação for bem-sucedida, false caso contrário.
     */
    public static boolean salvarVendaDetalhes(VendaDetalhes novaVendaDetalhes) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //PASSO 1 - CARREGAR O DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");

            //PASSO 2 - ABRIR A CONEXÃO COM O BANCO
            conexao = DriverManager.getConnection(url, login, senha);

            //PASSO 3 - PREPARAR O COMANDO SQL QUE SERA EXECUTADO
            comandoSQL = conexao.prepareStatement("INSERT INTO vendadetalhes(vendaId, produtoId, quantidade) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            comandoSQL.setInt(1, novaVendaDetalhes.getIdVenda());
            comandoSQL.setInt(2, novaVendaDetalhes.getIdProduto());
            comandoSQL.setInt(3, novaVendaDetalhes.getQuantidade());

            //EXECUÇÃO DO COMANDO
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

                rs = comandoSQL.getGeneratedKeys();

                if (rs != null) {
                    while (rs.next()) {
                        int idGerado = rs.getInt(1);

                    }

                }

            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDetalhesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;

    }
    
    /**
     * Busca os detalhes de uma venda com base no ID da venda passado.
     * @param buscarId O ID da venda para buscar detalhes.
     * @return Uma lista de objetos com os detalhes de uma venda que correspondem ao ID da venda.
     */
    public static ArrayList<VendaDetalhes> buscarPorId(int buscarId) {
        
        ArrayList<VendaDetalhes> lista = new ArrayList<>();
        
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;
        
        
        try {
            
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT produto.idProduto, produto.nomeProduto, vendadetalhes.quantidade, produto.valorProduto FROM venda JOIN vendadetalhes ON venda.idVenda = vendadetalhes.vendaId JOIN produto ON  vendadetalhes.produtoId = produto.idProduto WHERE venda.idVenda = ?;");
            
            comandoSQL.setInt(1, buscarId);
            
            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    
                    VendaDetalhes item = new VendaDetalhes();
                    item.setIdProduto(rs.getInt("idProduto"));
                    item.setNomeProdutoDetalhes(rs.getString("nomeProduto"));
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setPreçoProduto(rs.getDouble("valorProduto"));

                    lista.add(item);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDetalhesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDetalhesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDetalhesDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        
        return lista;
    }
    
    /**
     * Conta o total de vendas registradas no banco de dados.
     * @return O total de vendas no banco de dados.
     */
    public static int contarTotalVendas() {
        int totalVendas = 0;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //PASSO 1 - CARREGAR O DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");

            //PASSO 2 - ABRIR A CONEXÃO COM O BANCO
            conexao = DriverManager.getConnection(url, login, senha);

            //PASSO 3 - PREPARAR O COMANDO SQL QUE SERÁ EXECUTADO
            comandoSQL = conexao.prepareStatement("SELECT COUNT(*) AS total FROM venda");

            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs.next()) {
                totalVendas = rs.getInt("total");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (comandoSQL != null) {
                try {
                    comandoSQL.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return totalVendas;
    }

}
