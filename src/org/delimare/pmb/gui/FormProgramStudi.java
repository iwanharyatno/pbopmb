/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import org.delimare.pmb.entity.Fakultas;
import org.delimare.pmb.entity.ProgramStudi;
import org.delimare.pmb.entitymanager.FakultasManager;
import org.delimare.pmb.entitymanager.ProgramStudiManager;
import org.delimare.pmb.function.Alert;
import org.delimare.pmb.function.Logger;
import org.delimare.pmb.function.Utils;
import org.delimare.pmb.gui.events.EventFormClosed;
import org.delimare.pmb.gui.tables.JTableProgramStudi;

/**
 *
 * @author LENOVO
 */
public class FormProgramStudi extends javax.swing.JFrame {
    
    private JTableProgramStudi tableModel;
    private ProgramStudiManager manager;
    private FakultasManager fakultasManager;

    /**
     * Creates new form tambahPeserta
     */
    public FormProgramStudi() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        init();
    }
    
    private void init() {
        tableModel = new JTableProgramStudi();
        manager = new ProgramStudiManager();
        fakultasManager = new FakultasManager();
        
        loadData();
        loadComboFakultas();
    }
    
    private void loadComboFakultas() {
        try {
            List<Fakultas> result = fakultasManager.getSemua("");
            comboFakultas.removeAllItems();
            
            comboFakultas.setModel(new DefaultComboBoxModel(result.toArray()));
            
            tableProgramStudi.setModel(tableModel);
        } catch (Exception e) {
            Logger.error(this, "Gagal memuat data program studi: " + e.getMessage());
        }
    }
    
    private void loadData() {
        try {
            List<ProgramStudi> result = manager.getSemua(txtCariProdi.getText());
            tableModel.setList(result);
            
            tableProgramStudi.setModel(tableModel);
        } catch (Exception e) {
            Logger.error(this, "Gagal memuat data program studi: " + e.getMessage());
        }
    }
    
    private boolean inputValid() {
        if (txtKodeProdi.getText().length() == 0) {
            Alert.warning("Kode Prodi harus diisi");
            return false;
        }
        
        if (txtNamaProdi.getText().length() == 0) {
            Alert.warning("Nama Prodi harus diisi");
            return false;
        }
        
        if (txtKuotaProdi.getText().length() == 0) {
            Alert.warning("Kuota Prodi harus diisi");
            return false;
        }
        
        return true;
    }
    
    private void clear() {
        txtKodeProdi.setText("");
        txtNamaProdi.setText("");
        txtKuotaProdi.setText("");
        comboFakultas.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtKuotaProdi = new javax.swing.JTextField();
        txtKodeProdi = new javax.swing.JTextField();
        txtNamaProdi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboFakultas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProgramStudi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtCariProdi = new javax.swing.JTextField();
        btnBersihkan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nama Program Studi");

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jLabel3.setText("Fakultas");

        txtKuotaProdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKuotaProdiActionPerformed(evt);
            }
        });
        txtKuotaProdi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKuotaProdiKeyReleased(evt);
            }
        });

        txtKodeProdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeProdiActionPerformed(evt);
            }
        });

        txtNamaProdi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaProdiActionPerformed(evt);
            }
        });

        jLabel4.setText("Kode Prodi");

        jLabel5.setText("Kuota Prodi");

        comboFakultas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ilmu Komputer", "Bisnis dan Ilmu Sosial" }));
        comboFakultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFakultasActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 51, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 255));
        jLabel6.setText("Tambah Fakultas?");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tableProgramStudi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableProgramStudi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProgramStudiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProgramStudi);

        jLabel1.setText("Cari Prodi ");

        txtCariProdi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariProdiKeyReleased(evt);
            }
        });

        btnBersihkan.setText("Bersihkan");
        btnBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihkanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNamaProdi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(86, 86, 86)
                                    .addComponent(txtKodeProdi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtKuotaProdi)
                                    .addComponent(jLabel6)
                                    .addComponent(comboFakultas, 0, 210, Short.MAX_VALUE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSimpan)
                            .addGap(18, 18, 18)
                            .addComponent(btnHapus)
                            .addGap(18, 18, 18)
                            .addComponent(btnUbah)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBersihkan))
                        .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(14, 14, 14)
                        .addComponent(txtCariProdi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKodeProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(txtCariProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNamaProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtKuotaProdi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(comboFakultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addComponent(jLabel6)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnSimpan)
                                    .addComponent(btnHapus)
                                    .addComponent(btnUbah)
                                    .addComponent(btnBersihkan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        new FormUtama().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (inputValid()) {
            Fakultas item = (Fakultas) comboFakultas.getSelectedItem();
            manager.insert(new ProgramStudi(txtKodeProdi.getText(), txtNamaProdi.getText(), item.getKode(), Integer.parseInt(txtKuotaProdi.getText())));
            loadData();
            
            clear();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void comboFakultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFakultasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboFakultasActionPerformed

    private void txtKuotaProdiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKuotaProdiKeyReleased
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtKuotaProdiKeyReleased

    private void tableProgramStudiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProgramStudiMouseClicked
        int row = tableProgramStudi.rowAtPoint(evt.getPoint());
        
        txtKodeProdi.setText(tableProgramStudi.getValueAt(row, 0).toString());
        txtNamaProdi.setText(tableProgramStudi.getValueAt(row, 1).toString());
        Fakultas selected = new Fakultas(tableProgramStudi.getValueAt(row, 2).toString(), tableProgramStudi.getValueAt(row, 3).toString());
        comboFakultas.setSelectedItem(selected);
        txtKuotaProdi.setText(tableProgramStudi.getValueAt(row, 4).toString());
    }//GEN-LAST:event_tableProgramStudiMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        if (inputValid()) {
            Fakultas item = (Fakultas) comboFakultas.getSelectedItem();
            manager.update(new ProgramStudi(txtKodeProdi.getText(), txtNamaProdi.getText(), item.getKode(), Integer.parseInt(txtKuotaProdi.getText())));
            loadData();
            
            clear();
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        if (txtKodeProdi.getText().length() == 0) {
            Alert.warning("Pilih salah satu data untuk dihapus");
            return;
        }
        
        int result = Alert.confirm("Yakin ingin menghapus data prodi ini?");
        if (result == JOptionPane.YES_OPTION) {
            manager.delete(txtKodeProdi.getText());
            loadData();
            
            clear();
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtCariProdiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariProdiKeyReleased
        loadData();
    }//GEN-LAST:event_txtCariProdiKeyReleased

    private void btnBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihkanActionPerformed
        clear();
    }//GEN-LAST:event_btnBersihkanActionPerformed

    private void txtKodeProdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeProdiActionPerformed
        txtNamaProdi.requestFocus();
    }//GEN-LAST:event_txtKodeProdiActionPerformed

    private void txtNamaProdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaProdiActionPerformed
        txtKuotaProdi.requestFocus();
    }//GEN-LAST:event_txtNamaProdiActionPerformed

    private void txtKuotaProdiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKuotaProdiActionPerformed
        comboFakultas.requestFocus();
    }//GEN-LAST:event_txtKuotaProdiActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        FormFakultas fFakultas = new FormFakultas();
        fFakultas.setFormClosed(new EventFormClosed() {
            @Override
            public void onClosed() {
                loadComboFakultas();
            }
        });
        Utils.openFrame(this, fFakultas, false);
    }//GEN-LAST:event_jLabel6MouseClicked

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
            java.util.logging.Logger.getLogger(FormProgramStudi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormProgramStudi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormProgramStudi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormProgramStudi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormProgramStudi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBersihkan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> comboFakultas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProgramStudi;
    private javax.swing.JTextField txtCariProdi;
    private javax.swing.JTextField txtKodeProdi;
    private javax.swing.JTextField txtKuotaProdi;
    private javax.swing.JTextField txtNamaProdi;
    // End of variables declaration//GEN-END:variables

}
