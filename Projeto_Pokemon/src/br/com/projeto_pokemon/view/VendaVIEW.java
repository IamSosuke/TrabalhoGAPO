/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.view;

import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import br.com.projeto_pokemon.dto.VendaDTO;
import br.com.projeto_pokemon.ctr.VendaCTR;
import br.com.projeto_pokemon.dto.PokemonDTO;
import br.com.projeto_pokemon.ctr.PokemonCTR;
import br.com.projeto_pokemon.dto.TreinadorDTO;
import br.com.projeto_pokemon.ctr.TreinadorCTR;

import java.util.Date;

/**
 *
 * @author joaoo
 */
public class VendaVIEW extends javax.swing.JInternalFrame {

    /**
     * Creates new form VendaVIEW
     */
    public VendaVIEW() {
        initComponents();
        liberaCampos(false);
        liberaBotoes(true, false, false, true);
        modelo_jtl_consultar_trein = (DefaultTableModel) jtl_consultar_treinador.getModel();
        modelo_jtl_consultar_poke = (DefaultTableModel) jtl_consultar_poke.getModel();
        modelo_jtl_consultar_poke_selecionado = (DefaultTableModel) jtl_consultar_poke_selecionado.getModel();
    }
    
    VendaCTR vendaCTR = new VendaCTR();
    VendaDTO vendaDTO = new VendaDTO();
    PokemonCTR pokemonCTR = new PokemonCTR();
    PokemonDTO pokemonDTO = new PokemonDTO();
    TreinadorCTR treinadorCTR = new TreinadorCTR();
    TreinadorDTO treinadorDTO = new TreinadorDTO();
    
    ResultSet rs;
    
    DefaultTableModel modelo_jtl_consultar_trein;
    DefaultTableModel modelo_jtl_consultar_poke;
    DefaultTableModel modelo_jtl_consultar_poke_selecionado;
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar() {
        vendaDTO.setDat_vend(new Date());
        vendaDTO.setVal_vend(Double.parseDouble(totalVenda.getText()));
        treinadorDTO.setId_trein(Integer.parseInt(String.valueOf(
            jtl_consultar_treinador.getValueAt(jtl_consultar_treinador.getSelectedRow(), 0))));
        
        JOptionPane.showMessageDialog(null, vendaCTR.inserirVenda(vendaDTO, treinadorDTO, jtl_consultar_poke_selecionado));
    }
    
