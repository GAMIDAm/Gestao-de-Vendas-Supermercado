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

import static com.mycompany.vendas_Supermercado.dao.UsuarioDAO.login;
import static com.mycompany.vendas_Supermercado.dao.UsuarioDAO.senha;
import static com.mycompany.vendas_Supermercado.dao.UsuarioDAO.url;
import com.mycompany.vendas_Supermercado.model.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Classe responsável por lidar com as operações do banco de dados relacionadas
 * as Vendas.
 * @author user
 */
public class VendaDAO {

    /** URL JDBC para o banco de dados. */
    static String url = "jdbc:mysql://localhost:3306/baseappvendas";
    /** Nome de usuario no banco de dados. */
    static String login = "root";
    /** A senha de login do banco de dados. */
    static String senha = "1234";
    //P@$$w0rd

    /**
     * Registra uma nova Venda no banco de dados.
     * @param novaVenda O objeto Venda a ser salvo.
     * @return retorna o ID gerado para a nova venda, ou -1 se a operação falhar.
     */
    public static int salvarVenda(Venda novaVenda) {

        int idGerado = -1;
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
            comandoSQL = conexao.prepareStatement("INSERT INTO venda(clienteId, valorTotal, dataVenda) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            comandoSQL.setInt(1, novaVenda.getClienteId());
            comandoSQL.setDouble(2, novaVenda.getValorTotal());
            Timestamp dataHoraVenda = Timestamp.valueOf(novaVenda.getDataHoraVenda());
            comandoSQL.setTimestamp(3, dataHoraVenda);

            //EXECUÇÃO DO COMANDO
            int linhasAfetadas = comandoSQL.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;

                rs = comandoSQL.getGeneratedKeys();

                if (rs != null && rs.next()) {
                    idGerado = rs.getInt(1);
                }
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

        return idGerado;

    }

    /**
     * Lista todas as vendas registradas no banco de dados.
     * @return Uma lista de objetos Venda representando todas as vendas registradas.
     */
    public static ArrayList<Venda> listarVendas() {

        ArrayList<Venda> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("select venda.idVenda, date_format(dataVenda,'%d/%m/%Y - %H:%i:%s') as dataVenda, cliente.nomeCliente, venda.valorTotal from venda join cliente on cliente.idCliente = venda.clienteId order by venda.idVenda desc;");

            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    Venda item = new Venda();
                    item.setIdVenda(rs.getInt("idVenda"));

                    item.setDataVendaFormatada(rs.getString("dataVenda"));
                    item.setNomeCliente(rs.getString("nomeCliente"));
                    item.setValorTotal(rs.getDouble("valorTotal"));

                    lista.add(item);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;
    }
    
    /**
     * Busca vendas no banco de dados com base em um intervalo de datas.
     * @param buscarInicio A data de início do intervalo.
     * @param buscarFim A data de fim do intervalo.
     * @return Uma lista de objetos Venda que correspondem ao filtro de datas.
     */
    public static ArrayList<Venda> buscarPorFiltro(String buscarInicio, String buscarFim) {

        ArrayList<Venda> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT venda.idVenda, date_format(dataVenda,'%d/%m/%Y - %H:%i:%s') as dataVenda, cliente.nomeCliente, venda.valorTotal FROM venda JOIN cliente ON cliente.idCliente = venda.clienteId WHERE dataVenda >=  ?  AND dataVenda <= ? ORDER BY venda.idVenda DESC;");
            comandoSQL.setString(1, buscarInicio);
            comandoSQL.setString(2, buscarFim);

            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    Venda item = new Venda();
                    item.setIdVenda(rs.getInt("idVenda"));

                    item.setDataVendaFormatada(rs.getString("dataVenda"));
                    item.setNomeCliente(rs.getString("nomeCliente"));
                    item.setValorTotal(rs.getDouble("valorTotal"));

                    lista.add(item);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;
    }

    /*public static ArrayList<Venda> buscarPorFiltro(String buscarMes, String buscarAno) {

        ArrayList<Venda> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {

            //Passo 1 - Carregar o Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Passo 2 - Abrir a conexao com o mysql
            conexao = DriverManager.getConnection(url, login, senha);

            //Passo 3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("select venda.idVenda, date_format(dataVenda,'%d/%m/%Y - %H:%i:%s') as dataVenda, cliente.nomeCliente, venda.valorTotal from venda join cliente on cliente.idCliente = venda.clienteId WHERE MONTH(dataVenda) = ? AND YEAR(dataVenda) = ? order by dataVenda desc;");
            comandoSQL.setString(1, buscarMes);
            comandoSQL.setString(2, buscarAno);

            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs != null) {

                //Percorrer as linhas do result set
                while (rs.next()) {
                    Venda item = new Venda();
                    item.setIdVenda(rs.getInt("idVenda"));

                    item.setDataVendaFormatada(rs.getString("dataVenda"));
                    item.setNomeCliente(rs.getString("nomeCliente"));
                    item.setValorTotal(rs.getDouble("valorTotal"));

                    lista.add(item);

                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return lista;
    }*/

    /**
     * Exclui uma Venda do banco de dados com base no ID da venda passada como parâmetro.
     * @param idExcluirVenda O ID da venda a ser excluída.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public static boolean excluirVenda(int idExcluirVenda) {
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
            comandoSQL = conexao.prepareStatement("DELETE FROM venda WHERE idVenda = ?");
            comandoSQL.setInt(1, idExcluirVenda);
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
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    public static int obterUltimoIdInserido() {
        int ultimoId = -1;

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            //PASSO 1 - CARREGAR O DRIVER
            Class.forName("com.mysql.cj.jdbc.Driver");

            //PASSO 2 - ABRIR A CONEXÃO COM O BANCO
            conexao = DriverManager.getConnection(url, login, senha);

            //PASSO 3 - PREPARAR O COMANDO SQL QUE SERÁ EXECUTADO
            comandoSQL = conexao.prepareStatement("SELECT LAST_INSERT_ID() AS ultimoId");

            //EXECUÇÃO DO COMANDO
            rs = comandoSQL.executeQuery();

            if (rs.next()) {
                ultimoId = rs.getInt("ultimoId");
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

        return ultimoId;
    }

}
