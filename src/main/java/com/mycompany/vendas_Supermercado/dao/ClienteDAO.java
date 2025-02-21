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

import com.mycompany.vendas_Supermercado.model.Cliente;
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
 * aos Clientes
 * @author user
 */
public class ClienteDAO {

    /** URL JDBC para o banco de dados. */
    static String url = "jdbc:mysql://localhost:3306/baseappvendas";
    /** Nome do banco de dados. */
    static String login = "root";
    /** A senha de login do banco de dados. */
    static String senha = "1234";
    //P@$$w0rd

     /**
     * cadastra um novo cliente no banco de dados.
     *
     * @param novoCliente O objeto Cliente que será cadastrado.
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public static boolean cadastrarCliente(Cliente novoCliente) {

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
            comandoSQL = conexao.prepareStatement("INSERT INTO cliente(nomeCliente, cpfCliente, dataNascimentoCliente, sexoCliente, estadoCivilCliente, cepCliente, logradouroCliente, numCasaCliente, telefoneCliente, emailCliente) VALUES(?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            comandoSQL.setString(1, novoCliente.getNomeCliente());
            comandoSQL.setString(2, novoCliente.getCPFcliente());
            comandoSQL.setInt(3, novoCliente.getDataNascimentoCliente());
            comandoSQL.setString(4, novoCliente.getSexoCliente());
            comandoSQL.setString(5, novoCliente.getEstadoCivilCliente());
            comandoSQL.setInt(6, novoCliente.getCEPcliente());
            comandoSQL.setString(7, novoCliente.getLogradouroCliente());
            comandoSQL.setInt(8, novoCliente.getNumCasaCliente());
            comandoSQL.setString(9, novoCliente.getTelefoneCliente());
            comandoSQL.setString(10, novoCliente.getEmailCliente());

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
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }
    
    /**
     * Verifica se o CPF ja existe no banco de dados.
     *
     * @param cpfCliente O CPF do Cliente a ser verificado.
     * @return true se o CPF ja tiver cadastrado, false caso contrário.
     */
    public static boolean buscarcpf(String cpfCliente) {

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
            comandoSQL = conexao.prepareStatement("select cliente.cpfCliente from cliente where cliente.cpfCliente = ? ");
            comandoSQL.setString(1, cpfCliente);

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    retorno = true;
                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }
    
    /**
     * Recupera um objeto Cliente do banco de dados com base no CPF fornecido.
     *
     * @param cpfCliente O CPF do Cliente a ser passado como parâmetro.
     * @return O objeto Cliente com o CPF especificado, ou um objeto Cliente vazio se não encontrado.
     */
    public static Cliente buscarClientePorCPF(String cpfCliente) {

        Cliente retorno = new Cliente();
        
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //PASSO 1 - CARREGAR O DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");

            //PASSO 2 - ABRIR A CONEXÃO COM O BANCO
            conexao = DriverManager.getConnection(url, login, senha);

            //PASSO 3 - PREPARAR O COMANDO SQL QUE SERA EXECUTADO
            comandoSQL = conexao.prepareStatement("select * from cliente where cpfCliente = ? ");
            comandoSQL.setString(1, cpfCliente);

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    retorno.setNomeCliente(rs.getString("nomeCliente"));
                    retorno.setIdCliente(rs.getInt("idCliente"));
                    retorno.setCPFcliente(rs.getString("cpfCliente"));
                    retorno.setDataNascimentoCliente(rs.getInt("dataNascimentoCliente"));
                    retorno.setSexoCliente(rs.getString("sexoCliente"));
                    retorno.setEstadoCivilCliente(rs.getString("estadoCivilCliente"));
                    retorno.setCEPcliente(rs.getInt("cepCliente"));
                    retorno.setLogradouroCliente(rs.getString("logradouroCliente"));
                    retorno.setNumCasaCliente(rs.getInt("numCasaCliente"));
                    retorno.setTelefoneCliente(rs.getString("telefoneCliente"));
                    retorno.setEmailCliente(rs.getString("emailCliente"));
                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }

    /**
     * Recupera uma lista de todos os registros de Cliente do banco de dados.
     *
     * @return Um ArrayList de objetos Cliente com todos os clientes e os seus dados no banco de dados.
     */
    public static ArrayList<Cliente> listar() {

        ArrayList<Cliente> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT * FROM cliente");

            //Passo 4 - Executar o comando SQL
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    Cliente item = new Cliente();
                    item.setIdCliente(rs.getInt("idCliente"));
                    item.setNomeCliente(rs.getString("nomeCliente"));
                    item.setCPFcliente(rs.getString("cpfCliente"));
                    item.setDataNascimentoCliente(rs.getInt("dataNascimentoCliente"));
                    item.setSexoCliente(rs.getString("sexoCliente"));
                    item.setEstadoCivilCliente(rs.getString("estadoCivilCliente"));
                    item.setCEPcliente(rs.getInt("cepCliente"));
                    item.setLogradouroCliente(rs.getString("logradouroCliente"));
                    item.setNumCasaCliente(rs.getInt("numCasaCliente"));
                    item.setTelefoneCliente(rs.getString("telefoneCliente"));
                    item.setEmailCliente(rs.getString("emailCliente"));

                    lista.add(item);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;

    }

    /**
     * Altera os dados de um Cliente no banco de dados.
     *
     * @param clienteAlterar O objeto Cliente com informações atualizadas.
     * @return true se a alteração for bem-sucedida, false caso contrário.
     */
    public static boolean alterar(Cliente clienteAlterar) {

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
            comandoSQL = conexao.prepareStatement("UPDATE cliente SET nomeCliente = ?, cpfCliente = ?, dataNascimentoCliente = ?, sexoCliente = ?, estadoCivilCliente = ?, cepCliente = ?, logradouroCliente = ?, numCasaCliente = ?, telefoneCliente = ?, emailCliente = ? WHERE idCliente = ? ");

            comandoSQL.setString(1, clienteAlterar.getNomeCliente());
            comandoSQL.setString(2, clienteAlterar.getCPFcliente());
            comandoSQL.setInt(3, clienteAlterar.getDataNascimentoCliente());
            comandoSQL.setString(4, clienteAlterar.getSexoCliente());
            comandoSQL.setString(5, clienteAlterar.getEstadoCivilCliente());
            comandoSQL.setInt(6, clienteAlterar.getCEPcliente());
            comandoSQL.setString(7, clienteAlterar.getLogradouroCliente());
            comandoSQL.setInt(8, clienteAlterar.getNumCasaCliente());
            comandoSQL.setString(9, clienteAlterar.getTelefoneCliente());
            comandoSQL.setString(10, clienteAlterar.getEmailCliente());
            comandoSQL.setInt(11, clienteAlterar.getIdCliente());

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
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return retorno;

    }

    /**
     * Exclui um Cliente do banco de dados com base no ID do cliente passado como parâmetro.
     *
     * @param idExcluirCliente O ID do Cliente a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public static boolean excluirCliente(int idExcluirCliente) {
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
            comandoSQL = conexao.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
            comandoSQL.setInt(1, idExcluirCliente);
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
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

}
