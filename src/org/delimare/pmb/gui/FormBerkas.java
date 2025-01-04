/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.delimare.pmb.entity.Berkas;
import org.delimare.pmb.entitymanager.BerkasManager;
import org.delimare.pmb.function.Alert;
import org.delimare.pmb.function.Logger;
import org.delimare.pmb.function.Utils;
import org.delimare.pmb.gui.events.EventFormClosed;
import org.delimare.pmb.gui.tables.JTableBerkas;

/**
 *
 * @author DENDI
 */
public class FormBerkas extends javax.swing.JFrame {

    private BerkasManager manager;
    private JTableBerkas tableModel;
    private EventFormClosed onFormClosed;
    
    

    /**
     * Creates new form FormBerkas
     */
    public FormBerkas() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        manager = new BerkasManager();
        List<Integer> idCalonList = manager.getAllIdCalon();
        
        if (cb_idCalon != null) {
        cb_idCalon.removeAllItems();
        for (Integer idCalon : idCalonList) {
            cb_idCalon.addItem(idCalon.toString());
        }
        } else {
        Logger.error(this, "ComboBox cb_idCalon tidak terinisialisasi dengan benar");
        }

        cb_idCalon.addActionListener(e -> tampilkanNamaCalon());
        
        cb_jenis.removeAllItems();
        cb_jenis.addItem("KTP");
        cb_jenis.addItem("Ijazah");
        cb_jenis.addItem("Transkrip Nilai");
        
