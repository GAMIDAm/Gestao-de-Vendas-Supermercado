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

import com.mycompany.vendas_Supermercado.model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por lidar com as operações do banco de dados relacionadas
 * aos Produtos
 * @author user
 */
public class ProdutoDAO {

    /** URL JDBC para o banco de dados. */
    static String url = "jdbc:mysql://localhost:3306/baseappvendas";
    /** Nome de usuario no banco de dados. */
    static String login = "root";
    /** A senha de login do banco de dados. */
    static String senha = "1234";
    //P@$$w0rd

    /**
     * Salva um novo Produto no banco de dados.
     * @param novoProduto O objeto Produto a ser salvo.
     * @return true se a inserção for bem-sucedida, false caso contrário.
     */
    public static boolean cadastrarProduto(Produto novoProduto) {

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
            comandoSQL = conexao.prepareStatement("INSERT INTO produto(nomeProduto, valorProduto, estoqueProduto) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            comandoSQL.setString(1, novoProduto.getNomeProduto());
            comandoSQL.setDouble(2, novoProduto.getValorProduto());
            comandoSQL.setInt(3, novoProduto.getEstoqueProduto());

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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }

    /**
     * Recupera uma lista de todos Produtos salvos do banco de dados.
     *
     * @return Um ArrayList de objetos Produto representando todos os produtos no banco de dados.
     */
    public static ArrayList<Produto> listar() {

        ArrayList<Produto> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT * FROM produto");

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    Produto item = new Produto();
                    item.setIdProduto(rs.getInt("idProduto"));
                    item.setNomeProduto(rs.getString("nomeProduto"));
                    item.setValorProduto(rs.getDouble("valorProduto"));
                    item.setEstoqueProduto(rs.getInt("estoqueProduto"));

                    lista.add(item);

                }

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;

    }

    /**
     *Altera os dados de um Produto existente no banco de dados.
     * @param produtoAlterar O objeto Produto com os dados a serem atualizados.
     * @return true se a alteração for bem-sucedida, false caso contrário.
     */
    public static boolean alterar(Produto produtoAlterar) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL a ser executado
            comandoSQL = conexao.prepareStatement("UPDATE produto SET nomeProduto = ?, valorProduto = ?, estoqueProduto = ?  WHERE idProduto = ? ");

            comandoSQL.setString(1, produtoAlterar.getNomeProduto());
            comandoSQL.setDouble(2, produtoAlterar.getValorProduto());
            comandoSQL.setInt(3, produtoAlterar.getEstoqueProduto());
            comandoSQL.setInt(4, produtoAlterar.getIdProduto());

            //EXECUÇÃO DO COMANDO
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return retorno;

    }

    /**
     * Exclui um Produto do banco de dados com base no ID do produto passado como parâmetro.
     * @param idExcluirProduto O ID do Produto a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public static boolean excluirProduto(int idExcluirProduto) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;
        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Passo 2 - Abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            //Passo 3 - Preparar o comando SQL a ser executado
            comandoSQL = conexao.prepareStatement("DELETE FROM produto WHERE idProduto = ?");
            comandoSQL.setInt(1, idExcluirProduto);
            //EXECUCAO DO COMANDO
            int linhasAfetadas = comandoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    /**
     * Atualiza o estoque de um Produto no banco de dados com base na quantidade fornecida.
     * @param produtoAtualizarEstoque O objeto Produto com o id identificador do produto e a quantidade a ser atualizada no estoque.
     * @return true se a atualização do estoque for bem-sucedida, false caso contrário.
     */
    public static boolean atualizarEstoque(Produto produtoAtualizarEstoque) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL a ser executado
            comandoSQL = conexao.prepareStatement("update produto set estoqueProduto = estoqueProduto-? where idProduto = ? ");

            comandoSQL.setInt(1, produtoAtualizarEstoque.getQuantidadeProduto());
            comandoSQL.setInt(2, produtoAtualizarEstoque.getIdProduto());

            //EXECUÇÃO DO COMANDO
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return retorno;

    }

}
