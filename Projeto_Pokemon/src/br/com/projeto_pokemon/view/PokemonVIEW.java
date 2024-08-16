/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_pokemon.view;

import br.com.projeto_pokemon.ctr.MestreCTR;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import br.com.projeto_pokemon.dto.PokemonDTO;
import br.com.projeto_pokemon.ctr.PokemonCTR;
import br.com.projeto_pokemon.dto.MestreDTO;

/**
 *
 * @author joaoo
 */
public class PokemonVIEW extends javax.swing.JInternalFrame {
    
    PokemonDTO pokemonDTO = new PokemonDTO();
    PokemonCTR pokemonCTR = new PokemonCTR();
    MestreDTO mestreDTO = new MestreDTO();
    MestreCTR mestreCTR = new MestreCTR();
    
    int gravar_alterar;
    ResultSet rs;
    DefaultTableModel modelo_jtl_consultar_pokemon;
    DefaultTableModel modelo_jtl_consultar_mestre;

    /**
     * Creates new form PokemonVIEW
     */
    public PokemonVIEW() {
        initComponents();
        
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_pokemon = (DefaultTableModel) jtl_consultar_pokemon.getModel();
        modelo_jtl_consultar_mestre = (DefaultTableModel) jtl_consultar_mestre_pokemon.getModel();
    }
    