    private void preencherTabelaTreinador(String nome_trein){
        try {
            modelo_jtl_consultar_trein.setNumRows(0);
            
            treinadorDTO.setNome_trein(nome_trein);
            rs = treinadorCTR.consultarTreinador(treinadorDTO, 1);
            while(rs.next()) {
                modelo_jtl_consultar_trein.addRow(new Object[] {
                    rs.getString("id_trein"),
                    rs.getString("nome_trein"),
                });
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: "+ erTab);
        }
        finally {
            pokemonCTR.CloseDB();
        }
    }
    
    private void preencherTabelaPokemon(String nome_poke){
        try {
            modelo_jtl_consultar_poke.setNumRows(0);
            
            pokemonDTO.setNome_poke(nome_poke);
            rs = pokemonCTR.consultarPokemon(pokemonDTO, 1);
            while(rs.next()) {
                modelo_jtl_consultar_poke.addRow(new Object[] {
                    rs.getString("id_poke"),
                    rs.getString("nome_poke"),
                    rs.getString("valor_poke")
                });
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: "+ erTab);
        }
        finally {
            pokemonCTR.CloseDB();
        }
    }
    
    private void adicionaPokemonSelecionado(int id_poke, String nome_poke, double val_poke) {
        try {
            modelo_jtl_consultar_poke_selecionado.addRow(new Object[] {
                id_poke,
                nome_poke,
                val_poke
            });
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
    }
    
    private void removePokemonSelecionado(int linha_selecionada) {
        try {
            if (linha_selecionada >= 0) {
                modelo_jtl_consultar_poke_selecionado.removeRow(linha_selecionada);
                calculaTotalVenda();
            }
        } catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
    }
    
    private void calculaTotalVenda() {
        try {
            double total = 0;
            for (int cont = 0; cont < jtl_consultar_poke_selecionado.getRowCount(); cont++) {
                total += (Double.parseDouble(String.valueOf(
                            jtl_consultar_poke_selecionado.getValueAt(cont, 2))) *
                            Integer.parseInt(String.valueOf(
                            jtl_consultar_poke_selecionado.getValueAt(cont, 3))));
            }
            totalVenda.setText(String.valueOf(total));
        } catch (Exception erTab) {
            System.out.println("ERRO SQL: "+erTab);
        }
    }
    
    private void liberaCampos(boolean a) {
        pesquisa_nome_treinador.setEnabled(a);
        btnPesquisarTrein.setEnabled(a);
        jtl_consultar_treinador.setEnabled(a);
        pesquisa_nome_poke.setEnabled(a);
        btnPesquisarPoke.setEnabled(a);
        jtl_consultar_poke.setEnabled(a);
        btnPokeAdd.setEnabled(a);
        btnPokeRem.setEnabled(a);
        jtl_consultar_poke_selecionado.setEnabled(a);
        totalVenda.setText("0.00");
    }
    
    private void limpaCampos() {
        pesquisa_nome_treinador.setText("");
        modelo_jtl_consultar_trein.setNumRows(0);
        pesquisa_nome_poke.setText("");
        modelo_jtl_consultar_poke.setNumRows(0);
        modelo_jtl_consultar_poke_selecionado.setNumRows(0);
    }
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnSair.setEnabled(d);
    }
    
    private boolean verificaPreenchimento() {
        if(jtl_consultar_treinador.getSelectedRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Deve ser selecionado um treinador");
            jtl_consultar_treinador.requestFocus();
            return false;
        } else {
            if (jtl_consultar_poke_selecionado.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(null, "É necessário adicionar pelo menos um pokemon no pedido");
                jtl_consultar_poke_selecionado.requestFocus();
                return false;
            } else {
                int verifica = 0;
                for (int cont = 0; cont < jtl_consultar_poke_selecionado.getRowCount(); cont++) {
                    if (String.valueOf(jtl_consultar_poke_selecionado.getValueAt(
                            cont, 3)).equalsIgnoreCase("null")){
                        verifica++;
                    }
                }
                if (verifica > 0) {
                    JOptionPane.showMessageDialog(null, "A quantidade de cada pokemon vendido deve ser informada.");
                    jtl_consultar_poke_selecionado.requestFocus();
                    return false;
                } else { 
                    return true;
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pesquisa_nome_treinador = new javax.swing.JTextField();
        btnPesquisarTrein = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_treinador = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        totalVenda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        pesquisa_nome_poke = new javax.swing.JTextField();
        btnPesquisarPoke = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_poke = new javax.swing.JTable();
        btnPokeAdd = new javax.swing.JButton();
        btnPokeRem = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtl_consultar_poke_selecionado = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Ink Free", 1, 48)); // NOI18N
        jLabel1.setText("Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setToolTipText("");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Treinador: ");

        pesquisa_nome_treinador.setNextFocusableComponent(btnPesquisarTrein);

        btnPesquisarTrein.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarTrein.setNextFocusableComponent(pesquisa_nome_poke);
        btnPesquisarTrein.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarTreinActionPerformed(evt);
            }
        });

        jtl_consultar_treinador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_treinador);
        if (jtl_consultar_treinador.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_treinador.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_treinador.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setText("Total da Venda:");

        totalVenda.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        totalVenda.setForeground(new java.awt.Color(51, 51, 255));
        totalVenda.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome_treinador, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarTrein))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(totalVenda)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(pesquisa_nome_treinador, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnPesquisarTrein)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pokemons", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Descrição:");

        pesquisa_nome_poke.setNextFocusableComponent(btnPesquisarPoke);