        setLocationRelativeTo(null);
        init();
        
    }
    
    
    
    private void tampilkanNamaCalon() {
    Integer idCalon = Integer.parseInt(cb_idCalon.getSelectedItem().toString());
    String namaCalon = manager.getNamaCalonById(idCalon);
    txtNama.setText(namaCalon);
    }
    
    public void setFormClosed(EventFormClosed formClosed) {
        this.onFormClosed = formClosed;
    }
    
    private void init() {
        tableModel = new JTableBerkas();
        manager = new BerkasManager();
        
        loadData();
    }
    
    private void loadData() {
        try {
            List<Berkas> result = manager.getSemua(txtCari.getText());
            tableModel.setList(result);
            
            tb_berkas.setModel(tableModel);
        } catch (Exception e) {
            Logger.error(this, "Gagal memuat data berkas: " + e.getMessage());
        }
    }
    private boolean inputValid() {
        if (cb_idCalon.getSelectedItem() == null || cb_idCalon.getSelectedItem().toString().trim().isEmpty()) {
        Alert.warning("Id calon harus diisi");
        return false;
        }

        
        if (txtNamafile.getText().length() == 0) {
            Alert.warning("Nama file harus diisi");
            return false;
        }
        
        if (txtPathFile.getText().length() == 0) {
            Alert.warning("path file harus diisi");
            return false;
        }
        return true;
    }
    private void zipFolder(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipFolder(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            Files.copy(file.toPath(), zos);
            zos.closeEntry();
        }
    }
    
    private void createZipFromFiles(List<String> filePaths, String zipFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (String filePath : filePaths) {
                File file = new File(filePath);
                if (file.exists()) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zos.putNextEntry(zipEntry);

                    try (FileInputStream fis = new FileInputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) >= 0) {
                            zos.write(buffer, 0, length);
                        }
                    }

                    zos.closeEntry();
                } else {
                    Logger.error(this, "File tidak ditemukan: " + filePath);
                }
            }
        }
    }

    private void clear() {
        txtidberkas.setText("");
        txtNamafile.setText("");
        txtPathFile.setText("");
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btn_browse = new javax.swing.JButton();
        cb_jenis = new javax.swing.JComboBox<>();
        txtNamafile = new javax.swing.JTextField();
        txtPathFile = new javax.swing.JTextField();
        btn_upload = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_berkas = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btn_del = new javax.swing.JButton();
        cb_idCalon = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btDownload = new javax.swing.JButton();
        txtidberkas = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 0, 204));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel32.setText("Form Berkas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(316, 316, 316)
                .addComponent(jLabel32)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("ID CALON");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("JENIS BERKAS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("NAMA FILE");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("PATH FILE");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText(":");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText(":");

        btn_browse.setText("BROWSE");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        cb_jenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_jenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_jenisMouseClicked(evt);
            }
        });
        cb_jenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_jenisActionPerformed(evt);
            }
        });

        txtNamafile.setEditable(false);

        txtPathFile.setEditable(false);

        btn_upload.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btn_upload.setText("UPLOAD");
        btn_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_uploadActionPerformed(evt);
            }
        });

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        tb_berkas.setModel(new javax.swing.table.DefaultTableModel(
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
        tb_berkas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_berkasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tb_berkas);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("CARI BERKAS");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText(":");

        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        btn_del.setText("DELETE");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        cb_idCalon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb_idCalon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cb_idCalonMouseClicked(evt);
            }
        });
        cb_idCalon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_idCalonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel11.setText("NAMA CAMABA");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel12.setText(":");

        txtNama.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("ID BERKAS");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText(":");

        btDownload.setText("Download");
        btDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDownloadActionPerformed(evt);
            }
        });

        txtidberkas.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(299, 299, 299)
                                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(btn_back)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_del)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel4))
                                                .addComponent(jLabel11))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel12))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cb_jenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtNamafile)
                                                .addComponent(txtPathFile, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cb_idCalon, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel14)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtidberkas, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(btDownload)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel10)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtidberkas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(cb_idCalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(cb_jenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7)
                            .addComponent(txtNamafile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(txtPathFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_browse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_upload, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_back)
                            .addComponent(btn_del)
                            .addComponent(btDownload))))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
       
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(null);
    
    if (result == JFileChooser.APPROVE_OPTION) {
        
        File selectedFile = fileChooser.getSelectedFile();
        
        
        txtPathFile.setText(selectedFile.getAbsolutePath());
        txtNamafile.setText(selectedFile.getName());
    }
    }//GEN-LAST:event_btn_browseActionPerformed

    private void btn_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_uploadActionPerformed
    // Tentukan folder target untuk menyimpan file
    Path targetFolderPath = Paths.get("C:", "berkas", "PBO"); 

    if (inputValid()) {
        File selectedFile = new File(txtPathFile.getText());

        if (selectedFile.exists()) {
            try {
                
                String jenis = cb_jenis.getSelectedItem().toString();
                String idCalon = cb_idCalon.getSelectedItem().toString();
                
                
                String fileExtension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
                String newFileName = jenis + "_" + idCalon + fileExtension;
                
                Path targetFilePath = targetFolderPath.resolve(newFileName);
                
                
                if (Files.exists(targetFilePath)) {
                    JOptionPane.showMessageDialog(this, "File dengan jenis '" + jenis + "' dan ID calon '" + idCalon + "' sudah ada.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else {
                    Files.copy(selectedFile.toPath(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);

                    String fixedPath = targetFilePath.toString().replace("\\", "/");
                    txtPathFile.setText(fixedPath);
                    txtNamafile.setText(newFileName);

                    manager.insert(new Berkas(
                        "", 
                        idCalon, 
                        jenis,
                        newFileName, 
                        fixedPath
                    ));

                    loadData();
                    clear();

                    JOptionPane.showMessageDialog(this, "File berhasil diunggah dengan nama: " + newFileName, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException e) {
                Logger.error(this, "Gagal menyalin file: " + e.getMessage());
            }
        } else {
            Logger.error(this, "File yang dipilih tidak ditemukan.");
        }
    }
    }//GEN-LAST:event_btn_uploadActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin kembali?",
                    "Konfirmasi",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dispose();  
            }
        
    }//GEN-LAST:event_btn_backActionPerformed

    private void cb_jenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_jenisActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_cb_jenisActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        if (txtidberkas.getText().length() == 0) {
            Alert.warning("Pilih salah satu data untuk dihapus");
            return;
        }

        try {
        
        int id = Integer.parseInt(txtidberkas.getText());

   
        int result = Alert.confirm("Yakin ingin menghapus data berkas ini?");
        if (result == JOptionPane.YES_OPTION) {
            
            manager.delete((new Berkas(
                    txtidberkas.getText(), 
                    cb_idCalon.getSelectedItem().toString(), 
                    cb_jenis.getSelectedItem().toString(),
                    txtNamafile.getText(), 
                    txtPathFile.getText() 
                )));
            loadData();  
            clear();  
        }
        
    } catch (NumberFormatException e) {
        
        Alert.warning("ID berkas harus berupa angka");
    }
    }//GEN-LAST:event_btn_delActionPerformed

    private void cb_idCalonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_idCalonActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_cb_idCalonActionPerformed

    private void tb_berkasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_berkasMouseClicked
        int row = tb_berkas.rowAtPoint(evt.getPoint());
        if (row >= 0) {
            String idBerkas = tb_berkas.getValueAt(row, 0).toString();
            String idCalon = tb_berkas.getValueAt(row, 1).toString();
            String jenisBerkas = tb_berkas.getValueAt(row, 2).toString();
            String namaFile = tb_berkas.getValueAt(row, 3).toString();
            String pathFile = tb_berkas.getValueAt(row, 4).toString();

            cb_idCalon.setSelectedItem(idCalon); 
            cb_jenis.setSelectedItem(jenisBerkas); 
            txtidberkas.setText(idBerkas);
            txtNamafile.setText(namaFile);
            txtPathFile.setText(pathFile);
    }
        
    }//GEN-LAST:event_tb_berkasMouseClicked

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        loadData();
    }//GEN-LAST:event_txtCariKeyReleased

    private void cb_idCalonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_idCalonMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_cb_idCalonMouseClicked

    private void cb_jenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cb_jenisMouseClicked
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_cb_jenisMouseClicked

    private void btDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDownloadActionPerformed
         String idCalon = cb_idCalon.getSelectedItem().toString();
         List<String> filePaths = manager.getFilePathsByIdCalon(idCalon);

         if (filePaths.isEmpty()) {
             Logger.error(this, "Tidak ada berkas untuk ID Calon: " + idCalon);
             return;
         }

         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setDialogTitle("Pilih lokasi untuk menyimpan file ZIP");
         fileChooser.setSelectedFile(new File("berkas_idCalon " + idCalon + ".zip"));

         int userSelection = fileChooser.showSaveDialog(this);

         if (userSelection == JFileChooser.APPROVE_OPTION) {
             String zipFilePath = fileChooser.getSelectedFile().getAbsolutePath();

             if (!zipFilePath.endsWith(".zip")) {
                 zipFilePath += ".zip";
             }

             try {
                    createZipFromFiles(filePaths, zipFilePath);
                } catch (IOException e) {
                    Logger.error(this, "Gagal membuat file ZIP: " + e.getMessage());
                }

         }
    }//GEN-LAST:event_btDownloadActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(FormBerkas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBerkas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBerkas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBerkas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBerkas().setVisible(true);
            }
        }); 
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDownload;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_upload;
    private javax.swing.JComboBox<String> cb_idCalon;
    private javax.swing.JComboBox<String> cb_jenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tb_berkas;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNamafile;
    private javax.swing.JTextField txtPathFile;
    private javax.swing.JTextField txtidberkas;
    // End of variables declaration//GEN-END:variables

    

    
}
