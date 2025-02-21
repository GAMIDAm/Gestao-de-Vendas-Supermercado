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

import static com.mycompany.vendas_Supermercado.dao.ProdutoDAO.login;
import static com.mycompany.vendas_Supermercado.dao.ProdutoDAO.senha;
import static com.mycompany.vendas_Supermercado.dao.ProdutoDAO.url;
import com.mycompany.vendas_Supermercado.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe responsável por lidar com as operações do banco de dados relacionadas
 * aos Usuários
 * @author user
 */
public class UsuarioDAO {

    /** URL JDBC para o banco de dados. */
    static String url = "jdbc:mysql://localhost:3306/baseappvendas";
    /** Nome do banco de dados. */
    static String login = "root";
    /** A senha de login do banco de dados. */
    static String senha = "1234";
    //P@$$w0rd

    /**
     * Cadastra um novo Usuário no banco de dados.
     * @param novoUsuario O objeto Usuario a ser cadastrado.
     * @return true se o cadastro for bem-sucedido, false caso contrário.
     */
    public static boolean cadastrarUsuario(Usuario novoUsuario) {

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
            comandoSQL = conexao.prepareStatement("INSERT INTO usuario(nomeUsuario, loginUsuario, senhaUsuario) VALUES(?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

            comandoSQL.setString(1, novoUsuario.getNomeUsuario());
            comandoSQL.setString(2, novoUsuario.getLoginUsuario());
            comandoSQL.setString(3, novoUsuario.getSenhaUsuario());

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
     * Altera os dados de um Usuario existente no banco de dados.
     * @param usuarioAlterar O objeto Usuario com os dados a serem atualizados.
     * @return true se a alteração for bem-sucedida, false caso contrário.
     */
    public static boolean alterar(Usuario usuarioAlterar) {

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
            comandoSQL = conexao.prepareStatement("UPDATE usuario SET nomeUsuario = ?, loginUsuario = ?, senhaUsuario = ? WHERE idUsuario = ? ");

            comandoSQL.setString(1, usuarioAlterar.getNomeUsuario());
            comandoSQL.setString(2, usuarioAlterar.getLoginUsuario());
            comandoSQL.setString(3, usuarioAlterar.getSenhaUsuario());
            comandoSQL.setInt(4, usuarioAlterar.getIdUsuario());

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
     *  Exclui um Usuario do banco de dados com base no ID do usuario passado como parâmetro.
     * @param idExcluirUsuario O ID do Usuario a ser excluído.
     * @return true se a exclusão for bem-sucedida, false caso contrário.
     */
    public static boolean excluirUsuario(int idExcluirUsuario) {

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
            comandoSQL = conexao.prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
            comandoSQL.setInt(1, idExcluirUsuario);

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