        btnPesquisarPoke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisarPoke.setNextFocusableComponent(btnPokeAdd);
        btnPesquisarPoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarPokeActionPerformed(evt);
            }
        });

        jtl_consultar_poke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_poke);
        if (jtl_consultar_poke.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_poke.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_poke.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_poke.getColumnModel().getColumn(2).setResizable(false);
        }

        btnPokeAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/poe.jpg"))); // NOI18N
        btnPokeAdd.setNextFocusableComponent(btnPokeRem);
        btnPokeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokeAddActionPerformed(evt);
            }
        });

        btnPokeRem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/tira.jpg"))); // NOI18N
        btnPokeRem.setNextFocusableComponent(btnNovo);
        btnPokeRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPokeRemActionPerformed(evt);
            }
        });

        jtl_consultar_poke_selecionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Valor", "QTD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_poke_selecionado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtl_consultar_poke_selecionadoKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jtl_consultar_poke_selecionado);
        if (jtl_consultar_poke_selecionado.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_poke_selecionado.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_poke_selecionado.getColumnModel().getColumn(1).setResizable(false);
            jtl_consultar_poke_selecionado.getColumnModel().getColumn(2).setResizable(false);
            jtl_consultar_poke_selecionado.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btnPokeAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnPokeRem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisa_nome_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisarPoke)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPesquisarPoke)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(pesquisa_nome_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPokeAdd)
                    .addComponent(btnPokeRem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setNextFocusableComponent(btnSalvar);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");
        btnSalvar.setNextFocusableComponent(btnCancelar);
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(btnSair);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.setNextFocusableComponent(pesquisa_nome_treinador);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(67, 67, 67)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSair)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(450, 450, 450))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        liberaBotoes(false, true, true, true);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_trein.setNumRows(0);
        modelo_jtl_consultar_poke.setNumRows(0);
        liberaBotoes(true, false, false, true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnPesquisarTreinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarTreinActionPerformed
        // TODO add your handling code here:
        preencherTabelaTreinador(pesquisa_nome_treinador.getText());
    }//GEN-LAST:event_btnPesquisarTreinActionPerformed

    private void btnPesquisarPokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarPokeActionPerformed
        // TODO add your handling code here:
        preencherTabelaPokemon(pesquisa_nome_poke.getText());
    }//GEN-LAST:event_btnPesquisarPokeActionPerformed

    private void btnPokeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokeAddActionPerformed
        // TODO add your handling code here:
        adicionaPokemonSelecionado(Integer.parseInt(String.valueOf(jtl_consultar_poke.getValueAt(
                    jtl_consultar_poke.getSelectedRow(), 0))),
                    String.valueOf(jtl_consultar_poke.getValueAt(jtl_consultar_poke.getSelectedRow(), 1)), 
                    Double.parseDouble(String.valueOf(jtl_consultar_poke.getValueAt(
                            jtl_consultar_poke.getSelectedRow(), 2))));
    }//GEN-LAST:event_btnPokeAddActionPerformed

    private void btnPokeRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPokeRemActionPerformed
        // TODO add your handling code here:
        removePokemonSelecionado(jtl_consultar_poke_selecionado.getSelectedRow());
    }//GEN-LAST:event_btnPokeRemActionPerformed

    private void jtl_consultar_poke_selecionadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtl_consultar_poke_selecionadoKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            calculaTotalVenda();
        }
    }//GEN-LAST:event_jtl_consultar_poke_selecionadoKeyReleased
    
    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(verificaPreenchimento()) {
            gravar();
            limpaCampos();
            liberaCampos(false);
            liberaBotoes(true, false, false, true);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisarPoke;
    private javax.swing.JButton btnPesquisarTrein;
    private javax.swing.JButton btnPokeAdd;
    private javax.swing.JButton btnPokeRem;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jtl_consultar_poke;
    private javax.swing.JTable jtl_consultar_poke_selecionado;
    private javax.swing.JTable jtl_consultar_treinador;
    private javax.swing.JTextField pesquisa_nome_poke;
    private javax.swing.JTextField pesquisa_nome_treinador;
    private javax.swing.JLabel totalVenda;
    // End of variables declaration//GEN-END:variables
}
