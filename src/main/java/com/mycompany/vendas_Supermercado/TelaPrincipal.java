package com.mycompany.vendas_Supermercado;

import com.mycompany.vendas_Supermercado.dao.ClienteDAO;
import com.mycompany.vendas_Supermercado.dao.ProdutoDAO;
import com.mycompany.vendas_Supermercado.dao.VendaDAO;
import com.mycompany.vendas_Supermercado.dao.VendaDetalhesDAO;
import com.mycompany.vendas_Supermercado.model.Cliente;
import com.mycompany.vendas_Supermercado.model.Produto;
import com.mycompany.vendas_Supermercado.model.Venda;
import com.mycompany.vendas_Supermercado.model.VendaDetalhes;
import java.awt.Color;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class TelaPrincipal extends javax.swing.JFrame {

    Cliente objCliente = new Cliente();
    Produto objProduto = new Produto();

    private static void limparCamposCliente() {
        lblIDCliente.setText("0");
        jComboClientes.setSelectedIndex(0);
        txtAlterarNomeCliente.setText(null);
        txtAlterarCPF.setText(null);
        txtAlterarDataNascimento.setText(null);
        jComboAlterarSexo.setSelectedItem("....");
        jComboAlterarEstadoCivil.setSelectedItem("....");
        txtAlterarCEP.setText(null);
        txtAlterarLogradouro.setText(null);
        txtAlterarNumeroCasa.setText(null);
        txtAlterarTelefone.setText(null);
        txtAlterarEmail.setText(null);
        txtBuscarDadosCPF.setText(null);
    }
    

    private static void listarComboCliente() {
        ArrayList<Cliente> lista = ClienteDAO.listar();

        for (int i = jComboClientes.getItemCount() - 1; i > 0; i--) {
            jComboClientes.removeItemAt(i);
        }

        for (Cliente item : lista) {
            jComboClientes.addItem(item);
        }
    }

    private static void limparCamposProduto() {
        lblIDProduto.setText("0");
        jComboProdutos.setSelectedIndex(0);
        txtAlterarNomeProduto.setText(null);
        txtAlterarPrecoUnitario.setText(null);
        txtAlterarEstoque.setText(null);
    }

    private static void listarComboProduto() {
        ArrayList<Produto> lista = ProdutoDAO.listar();

        for (int i = jComboProdutos.getItemCount() - 1; i > 0; i--) {
            jComboProdutos.removeItemAt(i);
        }

        for (Produto item : lista) {
            jComboProdutos.addItem(item);
        }
    }

    private static void listarVendas() {
        ArrayList<Venda> lista = VendaDAO.listarVendas();

        DefaultTableModel modelo = (DefaultTableModel) jTableListaVendas.getModel();
        modelo.setRowCount(0);

        for (Venda item : lista) {

            modelo.addRow(new Object[]{
                String.valueOf(item.getIdVenda()),
                String.valueOf(item.getDataVendaFormatada()),
                String.valueOf(item.getNomeCliente()),
                String.valueOf(item.getValorTotal())
            });

        }

        //CHAMAR O METODO QUE CALCULA AS VENDAS
        calcularVendasRelatorio();
    }

    private static void calcularVendasRelatorio() {
        //CALCULO DO VALOR TOTAL DAS VENDAS PARA EXIBIR NA LABEL
        double valorTotaldasVendas = 0.0;

        for (int i = 0; i < jTableListaVendas.getRowCount(); i++) {

            String valorTotalString = (String) jTableListaVendas.getValueAt(i, 3);

            double valorTotalVenda = Double.parseDouble(valorTotalString);

            valorTotaldasVendas += valorTotalVenda;
        }

        String valorTotalFormatado = String.format("%.2f", valorTotaldasVendas);

        lblValorTotaldasVendas.setText(String.valueOf(valorTotalFormatado));
    }

    private static void listarVendasComFiltro(String inicioFiltro, String fimFiltro) {

        ArrayList<Venda> lista = VendaDAO.buscarPorFiltro(inicioFiltro, fimFiltro);

        DefaultTableModel modelo = (DefaultTableModel) jTableListaVendas.getModel();
        modelo.setRowCount(0);

        for (Venda item : lista) {

            modelo.addRow(new Object[]{
                String.valueOf(item.getIdVenda()),
                String.valueOf(item.getDataVendaFormatada()),
                String.valueOf(item.getNomeCliente()),
                String.valueOf(item.getValorTotal())
            });

        }

        calcularVendasRelatorio();
    }

    private static void limparCamposVenda() {

        jComboProdutosVenda.setSelectedIndex(0);
        txtQtdProduto.setText(null);
        lblNomeCliente.setText("");
        lblIdClienteVenda.setText("");
        txtCpfVenda.setText(null);
        DefaultTableModel modelo = (DefaultTableModel) jTableVenda.getModel();
        modelo.setRowCount(0);
        calcularValorVenda();
    }

    private static void limparCamposRelatorio() {
        txtFiltroInicio.setText(null);
        txtFiltroFim.setText(null);
        DefaultTableModel modelo = (DefaultTableModel) jTableListaVendas.getModel();
        modelo.setRowCount(0);

        calcularVendasRelatorio();
    }

    private static void calcularValorVenda() {
        double valorTotalVenda = 0.0;
        //CALCULO DO VALOR TOTAL DA VENDA
        for (int i = 0; i < jTableVenda.getRowCount(); i++) {

            double valorTotalProduto = (double) jTableVenda.getValueAt(i, 4);

            valorTotalVenda += valorTotalProduto;
        }

        String valorTotalFormatado = String.format("%.2f", valorTotalVenda);

        lblValorTotalVenda.setText(String.valueOf(valorTotalFormatado));
    }

    /**
     * Creates new form TelaVendas
     */
    public TelaPrincipal() {
        initComponents();
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/cart.png")).getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVenda = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboProdutosVenda = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        btnAdicionarProduto = new javax.swing.JButton();
        btnRemoverProduto = new javax.swing.JButton();
        txtQtdProduto = new javax.swing.JTextField();
        txtCpfVenda = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        btnBuscarCPF = new javax.swing.JButton();
        lblNomeCliente = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblIdClienteVenda = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnCancelarVenda = new javax.swing.JButton();
        btnFinalizarVenda = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        lblValorTotalVenda = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jComboClientes = new javax.swing.JComboBox();
        btnCadastroCliente = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAlterarNomeCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAlterarCPF = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlterarDataNascimento = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboAlterarSexo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboAlterarEstadoCivil = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        lblIDCliente = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtAlterarCEP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtAlterarLogradouro = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtAlterarNumeroCasa = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAlterarEmail = new javax.swing.JTextField();
        txtAlterarTelefone = new javax.swing.JFormattedTextField();
        btnAlterarCliente = new javax.swing.JButton();
        btnBuscarDadosCliente = new javax.swing.JButton();
        btnLimparDadosCliente = new javax.swing.JButton();
        btnExcluirCliente = new javax.swing.JButton();
        btnAtualizarInfosCliente = new javax.swing.JButton();
        txtBuscarDadosCPF = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        btnBuscarcomCPF = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jComboProdutos = new javax.swing.JComboBox();
        btnConfirmarBuscaProduto = new javax.swing.JButton();
        btnCadastroProduto = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtAlterarNomeProduto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAlterarPrecoUnitario = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAlterarEstoque = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblIDProduto = new javax.swing.JLabel();
        btnExcluirProduto = new javax.swing.JButton();
        btnAlterarProduto = new javax.swing.JButton();
        btnLimparDadosProduto = new javax.swing.JButton();
        btnAtualizarInfosProduto = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableListaVendas = new javax.swing.JTable();
        btnBuscarFiltro = new javax.swing.JButton();
        txtFiltroInicio = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        btnLimparRelatorio = new javax.swing.JButton();
        btnDetalhesVenda = new javax.swing.JButton();
        lblValorTotaldasVendas = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnListarVendas = new javax.swing.JButton();
        btnExcluirVenda = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtFiltroFim = new javax.swing.JFormattedTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuBTNcadastrarUsuario = new javax.swing.JMenu();
        MenuItemCadastrarUsuario = new javax.swing.JMenuItem();
        MenuItemCadastroCliente = new javax.swing.JMenuItem();
        jMenuCadastrarProduto = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnVoltarLogin = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(900, 776));

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 0, 51), java.awt.Color.red, new java.awt.Color(255, 0, 0), java.awt.Color.red));

        jTableVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Produto", "Qtd", "Valor unitário", "Valor total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVenda.setToolTipText("");
        jScrollPane2.setViewportView(jTableVenda);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Cliente:");

        jLabel15.setText("Produto:");

        jComboProdutosVenda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar Produto...." }));
        jComboProdutosVenda.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jComboProdutosVendaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel16.setText("Quantidade:");

        btnAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/adicionar.png"))); // NOI18N
        btnAdicionarProduto.setText("Adicionar Produto");
        btnAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProdutoActionPerformed(evt);
            }
        });

        btnRemoverProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/retornar.png"))); // NOI18N
        btnRemoverProduto.setText("Remover Produto");
        btnRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverProdutoActionPerformed(evt);
            }
        });

        try {
            txtCpfVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfVenda.setName("CPF"); // NOI18N

        jLabel22.setText("CPF:");

        btnBuscarCPF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/LupaAdicionar.png"))); // NOI18N
        btnBuscarCPF.setText("Buscar");
        btnBuscarCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCPFActionPerformed(evt);
            }
        });

        jLabel25.setText("Id Cliente:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQtdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(btnAdicionarProduto)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15)
                                    .addComponent(jComboProdutosVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblIdClienteVenda)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCpfVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarCPF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCpfVenda)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22)
                        .addComponent(lblNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(lblIdClienteVenda))
                .addGap(24, 24, 24)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboProdutosVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdicionarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQtdProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/X.png"))); // NOI18N
        btnCancelarVenda.setText("Cancelar");
        btnCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarVendaActionPerformed(evt);
            }
        });

        btnFinalizarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/check.png"))); // NOI18N
        btnFinalizarVenda.setText("Finalizar");
        btnFinalizarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarVendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCancelarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFinalizarVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel19.setText("Total da venda:");
        jLabel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblValorTotalVenda.setText("0,00");
        lblValorTotalVenda.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 54, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorTotalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(221, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Painel de vendas", jPanel2);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jComboClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecionar cliente..." }));
        jComboClientes.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jComboClientesAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnCadastroCliente.setText("Cadastrar novo cliente");
        btnCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroClienteActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Dados pessoais"), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(204, 0, 51))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));

        jLabel1.setText("Nome:");

        jLabel2.setText("CPF:");
        jLabel2.setPreferredSize(new java.awt.Dimension(36, 16));

        try {
            txtAlterarCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel3.setText("Data de Nascimento:");

        try {
            txtAlterarDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel4.setText("Sexo:");

        jComboAlterarSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "....", "Masculino", "Feminino" }));

        jLabel5.setText("Estado civil:");

        jComboAlterarEstadoCivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "....", "Solteiro", "Casado" }));

        jLabel18.setText("ID do Cliente:");

        lblIDCliente.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(122, 122, 122))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAlterarNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAlterarDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboAlterarEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblIDCliente)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAlterarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboAlterarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(lblIDCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlterarCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboAlterarSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlterarDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboAlterarEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel6.setText("CEP:");
        jLabel6.setMaximumSize(new java.awt.Dimension(110, 16));

        jLabel7.setText("Logradouro:");

        jLabel8.setText("Número:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlterarCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtAlterarLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlterarNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarNumeroCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));

        jLabel9.setText("Telefone:");

        jLabel10.setText("Email:");

        try {
            txtAlterarTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlterarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAlterarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        btnAlterarCliente.setText("Alterar");
        btnAlterarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarClienteActionPerformed(evt);
            }
        });

        btnBuscarDadosCliente.setText("Confirmar");
        btnBuscarDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarDadosClienteActionPerformed(evt);
            }
        });

        btnLimparDadosCliente.setText("Limpar");
        btnLimparDadosCliente.setPreferredSize(new java.awt.Dimension(84, 22));
        btnLimparDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparDadosClienteActionPerformed(evt);
            }
        });

        btnExcluirCliente.setText("Excluir");
        btnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClienteActionPerformed(evt);
            }
        });

        btnAtualizarInfosCliente.setText("Atualizar");
        btnAtualizarInfosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarInfosClienteActionPerformed(evt);
            }
        });

        try {
            txtBuscarDadosCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtBuscarDadosCPF.setName("CPF"); // NOI18N

        jLabel26.setBackground(new java.awt.Color(0, 0, 0));
        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 51));
        jLabel26.setText("CPF:");

        btnBuscarcomCPF.setText("Buscar por CPF");
        btnBuscarcomCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarcomCPFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBuscarDadosCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAtualizarInfosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnLimparDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscarDadosCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarcomCPF))
                            .addComponent(jComboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addComponent(btnCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarDadosCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(btnBuscarcomCPF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarDadosCliente)
                    .addComponent(btnAtualizarInfosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimparDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCadastroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jTabbedPane1.addTab("Clientes", jScrollPane1);

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));

        jComboProdutos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "...." }));
        jComboProdutos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jComboProdutosAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnConfirmarBuscaProduto.setText("Confirmar");
        btnConfirmarBuscaProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarBuscaProdutoActionPerformed(evt);
            }
        });

        btnCadastroProduto.setText("Cadastrar novo produto");
        btnCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroProdutoActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setText("Nome do produto:");

        jLabel12.setText("Preço unitário:");

        jLabel13.setText("Estoque:");

        jLabel17.setText("ID do Produto:");

        lblIDProduto.setText("0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtAlterarNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtAlterarEstoque, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtAlterarPrecoUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDProduto))
                    .addComponent(jLabel11))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblIDProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAlterarEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnExcluirProduto.setText("Excluir Produto");
        btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirProdutoActionPerformed(evt);
            }
        });

        btnAlterarProduto.setText("Alterar");
        btnAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarProdutoActionPerformed(evt);
            }
        });

        btnLimparDadosProduto.setText("Limpar");
        btnLimparDadosProduto.setMaximumSize(new java.awt.Dimension(84, 22));
        btnLimparDadosProduto.setMinimumSize(new java.awt.Dimension(84, 22));
        btnLimparDadosProduto.setPreferredSize(new java.awt.Dimension(84, 22));
        btnLimparDadosProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparDadosProdutoActionPerformed(evt);
            }
        });

        btnAtualizarInfosProduto.setText("Atualizar");
        btnAtualizarInfosProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarInfosProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(btnConfirmarBuscaProduto)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCadastroProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addComponent(btnExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(btnAlterarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizarInfosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnLimparDadosProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jComboProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimparDadosProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(btnConfirmarBuscaProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastroProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAlterarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnExcluirProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtualizarInfosProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produtos", jPanel6);

        jPanel12.setBackground(new java.awt.Color(51, 153, 255));

        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));

        jTableListaVendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Venda", "Data da Venda", "Cliente", "Valor Total"
            }
        ));
        jTableListaVendas.setToolTipText("");
        jTableListaVendas.setCellSelectionEnabled(true);
        jTableListaVendas.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(jTableListaVendas);
        jTableListaVendas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        btnBuscarFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/listarFiltro.png"))); // NOI18N
        btnBuscarFiltro.setText("Buscar");
        btnBuscarFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFiltroActionPerformed(evt);
            }
        });

        try {
            txtFiltroInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFiltroInicio.setToolTipText("");
        txtFiltroInicio.setName("Filtro início"); // NOI18N

        jLabel21.setText("Período de apuração");

        btnLimparRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/limparRelatorio.png"))); // NOI18N
        btnLimparRelatorio.setText("Limpar");
        btnLimparRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparRelatorioActionPerformed(evt);
            }
        });

        btnDetalhesVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/venda detalhes.png"))); // NOI18N
        btnDetalhesVenda.setText("Detalhes");
        btnDetalhesVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalhesVendaActionPerformed(evt);
            }
        });

        lblValorTotaldasVendas.setBackground(new java.awt.Color(204, 204, 204));
        lblValorTotaldasVendas.setText("0,00");
        lblValorTotaldasVendas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setText("Valor total das vendas:");
        jLabel23.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnListarVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/listar.png"))); // NOI18N
        btnListarVendas.setText("Listar Vendas");
        btnListarVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarVendasActionPerformed(evt);
            }
        });

        btnExcluirVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/excluir.png"))); // NOI18N
        btnExcluirVenda.setText("Excluir Venda");
        btnExcluirVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirVendaActionPerformed(evt);
            }
        });

        jLabel20.setText("Inicio");

        jLabel24.setText("Fim");

        try {
            txtFiltroFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFiltroFim.setName("Filtro fim"); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFiltroInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFiltroFim, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListarVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotaldasVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDetalhesVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLimparRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluirVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnListarVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFiltroInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiltroFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnExcluirVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDetalhesVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimparRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblValorTotaldasVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Relatórios", jPanel12);

        jMenuBar1.setBackground(new java.awt.Color(242, 242, 242));

        jMenuBTNcadastrarUsuario.setText("Arquivos");

        MenuItemCadastrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/cartaoCadastrar.png"))); // NOI18N
        MenuItemCadastrarUsuario.setText("Cadastrar Usuário");
        MenuItemCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemCadastrarUsuarioActionPerformed(evt);
            }
        });
        jMenuBTNcadastrarUsuario.add(MenuItemCadastrarUsuario);

        MenuItemCadastroCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/cadastrarCliente.png"))); // NOI18N
        MenuItemCadastroCliente.setText("Cadastrar  Cliente");
        MenuItemCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemCadastroClienteActionPerformed(evt);
            }
        });
        jMenuBTNcadastrarUsuario.add(MenuItemCadastroCliente);

        jMenuCadastrarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/caixa.png"))); // NOI18N
        jMenuCadastrarProduto.setText("Cadastrar Produto");
        jMenuCadastrarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCadastrarProdutoActionPerformed(evt);
            }
        });
        jMenuBTNcadastrarUsuario.add(jMenuCadastrarProduto);

        jMenuBar1.add(jMenuBTNcadastrarUsuario);

        jMenu3.setText("Edit");

        btnVoltarLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icones/door_out.png"))); // NOI18N
        btnVoltarLogin.setText("Voltar a Tela de Login");
        btnVoltarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarLoginActionPerformed(evt);
            }
        });
        jMenu3.add(btnVoltarLogin);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Sobre");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 899, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuItemCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemCadastroClienteActionPerformed

        TelaCadastrodeCliente CadastroCliente = new TelaCadastrodeCliente();
        CadastroCliente.setVisible(true);
    }//GEN-LAST:event_MenuItemCadastroClienteActionPerformed

    private void MenuItemCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemCadastrarUsuarioActionPerformed

        TelaCadastroUsuario CadastroVendedor = new TelaCadastroUsuario();
        CadastroVendedor.setVisible(true);
    }//GEN-LAST:event_MenuItemCadastrarUsuarioActionPerformed

    private void jMenuCadastrarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCadastrarProdutoActionPerformed

        TelaCadastroProduto cadastrarProduto = new TelaCadastroProduto();
        cadastrarProduto.setVisible(true);
    }//GEN-LAST:event_jMenuCadastrarProdutoActionPerformed

    private void btnVoltarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarLoginActionPerformed
        TelaLogin telalogin = new TelaLogin();
        telalogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVoltarLoginActionPerformed

    private void jComboProdutosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jComboProdutosAncestorAdded

        listarComboProduto();
    }//GEN-LAST:event_jComboProdutosAncestorAdded

    private void jComboClientesAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jComboClientesAncestorAdded
        listarComboCliente();
    }//GEN-LAST:event_jComboClientesAncestorAdded

    private void btnCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroClienteActionPerformed
        TelaCadastrodeCliente cadastroCliente = new TelaCadastrodeCliente();
        cadastroCliente.setVisible(true);
    }//GEN-LAST:event_btnCadastroClienteActionPerformed

    private void btnCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroProdutoActionPerformed
        TelaCadastroProduto cadastroProduto = new TelaCadastroProduto();
        cadastroProduto.setVisible(true);
    }//GEN-LAST:event_btnCadastroProdutoActionPerformed

    private void btnConfirmarBuscaProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarBuscaProdutoActionPerformed

        if (jComboProdutos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Produto selecionado!!");
        } else {
            Produto produto = (Produto) jComboProdutos.getSelectedItem();

            lblIDProduto.setText(String.valueOf(produto.getIdProduto()));
            txtAlterarNomeProduto.setText(produto.getNomeProduto());
            String preco = String.valueOf(produto.getValorProduto());
            preco = preco.replace(".", ",");
            txtAlterarPrecoUnitario.setText(preco);
            String estoque = String.valueOf(produto.getEstoqueProduto());
            txtAlterarEstoque.setText(estoque);
        }

    }//GEN-LAST:event_btnConfirmarBuscaProdutoActionPerformed

    private void btnBuscarDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarDadosClienteActionPerformed

        if (jComboClientes.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Nenhum Cliente selecionado!!");
        } else {
            Cliente cliente = (Cliente) jComboClientes.getSelectedItem();

            lblIDCliente.setText(String.valueOf(cliente.getIdCliente()));
            txtAlterarNomeCliente.setText(cliente.getNomeCliente());
            txtAlterarCPF.setText(cliente.getCPFcliente());
            String dataNascimento = String.valueOf(cliente.getDataNascimentoCliente());
            txtAlterarDataNascimento.setText(dataNascimento);
            //TESTE PARA CONSULTAR O SEXO E A MESMA ESTRUTURA PARA O ESTADO CIVIL
            String sexo = cliente.getSexoCliente();
            if (sexo.equalsIgnoreCase("masculino")) {
                jComboAlterarSexo.setSelectedIndex(1);
            } else {
                jComboAlterarSexo.setSelectedIndex(2);
            }
            String estadoCivil = cliente.getEstadoCivilCliente();
            if (estadoCivil.equalsIgnoreCase("solteiro")) {
                jComboAlterarEstadoCivil.setSelectedIndex(1);
            } else {
                jComboAlterarEstadoCivil.setSelectedIndex(2);
            }
            String cep = String.valueOf(cliente.getCEPcliente());
            txtAlterarCEP.setText(cep);
            txtAlterarLogradouro.setText(cliente.getLogradouroCliente());
            String numCasa = String.valueOf(cliente.getNumCasaCliente());
            txtAlterarNumeroCasa.setText(numCasa);
            String telefone = String.valueOf(cliente.getTelefoneCliente());
            txtAlterarTelefone.setText(telefone);
            txtAlterarEmail.setText(cliente.getEmailCliente());
        }
    }//GEN-LAST:event_btnBuscarDadosClienteActionPerformed

    private void btnLimparDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparDadosClienteActionPerformed

        limparCamposCliente();
    }//GEN-LAST:event_btnLimparDadosClienteActionPerformed

    private void btnLimparDadosProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparDadosProdutoActionPerformed

        limparCamposProduto();
    }//GEN-LAST:event_btnLimparDadosProdutoActionPerformed

    private void btnAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarProdutoActionPerformed

        String nomeProduto = (txtAlterarNomeProduto.getText());
        String valorString = (txtAlterarPrecoUnitario.getText());
        valorString = valorString.replaceAll(",", ".");
        double valor = Double.parseDouble(valorString);
        int estoque = Integer.parseInt(txtAlterarEstoque.getText());
        int idProduto = Integer.parseInt(lblIDProduto.getText());

        objProduto.setNomeProduto(nomeProduto);
        objProduto.setValorProduto(valor);
        objProduto.setEstoqueProduto(estoque);
        objProduto.setIdProduto(idProduto);

        boolean retorno = ProdutoDAO.alterar(objProduto);

        if (retorno) {
            JOptionPane.showMessageDialog(rootPane, "Sucesso!");
            limparCamposProduto();
            listarComboProduto();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falha!");
        }
    }//GEN-LAST:event_btnAlterarProdutoActionPerformed

    private void btnAlterarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarClienteActionPerformed

        String nome = (txtAlterarNomeCliente.getText());
        String cpf = (txtAlterarCPF.getText());
        String dataNascimentoString = (txtAlterarDataNascimento.getText());
        dataNascimentoString = dataNascimentoString.replaceAll("[^0-9]", "");
        int dataNascimento = Integer.parseInt(dataNascimentoString);
        String sexo = jComboAlterarSexo.getSelectedItem().toString();
        String estadocivil = jComboAlterarEstadoCivil.getSelectedItem().toString();
        int cep = Integer.parseInt(txtAlterarCEP.getText());
        String logradouro = (txtAlterarLogradouro.getText());
        int numcasa = Integer.parseInt(txtAlterarNumeroCasa.getText());
        String alterarTelefoneString = (txtAlterarTelefone.getText());
        alterarTelefoneString = alterarTelefoneString.replaceAll("[^0-9]", "");
        String telefone = alterarTelefoneString;
        String email = (txtAlterarEmail.getText());
        int idCliente = Integer.parseInt(lblIDCliente.getText());

        objCliente.setNomeCliente(nome);
        objCliente.setCPFcliente(cpf);
        objCliente.setDataNascimentoCliente(dataNascimento);
        objCliente.setSexoCliente(sexo);
        objCliente.setEstadoCivilCliente(estadocivil);
        objCliente.setCEPcliente(cep);
        objCliente.setLogradouroCliente(logradouro);
        objCliente.setNumCasaCliente(numcasa);
        objCliente.setTelefoneCliente(telefone);
        objCliente.setEmailCliente(email);
        objCliente.setIdCliente(idCliente);

        boolean retorno = ClienteDAO.alterar(objCliente);

        if (retorno) {
            JOptionPane.showMessageDialog(rootPane, "Sucesso!");
            limparCamposCliente();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falha!");
        }

    }//GEN-LAST:event_btnAlterarClienteActionPerformed

    private void jComboProdutosVendaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jComboProdutosVendaAncestorAdded

        ArrayList<Produto> lista = ProdutoDAO.listar();

        for (int i = jComboProdutosVenda.getItemCount() - 1; i > 0; i--) {
            jComboProdutosVenda.removeItemAt(i);
        }

        for (Produto item : lista) {
            jComboProdutosVenda.addItem(item);
        }

    }//GEN-LAST:event_jComboProdutosVendaAncestorAdded

    private void btnAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProdutoActionPerformed
        DefaultTableModel modelo = (DefaultTableModel) jTableVenda.getModel();

        Validador objValidador = new Validador();

        objValidador.ValidarJCombo(jComboProdutosVenda);
        objValidador.ValidarNumero(txtQtdProduto);

        if (objValidador.hasErro()) {
            JOptionPane.showMessageDialog(rootPane, "Produto ou qtde não selecionados!!");
        } else {
            Produto produto = (Produto) jComboProdutosVenda.getSelectedItem();

            int estoquedisponivel = produto.getEstoqueProduto();
            int quantidadeProduto = Integer.parseInt(txtQtdProduto.getText());

            //TESTE PARA VER SE O PRODUTO É MENOR OU IGUAL A ZERO
            if (quantidadeProduto > 0) {

                if (quantidadeProduto <= estoquedisponivel) {

                    int codigo = produto.getIdProduto();
                    String nomeProduto = produto.getNomeProduto();

                    double valorProduto = produto.getValorProduto();
                    double valorTotalProduto = valorProduto * quantidadeProduto;

                    //TESTE PARA VER SE O PRODUTO DA FOI ADICIONADO A VENDA
                    boolean produtoJaSelecionado = false;

                    for (int i = 0; i < modelo.getRowCount(); i++) {
                        int codigoNaTabela = (int) modelo.getValueAt(i, 0);
                        if (codigo == codigoNaTabela) {
                            produtoJaSelecionado = true;
                            break;
                        }
                    }

                    if (produtoJaSelecionado) {
                        JOptionPane.showMessageDialog(rootPane, "Este produto já foi selecionado!!");
                    } else {
                        modelo.addRow(new Object[]{
                            produto.getIdProduto(),
                            produto.getNomeProduto(),
                            quantidadeProduto,
                            valorProduto,
                            valorTotalProduto
                        });
                        jComboProdutosVenda.setSelectedIndex(0);
                        txtQtdProduto.setText("");
                    }
                    //FIM DO TESTE QUE VERIFICA SE O PRODUTO JA FOI ADICIONADO
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Quantidade selecionada maior que a disponivel no estoque!! \nEstoque Disponivel: " + estoquedisponivel);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Quantidade do produto não pode ser menor ou igual a 0");
            }

        }

        //CHAMAR O METODO PARA CALCULAR O VALOR TOTAL DA VENDA
        calcularValorVenda();

    }//GEN-LAST:event_btnAdicionarProdutoActionPerformed

    private void btnRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverProdutoActionPerformed

        int linhaSelecionada = jTableVenda.getSelectedRow();

        DefaultTableModel modelo = (DefaultTableModel) jTableVenda.getModel();

        if (linhaSelecionada >= 0) {
            modelo.removeRow(linhaSelecionada);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma linha!");
        }
        calcularValorVenda();

    }//GEN-LAST:event_btnRemoverProdutoActionPerformed

    private void btnFinalizarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarVendaActionPerformed

        Validador objValidador = new Validador();

        objValidador.ValidarJTable(jTableVenda);

        if (objValidador.hasErro() || lblNomeCliente.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, objValidador.getMensagensErro() + "Nenhum cliente selecionado!");
        } else {

            int clienteId = Integer.parseInt(lblIdClienteVenda.getText());
            String valorTotalString = (lblValorTotalVenda.getText());
            valorTotalString = valorTotalString.replace(",", ".");
            double valorTotal = Double.parseDouble(valorTotalString);
            LocalDateTime dataVenda = LocalDateTime.now();

            Venda novaVenda = new Venda(clienteId, valorTotal, dataVenda);

            //CHAMAR A DAO
            int idVenda = VendaDAO.salvarVenda(novaVenda);

            for (int i = 0; i < jTableVenda.getRowCount(); i++) {

                int produtoId = (int) jTableVenda.getValueAt(i, 0);
                int quantidade = (int) jTableVenda.getValueAt(i, 2);

                VendaDetalhes novaVendaDetalhes = new VendaDetalhes(idVenda, produtoId, quantidade);

                try {
                    boolean retornoVendaDetalhes = VendaDetalhesDAO.salvarVendaDetalhes(novaVendaDetalhes);

                    if (!retornoVendaDetalhes) {
                        System.out.println("Erro ao salvar detalhes da venda para o produto com id: " + produtoId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Erro ao salvar detalhes da venda para o produto com id: " + produtoId);
                }

                //METODO PARA DECREMENTAR O ESTOQUE
                objProduto.setQuantidadeProduto(quantidade);
                objProduto.setIdProduto(produtoId);

                boolean decrementarEstoqueProduto = ProdutoDAO.atualizarEstoque(objProduto);

                //JOptionPane.showMessageDialog(rootPane, "Venda efetuada!!\n\nValor total da Venda: " + valorTotalString);
            }
            
            JOptionPane.showMessageDialog(rootPane, "Venda efetuada!!\n\nValor total da Venda: " + valorTotalString);

            limparCamposVenda();
            calcularValorVenda();

        }

    }//GEN-LAST:event_btnFinalizarVendaActionPerformed

    private void btnBuscarFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFiltroActionPerformed

        String filtroInicio = txtFiltroInicio.getText();
        String filtroFim = txtFiltroFim.getText();

        if (filtroInicio.equalsIgnoreCase("  /  /    ") || filtroFim.equalsIgnoreCase("  /  /    ")) {
            JOptionPane.showMessageDialog(rootPane, "há Filtros não preenchidos");
        } else {

            String[] partesInicio = filtroInicio.split("/");

            String inicioFormatado = partesInicio[2] + "-" + partesInicio[1] + "-" + partesInicio[0];

            String[] partesFim = filtroFim.split("/");

            int dia = Integer.parseInt(partesFim[0]);
            dia = dia + 1;
            String diaFormatado = String.valueOf(dia);

            String fimFormatado = partesFim[2] + "-" + partesFim[1] + "-" + partesFim[0];

            listarVendasComFiltro(inicioFormatado, fimFormatado);
        }


    }//GEN-LAST:event_btnBuscarFiltroActionPerformed

    private void btnListarVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarVendasActionPerformed

        listarVendas();
    }//GEN-LAST:event_btnListarVendasActionPerformed

    private void btnDetalhesVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalhesVendaActionPerformed

        int linhaSelecionada = jTableListaVendas.getSelectedRow();

        if (linhaSelecionada >= 0) {
            //PEGO O ID DA VENDA NA LINHA SELECIONADA    
            String idVendaString = (String) jTableListaVendas.getValueAt(linhaSelecionada, 0);
            //CONVERTO O VALOR PARA A VARIAVEL INT idVenda
            int idVenda = Integer.parseInt(idVendaString);

            //CRIEI A INSTANCIA DA JFRAME VendaDetalhes.
            VendaDetalhesView detalhes = new VendaDetalhesView();

            detalhes.setVisible(true);

            /*PASSEI O idVenda COMO PARAMETRO PARA A CLASSE imprimirDetalhes da JFrame VendaDetalhes, QUE PASSA OS VALORES DOS DETALHES PARA A TABELA.
             */
            idVenda = detalhes.imprimirDetalhes(idVenda);

        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma venda!");
        }

    }//GEN-LAST:event_btnDetalhesVendaActionPerformed

    private void btnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClienteActionPerformed

        int idExcluirCliente = Integer.parseInt(lblIDCliente.getText());

        boolean retorno = ClienteDAO.excluirCliente(idExcluirCliente);
        if (retorno) {
            JOptionPane.showMessageDialog(rootPane, "Sucesso!");
            limparCamposCliente();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falha!");
        }


    }//GEN-LAST:event_btnExcluirClienteActionPerformed

    private void btnExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirProdutoActionPerformed

        int idExcluirProduto = Integer.parseInt(lblIDProduto.getText());

        boolean retorno = ProdutoDAO.excluirProduto(idExcluirProduto);
        if (retorno) {
            JOptionPane.showMessageDialog(rootPane, "Produto excluido!");
            limparCamposProduto();
            listarComboProduto();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Falha!");
        }
    }//GEN-LAST:event_btnExcluirProdutoActionPerformed

    private void btnExcluirVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirVendaActionPerformed

        int vendaSelecionada = jTableListaVendas.getSelectedRow();

        if (vendaSelecionada >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) jTableListaVendas.getModel();
            int idExcluirVenda = Integer.parseInt(modelo.getValueAt(vendaSelecionada, 0).toString());

            boolean retorno = VendaDAO.excluirVenda(idExcluirVenda);
            if (retorno) {
                JOptionPane.showMessageDialog(rootPane, "Venda excluida!");
                listarVendas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Falha!");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione uma venda!");
        }


    }//GEN-LAST:event_btnExcluirVendaActionPerformed

    private void btnAtualizarInfosClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarInfosClienteActionPerformed

        listarComboCliente();
        limparCamposCliente();
    }//GEN-LAST:event_btnAtualizarInfosClienteActionPerformed

    private void btnAtualizarInfosProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarInfosProdutoActionPerformed

        listarComboProduto();
        limparCamposProduto();
    }//GEN-LAST:event_btnAtualizarInfosProdutoActionPerformed

    private void btnCancelarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarVendaActionPerformed

        limparCamposVenda();
    }//GEN-LAST:event_btnCancelarVendaActionPerformed

    private void btnLimparRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparRelatorioActionPerformed

        limparCamposRelatorio();
    }//GEN-LAST:event_btnLimparRelatorioActionPerformed

    private void btnBuscarCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCPFActionPerformed

        if (txtCpfVenda.getText().equalsIgnoreCase("   .   .   -  ")) {
            JOptionPane.showMessageDialog(rootPane, "Campo de busca não preenchido: " + txtCpfVenda.getName());
        }
        
        else {
            boolean consultaCpf = ClienteDAO.buscarcpf(txtCpfVenda.getText());

            if (consultaCpf == true) {
                Cliente cliente = ClienteDAO.buscarClientePorCPF(txtCpfVenda.getText());

                lblNomeCliente.setText(cliente.getNomeCliente());
                String id = String.valueOf(cliente.getIdCliente());
                lblIdClienteVenda.setText(id);
            } else {
                JOptionPane.showMessageDialog(rootPane, "CPF incorreto ou não cadastrado!");
                lblNomeCliente.setText("");
                lblIdClienteVenda.setText("");
            }
        }

    }//GEN-LAST:event_btnBuscarCPFActionPerformed

    private void btnBuscarcomCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarcomCPFActionPerformed
        if (txtBuscarDadosCPF.getText().equalsIgnoreCase("   .   .   -  ")) {
            JOptionPane.showMessageDialog(rootPane, "Campo de busca não preenchido: " + txtBuscarDadosCPF.getName());
        } else {

            boolean consultaCpf = ClienteDAO.buscarcpf(txtBuscarDadosCPF.getText());

            if (consultaCpf == true) {
                Cliente cliente = ClienteDAO.buscarClientePorCPF(txtBuscarDadosCPF.getText());

                lblIDCliente.setText(String.valueOf(cliente.getIdCliente()));
                txtAlterarNomeCliente.setText(cliente.getNomeCliente());
                txtAlterarCPF.setText(cliente.getCPFcliente());
                String dataNascimento = String.valueOf(cliente.getDataNascimentoCliente());
                txtAlterarDataNascimento.setText(dataNascimento);
                //TESTE PARA CONSULTAR O SEXO E A MESMA ESTRUTURA PARA O ESTADO CIVIL
                String sexo = cliente.getSexoCliente();
                if (sexo.equalsIgnoreCase("masculino")) {
                    jComboAlterarSexo.setSelectedIndex(1);
                } else {
                    jComboAlterarSexo.setSelectedIndex(2);
                }
                String estadoCivil = cliente.getEstadoCivilCliente();
                if (estadoCivil.equalsIgnoreCase("solteiro")) {
                    jComboAlterarEstadoCivil.setSelectedIndex(1);
                } else {
                    jComboAlterarEstadoCivil.setSelectedIndex(2);
                }
                String cep = String.valueOf(cliente.getCEPcliente());
                txtAlterarCEP.setText(cep);
                txtAlterarLogradouro.setText(cliente.getLogradouroCliente());
                String numCasa = String.valueOf(cliente.getNumCasaCliente());
                txtAlterarNumeroCasa.setText(numCasa);
                String telefone = String.valueOf(cliente.getTelefoneCliente());
                txtAlterarTelefone.setText(telefone);
                txtAlterarEmail.setText(cliente.getEmailCliente());
            } else {
                JOptionPane.showMessageDialog(rootPane, "CPF incorreto ou não cadastrado!");
            }
        }
    }//GEN-LAST:event_btnBuscarcomCPFActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemCadastrarUsuario;
    private javax.swing.JMenuItem MenuItemCadastroCliente;
    private javax.swing.JButton btnAdicionarProduto;
    private javax.swing.JButton btnAlterarCliente;
    private javax.swing.JButton btnAlterarProduto;
    private javax.swing.JButton btnAtualizarInfosCliente;
    private javax.swing.JButton btnAtualizarInfosProduto;
    private javax.swing.JButton btnBuscarCPF;
    private javax.swing.JButton btnBuscarDadosCliente;
    private static javax.swing.JButton btnBuscarFiltro;
    private javax.swing.JButton btnBuscarcomCPF;
    private javax.swing.JButton btnCadastroCliente;
    private javax.swing.JButton btnCadastroProduto;
    private javax.swing.JButton btnCancelarVenda;
    private javax.swing.JButton btnConfirmarBuscaProduto;
    private javax.swing.JButton btnDetalhesVenda;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnExcluirProduto;
    private javax.swing.JButton btnExcluirVenda;
    private javax.swing.JButton btnFinalizarVenda;
    private javax.swing.JButton btnLimparDadosCliente;
    private javax.swing.JButton btnLimparDadosProduto;
    private javax.swing.JButton btnLimparRelatorio;
    private javax.swing.JButton btnListarVendas;
    private javax.swing.JButton btnRemoverProduto;
    private javax.swing.JMenuItem btnVoltarLogin;
    private static javax.swing.JComboBox<String> jComboAlterarEstadoCivil;
    private static javax.swing.JComboBox<String> jComboAlterarSexo;
    private static javax.swing.JComboBox jComboClientes;
    private static javax.swing.JComboBox jComboProdutos;
    private static javax.swing.JComboBox jComboProdutosVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenuBTNcadastrarUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuCadastrarProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private static javax.swing.JTable jTableListaVendas;
    private static javax.swing.JTable jTableVenda;
    private static javax.swing.JLabel lblIDCliente;
    private static javax.swing.JLabel lblIDProduto;
    private static javax.swing.JLabel lblIdClienteVenda;
    private static javax.swing.JLabel lblNomeCliente;
    private static javax.swing.JLabel lblValorTotalVenda;
    private static javax.swing.JLabel lblValorTotaldasVendas;
    private static javax.swing.JTextField txtAlterarCEP;
    private static javax.swing.JFormattedTextField txtAlterarCPF;
    private static javax.swing.JFormattedTextField txtAlterarDataNascimento;
    private static javax.swing.JTextField txtAlterarEmail;
    private static javax.swing.JTextField txtAlterarEstoque;
    private static javax.swing.JTextField txtAlterarLogradouro;
    private static javax.swing.JTextField txtAlterarNomeCliente;
    private static javax.swing.JTextField txtAlterarNomeProduto;
    private static javax.swing.JTextField txtAlterarNumeroCasa;
    private static javax.swing.JTextField txtAlterarPrecoUnitario;
    private static javax.swing.JFormattedTextField txtAlterarTelefone;
    private static javax.swing.JFormattedTextField txtBuscarDadosCPF;
    private static javax.swing.JFormattedTextField txtCpfVenda;
    private static javax.swing.JFormattedTextField txtFiltroFim;
    private static javax.swing.JFormattedTextField txtFiltroInicio;
    private static javax.swing.JTextField txtQtdProduto;
    // End of variables declaration//GEN-END:variables
}