    private void excluir(){
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o pokemon?", "Aviso",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, pokemonCTR.excluirPokemon(pokemonDTO));
        }
    }
    
    private void alterar(){
        try {
            pokemonDTO.setNome_poke(nome_poke.getText());
            pokemonDTO.setTipo_poke(tipo_poke.getSelectedItem().toString());
            pokemonDTO.setGeracao_poke(Integer.parseInt(geracao_poke.getSelectedItem().toString()));
            pokemonDTO.setQtdEvo_poke(Integer.parseInt(qtdEvo_poke.getText()));
            pokemonDTO.setAtaque_poke(Integer.parseInt(ataque_poke.getText()));
            pokemonDTO.setAtaqueEsp_poke(Integer.parseInt(ataqueEsp_poke.getText()));
            pokemonDTO.setDefesa_poke(Integer.parseInt(defesa_poke.getText()));
            pokemonDTO.setDefesaEsp_poke(Integer.parseInt(defesaEsp_poke.getText()));
            pokemonDTO.setAltura_poke(Float.parseFloat(altura_poke.getText()));
            pokemonDTO.setPeso_poke(Float.parseFloat(peso_poke.getText()));
            pokemonDTO.setValor_poke(Integer.parseInt(valor_poke.getText()));
            mestreDTO.setId_mestre(Integer.parseInt(String.valueOf(
                    jtl_consultar_mestre_pokemon.getValueAt(
                    jtl_consultar_mestre_pokemon.getSelectedRow(), 0))));
            
            
            JOptionPane.showMessageDialog(null, pokemonCTR.alterarPokemon(pokemonDTO, mestreDTO));
            
        }
        catch (Exception e) {
            System.out.println("Erro ao alterar" + e.getMessage());
        }
    }
    
    private void preencheCampos(int id_poke){
        try {
            pokemonDTO.setId_poke(id_poke);
            rs = pokemonCTR.consultarPokemon(pokemonDTO, 2);
            if(rs.next()) {
                limpaCampos();
                nome_poke.setText(rs.getString("nome_poke"));
                tipo_poke.setSelectedItem(rs.getString("tipo_poke"));
                geracao_poke.setSelectedItem(rs.getString("geracao_poke"));
                qtdEvo_poke.setText(rs.getString("qtdEvo_poke"));
                ataque_poke.setText(rs.getString("ataque_poke"));
                ataqueEsp_poke.setText(rs.getString("ataqueEsp_poke"));
                defesa_poke.setText(rs.getString("defesa_poke"));
                defesaEsp_poke.setText(rs.getString("defesaEsp_poke"));
                altura_poke.setText(rs.getString("altura_poke"));
                peso_poke.setText(rs.getString("peso_poke"));
                valor_poke.setText(rs.getString("valor_poke"));
                
                modelo_jtl_consultar_mestre.setNumRows(0);
                modelo_jtl_consultar_mestre.addRow(new Object[]{rs.getInt("id_mestre"), rs.getString("nome_mestre"),});
                jtl_consultar_mestre_pokemon.setRowSelectionInterval(0, 0);
                
                gravar_alterar = 2;
                liberaCampos(true);
            }
        }
        catch (Exception erTab) {
            System.out.println("Erro SQL: "+erTab);
        }
        finally {
            pokemonCTR.CloseDB();
        }
    }
    
    private void preencherTabela(String nome_poke){
        try {
            modelo_jtl_consultar_pokemon.setNumRows(0);
            
            pokemonDTO.setNome_poke(nome_poke);
            rs = pokemonCTR.consultarPokemon(pokemonDTO, 1);
            while(rs.next()) {
                modelo_jtl_consultar_pokemon.addRow(new Object[] {
                    rs.getString("id_poke"),
                    rs.getString("nome_poke"),
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
    
    private void preencherTabelaMestre(String nome_mestre){
        try {
            modelo_jtl_consultar_mestre.setNumRows(0);
            
            mestreDTO.setNome_mestre(nome_mestre);
            rs = mestreCTR.consultarMestre(mestreDTO, 1);
            while(rs.next()) {
                modelo_jtl_consultar_mestre.addRow(new Object[] {
                    rs.getString("id_mestre"),
                    rs.getString("nome_mestre"),
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
    
    private void gravar(){
        try {
            pokemonDTO.setNome_poke(nome_poke.getText());
            pokemonDTO.setTipo_poke(tipo_poke.getSelectedItem().toString());
            pokemonDTO.setGeracao_poke(Integer.parseInt(geracao_poke.getSelectedItem().toString()));
            pokemonDTO.setQtdEvo_poke(Integer.parseInt(qtdEvo_poke.getText()));
            pokemonDTO.setAtaque_poke(Integer.parseInt(ataque_poke.getText()));
            pokemonDTO.setAtaqueEsp_poke(Integer.parseInt(ataqueEsp_poke.getText()));
            pokemonDTO.setDefesa_poke(Integer.parseInt(defesa_poke.getText()));
            pokemonDTO.setDefesaEsp_poke(Integer.parseInt(defesaEsp_poke.getText()));
            pokemonDTO.setAltura_poke(Float.parseFloat(altura_poke.getText()));
            pokemonDTO.setPeso_poke(Float.parseFloat(peso_poke.getText()));
            pokemonDTO.setValor_poke(Integer.parseInt(valor_poke.getText()));
            mestreDTO.setId_mestre(Integer.parseInt(String.valueOf(
                    jtl_consultar_mestre_pokemon.getValueAt(
                    jtl_consultar_mestre_pokemon.getSelectedRow(), 0))));
            
            JOptionPane.showMessageDialog(null, pokemonCTR.inserirPokemon(pokemonDTO, mestreDTO));
        }
        catch(Exception e) {
            System.out.println("Erro ao gravar!" + e.getMessage());
        }
    }
    
    private void liberaCampos(boolean a){
        nome_poke.setEnabled(a);
        tipo_poke.setEnabled(a);
        geracao_poke.setEnabled(a);
        qtdEvo_poke.setEnabled(a);
        ataque_poke.setEnabled(a);
        ataqueEsp_poke.setEnabled(a);
        defesa_poke.setEnabled(a);
        defesaEsp_poke.setEnabled(a);
        altura_poke.setEnabled(a);
        peso_poke.setEnabled(a);
        valor_poke.setEnabled(a);
        pesquisa_mestre_poke.setEnabled(a);
        btnPesquisaMestre.setEnabled(a);
        jtl_consultar_mestre_pokemon.setEnabled(a);
    }
    
    private void limpaCampos(){
        nome_poke.setText("");
        qtdEvo_poke.setText("");
        ataque_poke.setText("");
        ataqueEsp_poke.setText("");
        defesa_poke.setText("");
        defesaEsp_poke.setText("");
        altura_poke.setText("");
        peso_poke.setText("");
        valor_poke.setText("");
        pesquisa_mestre_poke.setText("");
        modelo_jtl_consultar_mestre.setNumRows(0);
    }
    
    private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){
        btnNovo.setEnabled(a);
        btnSalvar.setEnabled(b);
        btnCancelar.setEnabled(c);
        btnExcluir.setEnabled(d);
        btnSair.setEnabled(e);
    }
    
    public void setPosicao(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nome_poke = new javax.swing.JTextField();
        tipo_poke = new javax.swing.JComboBox();
        geracao_poke = new javax.swing.JComboBox();
        qtdEvo_poke = new javax.swing.JTextField();
        altura_poke = new javax.swing.JTextField();
        peso_poke = new javax.swing.JTextField();
        ataque_poke = new javax.swing.JTextField();
        ataqueEsp_poke = new javax.swing.JTextField();
        defesa_poke = new javax.swing.JTextField();
        defesaEsp_poke = new javax.swing.JTextField();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pesquisa_nome_poke = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtl_consultar_pokemon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_mestre_pokemon = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        valor_poke = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        pesquisa_mestre_poke = new javax.swing.JTextField();
        btnPesquisaMestre = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Ink Free", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("Pokemon");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel3.setText("Tipo:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel4.setText("Geração:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel5.setText("Qtd. de Evoluções: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel6.setText("Altura:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel7.setText("Peso:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel8.setText("Ataque:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel9.setText("Ataque Esp:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel10.setText("Defesa:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel11.setText("Defesa Esp:");

        nome_poke.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, tipo_poke, org.jdesktop.beansbinding.ObjectProperty.create(), nome_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        nome_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_pokeActionPerformed(evt);
            }
        });

        tipo_poke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NORMAL", "FIGHTING", "FLYING", "POISON", "GROUND", "ROCK", "BUG", "GHOST", "STEEL", "FIRE", "WATER", "GRASS", "ELECTRIC", "PSYCHIC", "ICE", "DRAGON", "DARK", "FAIRY" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, geracao_poke, org.jdesktop.beansbinding.ObjectProperty.create(), tipo_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        tipo_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_pokeActionPerformed(evt);
            }
        });

        geracao_poke.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, qtdEvo_poke, org.jdesktop.beansbinding.ObjectProperty.create(), geracao_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, altura_poke, org.jdesktop.beansbinding.ObjectProperty.create(), qtdEvo_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        qtdEvo_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdEvo_pokeActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, peso_poke, org.jdesktop.beansbinding.ObjectProperty.create(), altura_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        altura_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altura_pokeActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ataque_poke, org.jdesktop.beansbinding.ObjectProperty.create(), peso_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        peso_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peso_pokeActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, ataqueEsp_poke, org.jdesktop.beansbinding.ObjectProperty.create(), ataque_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        ataque_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataque_pokeActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, defesa_poke, org.jdesktop.beansbinding.ObjectProperty.create(), ataqueEsp_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        ataqueEsp_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ataqueEsp_pokeActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, defesaEsp_poke, org.jdesktop.beansbinding.ObjectProperty.create(), defesa_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        defesa_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defesa_pokeActionPerformed(evt);
            }
        });

        defesaEsp_poke.setNextFocusableComponent(valor_poke);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnNovo, org.jdesktop.beansbinding.ObjectProperty.create(), defesaEsp_poke, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        defesaEsp_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defesaEsp_pokeActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/novo.png"))); // NOI18N
        btnNovo.setText("Novo");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnSalvar, org.jdesktop.beansbinding.ObjectProperty.create(), btnNovo, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/salvar.png"))); // NOI18N
        btnSalvar.setText("Salvar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnCancelar, org.jdesktop.beansbinding.ObjectProperty.create(), btnSalvar, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnExcluir, org.jdesktop.beansbinding.ObjectProperty.create(), btnCancelar, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/excluir.png"))); // NOI18N
        btnExcluir.setText("Excluir");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, btnSair, org.jdesktop.beansbinding.ObjectProperty.create(), btnExcluir, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/sair.png"))); // NOI18N
        btnSair.setText("Sair");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pesquisa_nome_poke, org.jdesktop.beansbinding.ObjectProperty.create(), btnSair, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        jLabel12.setText("Consulta");

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel13.setText("Nome:");

        pesquisa_nome_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_nome_pokeActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/pesquisar.png"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, nome_poke, org.jdesktop.beansbinding.ObjectProperty.create(), btnPesquisar, org.jdesktop.beansbinding.BeanProperty.create("nextFocusableComponent"));
        bindingGroup.addBinding(binding);

        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jtl_consultar_pokemon.setModel(new javax.swing.table.DefaultTableModel(
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
        jtl_consultar_pokemon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_pokemonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtl_consultar_pokemon);
        if (jtl_consultar_pokemon.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_pokemon.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_pokemon.getColumnModel().getColumn(1).setResizable(false);
        }

        jtl_consultar_mestre_pokemon.setModel(new javax.swing.table.DefaultTableModel(
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
        jtl_consultar_mestre_pokemon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_mestre_pokemonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_mestre_pokemon);
        if (jtl_consultar_mestre_pokemon.getColumnModel().getColumnCount() > 0) {
            jtl_consultar_mestre_pokemon.getColumnModel().getColumn(0).setResizable(false);
            jtl_consultar_mestre_pokemon.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel14.setText("Valor (R$):");

        valor_poke.setNextFocusableComponent(pesquisa_mestre_poke);
        valor_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valor_pokeActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel15.setText("Mestre Pokemon:");

        pesquisa_mestre_poke.setNextFocusableComponent(btnPesquisaMestre);
        pesquisa_mestre_poke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisa_mestre_pokeActionPerformed(evt);
            }
        });

        btnPesquisaMestre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/projeto_pokemon/view/imagens/pesquisar.png"))); // NOI18N
        btnPesquisaMestre.setNextFocusableComponent(btnNovo);
        btnPesquisaMestre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaMestreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nome_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tipo_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(geracao_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(qtdEvo_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(altura_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(peso_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(valor_poke))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ataque_poke, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                            .addComponent(defesa_poke))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(defesaEsp_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ataqueEsp_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_mestre_poke)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisaMestre, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pesquisa_nome_poke)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jLabel12)
                        .addContainerGap(169, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nome_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(tipo_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(geracao_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(qtdEvo_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(altura_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(peso_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesquisa_nome_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPesquisar))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(ataque_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(ataqueEsp_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(defesa_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(defesaEsp_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(valor_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(pesquisa_mestre_poke, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnPesquisaMestre))
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nome_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nome_pokeActionPerformed

    private void qtdEvo_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdEvo_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdEvo_pokeActionPerformed

    private void altura_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altura_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_altura_pokeActionPerformed

    private void peso_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peso_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_peso_pokeActionPerformed

    private void ataque_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ataque_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ataque_pokeActionPerformed

    private void ataqueEsp_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ataqueEsp_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ataqueEsp_pokeActionPerformed

    private void defesa_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defesa_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defesa_pokeActionPerformed

    private void defesaEsp_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defesaEsp_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defesaEsp_pokeActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
        if(gravar_alterar == 1){
            gravar();
            gravar_alterar = 0;
        }
        else {
            if (gravar_alterar == 2) {
                alterar();
                gravar_alterar = 0;
            }
            else {
                JOptionPane.showMessageDialog(null, "Erro no sistema!!!");
            }
        }
        
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        // TODO add your handling code here:
        liberaCampos(true);
        liberaBotoes(false, true, true, false, true);
        gravar_alterar = 1;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void pesquisa_nome_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_nome_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_nome_pokeActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // TODO add your handling code here:
        preencherTabela(pesquisa_nome_poke.getText());
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_pokemonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_pokemonMouseClicked
        // TODO add your handling code here:
        preencheCampos(Integer.parseInt(String.valueOf(
                jtl_consultar_pokemon.getValueAt(
                jtl_consultar_pokemon.getSelectedRow(), 0))));
        liberaBotoes(false, true, true, true, true);
    }//GEN-LAST:event_jtl_consultar_pokemonMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        excluir();
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true, false, false, false, true);
        modelo_jtl_consultar_pokemon.setNumRows(0);
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpaCampos();
        liberaCampos(false);
        modelo_jtl_consultar_pokemon.setNumRows(0);
        liberaBotoes(true, false, false, false, true);
        gravar_alterar = 0;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void jtl_consultar_mestre_pokemonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_mestre_pokemonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_mestre_pokemonMouseClicked

    private void valor_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valor_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valor_pokeActionPerformed

    private void pesquisa_mestre_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisa_mestre_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisa_mestre_pokeActionPerformed

    private void btnPesquisaMestreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaMestreActionPerformed
        // TODO add your handling code here:
        preencherTabelaMestre(pesquisa_mestre_poke.getText());
    }//GEN-LAST:event_btnPesquisaMestreActionPerformed

    private void tipo_pokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_pokeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_pokeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField altura_poke;
    private javax.swing.JTextField ataqueEsp_poke;
    private javax.swing.JTextField ataque_poke;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisaMestre;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField defesaEsp_poke;
    private javax.swing.JTextField defesa_poke;
    private javax.swing.JComboBox geracao_poke;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtl_consultar_mestre_pokemon;
    private javax.swing.JTable jtl_consultar_pokemon;
    private javax.swing.JTextField nome_poke;
    private javax.swing.JTextField peso_poke;
    private javax.swing.JTextField pesquisa_mestre_poke;
    private javax.swing.JTextField pesquisa_nome_poke;
    private javax.swing.JTextField qtdEvo_poke;
    private javax.swing.JComboBox tipo_poke;
    private javax.swing.JTextField valor_poke;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
