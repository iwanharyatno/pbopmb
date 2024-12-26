/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.delimare.pmb.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import org.delimare.pmb.connection.Koneksi;
import org.delimare.pmb.entity.Alamat;
import org.delimare.pmb.entity.BiodataOrangTua;
import org.delimare.pmb.entity.CalonMahasiswa;
import org.delimare.pmb.entity.ProgramStudi;
import org.delimare.pmb.entity.RiwayatPendidikan;
import org.delimare.pmb.entitymanager.AlamatManager;
import org.delimare.pmb.entitymanager.BiodataOrangTuaManager;
import org.delimare.pmb.entitymanager.CalonMahasiswaManager;
import org.delimare.pmb.entitymanager.JadwalManager;
import org.delimare.pmb.entitymanager.ProgramStudiManager;
import org.delimare.pmb.entitymanager.RiwayatPendidikanManager;
import org.delimare.pmb.function.Alert;
import org.delimare.pmb.function.Logger;
import org.delimare.pmb.function.Utils;
import org.delimare.pmb.gui.events.EventFormClosed;

/**
 *
 * @author smart user
 */
public class FormTambahPeserta extends javax.swing.JFrame {

    private Koneksi db;
    private Connection conn;

    private AlamatManager alamatManager;
    private ProgramStudiManager programStudiManager;
    private CalonMahasiswaManager calonMahasiswaManager;
    private RiwayatPendidikanManager riwayatPendidikanManager;
    private BiodataOrangTuaManager biodataOrangTuaManager;
    private JadwalManager jadwalManager;

    private int calonId;
    private CalonMahasiswa calon = null;

    private EventFormClosed onFormClosed;

    /**
     * Creates new form FormTambahPeserta
     */
    public FormTambahPeserta() {
        initComponents();

        db = new Koneksi();
        alamatManager = new AlamatManager();
        conn = db.getConnection();
        programStudiManager = new ProgramStudiManager();
        calonMahasiswaManager = new CalonMahasiswaManager();
        riwayatPendidikanManager = new RiwayatPendidikanManager();
        biodataOrangTuaManager = new BiodataOrangTuaManager();
        jadwalManager = new JadwalManager();

        loadComboProgramStudi();
        
        setLocationRelativeTo(null);
    }

    public FormTambahPeserta(int calonId) {
        this();
        this.calonId = calonId;

        populateFields();
        lblJudul.setText("Ubah Data Peserta");
    }

    public void setFormClosedListener(EventFormClosed event) {
        onFormClosed = event;
    }

    private void populateFields() {
        CalonMahasiswa mhs = calonMahasiswaManager.getSingleWithRelations(calonId);
        if (mhs == null) {
            dispose();
            return;
        }
        this.calon = mhs;

        // Populate CalonMahasiswa fields
        txtIDPeserta.setText(mhs.getId() + "");
        txtNIKPeserta.setText(mhs.getNikKtp());
        txtNamaPeserta.setText(mhs.getNamaLengkap());
        txtTempatLahir.setText(mhs.getTempatLahir());
        calendarTglLahir.setDate(mhs.getTanggalLahir());
        comboAgama.setSelectedItem(mhs.getAgama());
        comboStatusPerkawinan.setSelectedItem(mhs.getStatusPerkawinan());
        txtJumlahAnak.setText(mhs.getJumlahAnak() + "");
        txtNISN.setText(mhs.getNisn());
        comboJenisKelamin.setSelectedItem(mhs.getJenisKelamnin());
        txtNoTelepon.setText(mhs.getNoTelepon());
        txtEmail.setText(mhs.getEmail());
        numTahunDaftar.setValue(mhs.getTahunDaftar());
        comboStatusPendaftaran.setSelectedItem(mhs.getStatusPendaftaran());
        txtIdCalon.setText(mhs.getId() + "");
        comboProgramStudi.setSelectedItem(calon.getProgramStudiObj());

        // Populate Alamat CalonMahasiswa fields
        Alamat alamatCalon = mhs.getAlamat();
        if (alamatCalon != null) {
            txtIdAlamat.setText(alamatCalon.getId() + "");
            txtRt.setText(alamatCalon.getRt() + "");
            txtRw.setText(alamatCalon.getRw() + "");
            txtKecamatan.setText(alamatCalon.getKecamatan());
            txtKabupaten.setText(alamatCalon.getKabupaten());
            txtProvinsi.setText(alamatCalon.getProvinsi());
            txtKodePos.setText(alamatCalon.getKodePos() + "");
            txtAlamat.setText(alamatCalon.getAlamat());
        }

        // Populate BiodataOrangTua fields
        BiodataOrangTua biodataOrtu = mhs.getBiodataOrangTua();
        if (biodataOrtu != null) {
            txtNamaAyah.setText(biodataOrtu.getNamaAyah());
            txtPendidikanAyah.setText(biodataOrtu.getPendidikanAyah());
            txtPekerjaanAyah.setText(biodataOrtu.getPekerjaanAyah());
            comboStatusAyah.setSelectedItem(biodataOrtu.getStatusAyah());
            txtNoHpAyah.setText(biodataOrtu.getNoHpAyah());
            txtNIPAyah.setText(biodataOrtu.getNipAyah());
            txtPangkatAyah.setText(biodataOrtu.getPangkatAyah());
            txtJabatanAyah.setText(biodataOrtu.getJabatanAyah());
            txtInstansiAyah.setText(biodataOrtu.getInstansiAyah());

            txtNamaIbu.setText(biodataOrtu.getNamaIbu());
            txtPendidikanIbu.setText(biodataOrtu.getPendidikanIbu());
            txtPekerjaanIbu.setText(biodataOrtu.getPekerjaanIbu());
            comboStatusIbu.setSelectedItem(biodataOrtu.getStatusIbu());
            txtNoHpIbu.setText(biodataOrtu.getNoHpIbu());
            txtNIPIbu.setText(biodataOrtu.getNipIbu());
            txtPangkatIbu.setText(biodataOrtu.getPangkatIbu());
            txtJabatanIbu.setText(biodataOrtu.getJabatanIbu());
            txtInstansiIbu.setText(biodataOrtu.getInstansiIbu());

            // Populate Alamat Ayah fields
            Alamat alamatAyah = biodataOrtu.getAlamatAyah();
            if (alamatAyah != null) {
                txtAlamatAyah.setText(alamatAyah.getAlamat());
            }

            // Populate Alamat Ibu fields
            Alamat alamatIbu = biodataOrtu.getAlamatIbu();
            if (alamatIbu != null) {
                txtAlamatIbu.setText(alamatIbu.getAlamat());
            }
        }

        ArrayList<RiwayatPendidikan> riwayatPendidikan = riwayatPendidikanManager.getDariCalon(calonId);
        this.calon.setRiwayatPendidikan(riwayatPendidikan);

        for (RiwayatPendidikan rp : riwayatPendidikan) {
            if ("SD".equals(rp.getSekolah())) {
                txtNamaSekolahSd.setText(rp.getNamaSekolah());
                txtNamaKabupatenSD.setText(rp.getKabupaten());
                txtNamaProvinsiSD.setText(rp.getProvinsi());
                numTahunLulusSD.setValue(rp.getTahunLulus());
            }
            if ("SMP".equals(rp.getSekolah())) {
                txtNamaSekolahSMP.setText(rp.getNamaSekolah());
                txtNamaKabupatenSMP.setText(rp.getKabupaten());
                txtNamaProvinsiSMP.setText(rp.getProvinsi());
                numTahunLulusSMP.setValue(rp.getTahunLulus());
            }
            if ("SMA".equals(rp.getSekolah())) {
                txtNamaSekolahSMA.setText(rp.getNamaSekolah());
                txtNamaKabupatenSMA.setText(rp.getKabupaten());
                txtNamaProvinsiSMA.setText(rp.getProvinsi());
                numTahunLulusSMA.setValue(rp.getTahunLulus());
            }
        }
    }

    private boolean inputValid() {

        if (txtNIKPeserta.getText().length() == 0) {
            Alert.warning("Kolom NIK Peserta wajib diisi");
            return false;
        }

        if (txtNamaPeserta.getText().length() == 0) {
            Alert.warning("Kolom Nama Peserta wajib diisi");
            return false;
        }

        if (txtAlamat.getText().length() == 0) {
            Alert.warning("Kolom Alamat wajib diisi");
            return false;
        }

        if (txtNamaAyah.getText().length() == 0) {
            Alert.warning("Kolom Nama Ayah wajib diisi");
            return false;
        }

        if (txtAlamatAyah.getText().length() == 0) {
            Alert.warning("Kolom Alamat Ayah wajib diisi");
            return false;
        }

        if (txtNamaIbu.getText().length() == 0) {
            Alert.warning("Kolom Nama Ibu wajib diisi");
            return false;
        }

        if (txtAlamatIbu.getText().length() == 0) {
            Alert.warning("Kolom Alamat Ibu wajib diisi");
            return false;
        }

        return true;
    }

    private void loadComboProgramStudi() {
        ArrayList<ProgramStudi> programStudi = programStudiManager.getSemua("");
        comboProgramStudi.setModel(new DefaultComboBoxModel(programStudi.toArray()));
    }

    private void toNextField(java.awt.event.KeyEvent evt, javax.swing.JComponent next) {
        int code = (int) evt.getKeyChar();
        if ((code == 9 || code == 10) && evt.isControlDown()) {
            next.requestFocus();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtIDPeserta = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNIKPeserta = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNamaPeserta = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNISN = new javax.swing.JTextPane();
        calendarTglLahir = new com.toedter.calendar.JCalendar();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboStatusPerkawinan = new javax.swing.JComboBox();
        comboAgama = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtTempatLahir = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtJumlahAnak = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtEmail = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtNoTelepon = new javax.swing.JTextPane();
        comboStatusPendaftaran = new javax.swing.JComboBox();
        comboProgramStudi = new javax.swing.JComboBox();
        numTahunDaftar = new com.toedter.calendar.JYearChooser();
        comboJenisKelamin = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtIdCalon = new javax.swing.JTextPane();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtNamaAyah = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtAlamatAyah = new javax.swing.JTextPane();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtPangkatAyah = new javax.swing.JTextPane();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        comboStatusAyah = new javax.swing.JComboBox();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtPendidikanAyah = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtJabatanAyah = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        txtNIPAyah = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtInstansiAyah = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtPekerjaanAyah = new javax.swing.JTextPane();
        jScrollPane22 = new javax.swing.JScrollPane();
        txtNoHpAyah = new javax.swing.JTextPane();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        txtNoHpIbu = new javax.swing.JTextPane();
        jScrollPane18 = new javax.swing.JScrollPane();
        txtNamaIbu = new javax.swing.JTextPane();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtAlamatIbu = new javax.swing.JTextPane();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtPendidikanIbu = new javax.swing.JTextPane();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        txtJabatanIbu = new javax.swing.JTextPane();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        txtNIPIbu = new javax.swing.JTextPane();
        comboStatusIbu = new javax.swing.JComboBox();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtPekerjaanIbu = new javax.swing.JTextPane();
        jScrollPane30 = new javax.swing.JScrollPane();
        txtInstansiIbu = new javax.swing.JTextPane();
        jScrollPane31 = new javax.swing.JScrollPane();
        txtPangkatIbu = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtNamaSekolahSd = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        numTahunLulusSD = new com.toedter.calendar.JYearChooser();
        jTextField5 = new javax.swing.JTextField();
        txtNamaSekolahSMP = new javax.swing.JTextField();
        numTahunLulusSMP = new com.toedter.calendar.JYearChooser();
        jTextField8 = new javax.swing.JTextField();
        txtNamaSekolahSMA = new javax.swing.JTextField();
        numTahunLulusSMA = new com.toedter.calendar.JYearChooser();
        txtNamaKabupatenSD = new javax.swing.JTextField();
        txtNamaProvinsiSD = new javax.swing.JTextField();
        txtNamaKabupatenSMP = new javax.swing.JTextField();
        txtNamaKabupatenSMA = new javax.swing.JTextField();
        txtNamaProvinsiSMP = new javax.swing.JTextField();
        txtNamaProvinsiSMA = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtIdAlamat = new javax.swing.JTextPane();
        jLabel48 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtRt = new javax.swing.JTextPane();
        jLabel49 = new javax.swing.JLabel();
        jScrollPane32 = new javax.swing.JScrollPane();
        txtRw = new javax.swing.JTextPane();
        jLabel50 = new javax.swing.JLabel();
        jScrollPane33 = new javax.swing.JScrollPane();
        txtKecamatan = new javax.swing.JTextPane();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane34 = new javax.swing.JScrollPane();
        txtKabupaten = new javax.swing.JTextPane();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane36 = new javax.swing.JScrollPane();
        txtProvinsi = new javax.swing.JTextPane();
        jScrollPane37 = new javax.swing.JScrollPane();
        txtKodePos = new javax.swing.JTextPane();
        jLabel55 = new javax.swing.JLabel();
        jScrollPane38 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextPane();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lblJudul = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnKeluar = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnKelolaBerkas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Biodata Peserta");

        txtIDPeserta.setEditable(false);
        jScrollPane1.setViewportView(txtIDPeserta);

        jLabel2.setText("ID PESERTA");

        jLabel3.setText("NIK");

        txtNIKPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNIKPesertaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtNIKPeserta);

        txtNamaPeserta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaPesertaKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(txtNamaPeserta);

        jLabel4.setText("NAMA");

        jLabel5.setText("TEMPAT LAHIR");

        jLabel6.setText("TANGGAL LAHIR");

        txtNISN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNISNKeyTyped(evt);
            }
        });
        jScrollPane5.setViewportView(txtNISN);

        jLabel7.setText("AGAMA");

        jLabel8.setText("STATUS PERKAWINAN");

        jLabel9.setText("JUMLAH ANAK");

        jLabel10.setText("NISN");

        jLabel11.setText("JENIS KELAMIN");

        jLabel12.setText("NO TELEPON");

        jLabel13.setText("EMAIL");

        jLabel14.setText("TAHUN DAFTAR");

        jLabel15.setText("STATUS PENDAFTARAN");

        jLabel16.setText("PROGRAM STUDI");

        comboStatusPerkawinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM MENIKAH", "MENIKAH", "JANDA", "DUDA" }));
        comboStatusPerkawinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboStatusPerkawinanKeyTyped(evt);
            }
        });

        comboAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Islam", "Kristen", "Katolik", "Kristen Protestan", "Hindu", "Buddha" }));
        comboAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboAgamaKeyTyped(evt);
            }
        });

        txtTempatLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTempatLahirKeyTyped(evt);
            }
        });
        jScrollPane7.setViewportView(txtTempatLahir);

        txtJumlahAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJumlahAnakKeyTyped(evt);
            }
        });
        jScrollPane9.setViewportView(txtJumlahAnak);

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEmailKeyTyped(evt);
            }
        });
        jScrollPane10.setViewportView(txtEmail);

        txtNoTelepon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoTeleponKeyTyped(evt);
            }
        });
        jScrollPane12.setViewportView(txtNoTelepon);

        comboStatusPendaftaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Lulus", "Tidak Lulus" }));
        comboStatusPendaftaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboStatusPendaftaranKeyTyped(evt);
            }
        });

        comboProgramStudi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboProgramStudi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboProgramStudiKeyTyped(evt);
            }
        });

        numTahunDaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numTahunDaftarKeyTyped(evt);
            }
        });

        comboJenisKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-laki", "Perempuan" }));
        comboJenisKelamin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboJenisKelaminKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calendarTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboAgama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboStatusPerkawinan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(comboJenisKelamin, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(numTahunDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboProgramStudi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboStatusPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calendarTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboAgama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8))
                    .addComponent(comboStatusPerkawinan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboJenisKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(comboProgramStudi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(comboStatusPendaftaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(numTahunDaftar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel18.setText("BIODATA AYAH");

        txtIdCalon.setEditable(false);
        jScrollPane4.setViewportView(txtIdCalon);

        jLabel19.setText("ID CALON");

        jLabel20.setText("NAMA AYAH ");

        txtNamaAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaAyahKeyTyped(evt);
            }
        });
        jScrollPane6.setViewportView(txtNamaAyah);

        txtAlamatAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatAyahKeyTyped(evt);
            }
        });
        jScrollPane13.setViewportView(txtAlamatAyah);

        jLabel21.setText("ALAMAT AYAH");

        jLabel22.setText("PENDIDIKAN AYAH");

        txtPangkatAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPangkatAyahKeyTyped(evt);
            }
        });
        jScrollPane14.setViewportView(txtPangkatAyah);

        jLabel24.setText("PEKERJAAN AYAH");

        jLabel25.setText("STATUS");

        jLabel26.setText("NO HP AYAH");

        jLabel27.setText("NIP  AYAH");

        jLabel28.setText("PANGKAT AYAH");

        jLabel29.setText("JABATAN AYAH");

        jLabel30.setText("INSTANSI AYAH");

        comboStatusAyah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Pensiunan", "Lainnya" }));
        comboStatusAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboStatusAyahKeyTyped(evt);
            }
        });

        txtPendidikanAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendidikanAyahKeyTyped(evt);
            }
        });
        jScrollPane15.setViewportView(txtPendidikanAyah);

        txtJabatanAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJabatanAyahKeyTyped(evt);
            }
        });
        jScrollPane16.setViewportView(txtJabatanAyah);

        txtNIPAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNIPAyahKeyTyped(evt);
            }
        });
        jScrollPane17.setViewportView(txtNIPAyah);

        txtInstansiAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInstansiAyahKeyTyped(evt);
            }
        });
        jScrollPane20.setViewportView(txtInstansiAyah);

        txtPekerjaanAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPekerjaanAyahKeyTyped(evt);
            }
        });
        jScrollPane21.setViewportView(txtPekerjaanAyah);

        txtNoHpAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoHpAyahKeyTyped(evt);
            }
        });
        jScrollPane22.setViewportView(txtNoHpAyah);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel23.setText("BIODATA IBU");

        txtNoHpIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNoHpIbuKeyTyped(evt);
            }
        });
        jScrollPane24.setViewportView(txtNoHpIbu);

        txtNamaIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaIbuKeyTyped(evt);
            }
        });
        jScrollPane18.setViewportView(txtNamaIbu);

        jLabel31.setText("NAMA IBU");

        jLabel33.setText("ALAMAT IBU");

        txtAlamatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatIbuKeyTyped(evt);
            }
        });
        jScrollPane25.setViewportView(txtAlamatIbu);

        txtPendidikanIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPendidikanIbuKeyTyped(evt);
            }
        });
        jScrollPane26.setViewportView(txtPendidikanIbu);

        jLabel35.setText("PENDIDIKAN IBU");

        jLabel36.setText("PEKERJAAN IBU");

        txtJabatanIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtJabatanIbuKeyTyped(evt);
            }
        });
        jScrollPane27.setViewportView(txtJabatanIbu);

        jLabel37.setText("STATUS");

        jLabel38.setText("NO HP IBU");

        jLabel39.setText("NIP IBU");

        jLabel40.setText("PANGKAT IBU");

        jLabel41.setText("JABATAN IBU");

        jLabel42.setText("INSTANSI IBU");

        txtNIPIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNIPIbuKeyTyped(evt);
            }
        });
        jScrollPane28.setViewportView(txtNIPIbu);

        comboStatusIbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aktif", "Pensiunan", "Lainnya" }));
        comboStatusIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                comboStatusIbuKeyTyped(evt);
            }
        });

        txtPekerjaanIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPekerjaanIbuKeyTyped(evt);
            }
        });
        jScrollPane29.setViewportView(txtPekerjaanIbu);

        txtInstansiIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInstansiIbuKeyTyped(evt);
            }
        });
        jScrollPane30.setViewportView(txtInstansiIbu);

        txtPangkatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPangkatIbuKeyTyped(evt);
            }
        });
        jScrollPane31.setViewportView(txtPangkatIbu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane13)
                            .addComponent(jScrollPane15)
                            .addComponent(jScrollPane21)
                            .addComponent(comboStatusAyah, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(jScrollPane4)))
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(jScrollPane25)
                            .addComponent(jScrollPane26)
                            .addComponent(jScrollPane29)
                            .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboStatusIbu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane24)
                            .addComponent(jScrollPane28)
                            .addComponent(jScrollPane31)
                            .addComponent(jScrollPane27))))
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane13)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboStatusAyah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboStatusIbu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane14)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane16)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane20)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(69, 69, 69))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel43.setText("NAMA SEKOLAH");

        jLabel44.setText("SEKOLAH");

        jLabel45.setText("KABUPATEN");

        jLabel46.setText("PROVINSI");

        jLabel47.setText("TAHUN LULUS");

        txtNamaSekolahSd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSekolahSdActionPerformed(evt);
            }
        });
        txtNamaSekolahSd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaSekolahSdKeyTyped(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setText("SD");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        numTahunLulusSD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numTahunLulusSDKeyTyped(evt);
            }
        });

        jTextField5.setEditable(false);
        jTextField5.setText("SMP");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        txtNamaSekolahSMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSekolahSMPActionPerformed(evt);
            }
        });
        txtNamaSekolahSMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaSekolahSMPKeyTyped(evt);
            }
        });

        numTahunLulusSMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numTahunLulusSMPKeyTyped(evt);
            }
        });

        jTextField8.setEditable(false);
        jTextField8.setText("SMA");
        jTextField8.setToolTipText("");
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        txtNamaSekolahSMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaSekolahSMAActionPerformed(evt);
            }
        });
        txtNamaSekolahSMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaSekolahSMAKeyTyped(evt);
            }
        });

        numTahunLulusSMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numTahunLulusSMAKeyTyped(evt);
            }
        });

        txtNamaKabupatenSD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKabupatenSDKeyTyped(evt);
            }
        });

        txtNamaProvinsiSD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaProvinsiSDKeyTyped(evt);
            }
        });

        txtNamaKabupatenSMP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaKabupatenSMPActionPerformed(evt);
            }
        });
        txtNamaKabupatenSMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKabupatenSMPKeyTyped(evt);
            }
        });

        txtNamaKabupatenSMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKabupatenSMAKeyTyped(evt);
            }
        });

        txtNamaProvinsiSMP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaProvinsiSMPKeyTyped(evt);
            }
        });

        txtNamaProvinsiSMA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaProvinsiSMAKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3)
                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel43))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaSekolahSMA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(txtNamaSekolahSMP, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNamaSekolahSd))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45)
                    .addComponent(txtNamaKabupatenSD)
                    .addComponent(txtNamaKabupatenSMP)
                    .addComponent(txtNamaKabupatenSMA, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel46)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel47))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamaProvinsiSMP)
                                    .addComponent(txtNamaProvinsiSMA))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numTahunLulusSMA, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numTahunLulusSMP, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNamaProvinsiSD, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(numTahunLulusSD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(9, 9, 9))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaSekolahSd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaKabupatenSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaProvinsiSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(numTahunLulusSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaSekolahSMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaKabupatenSMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaProvinsiSMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(numTahunLulusSMP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaSekolahSMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaKabupatenSMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNamaProvinsiSMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(numTahunLulusSMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtIdAlamat.setEditable(false);
        jScrollPane19.setViewportView(txtIdAlamat);

        jLabel48.setText("ID ");

        txtRt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRtKeyTyped(evt);
            }
        });
        jScrollPane23.setViewportView(txtRt);

        jLabel49.setText("RT");

        txtRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRwKeyReleased(evt);
            }
        });
        jScrollPane32.setViewportView(txtRw);

        jLabel50.setText("RW");

        txtKecamatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKecamatanKeyTyped(evt);
            }
        });
        jScrollPane33.setViewportView(txtKecamatan);

        jLabel51.setText("KECAMATAN");

        jLabel52.setText("KABUPATEN");

        txtKabupaten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKabupatenKeyTyped(evt);
            }
        });
        jScrollPane34.setViewportView(txtKabupaten);

        jLabel54.setText("PROVINSI");

        txtProvinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProvinsiKeyTyped(evt);
            }
        });
        jScrollPane36.setViewportView(txtProvinsi);

        txtKodePos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKodePosKeyTyped(evt);
            }
        });
        jScrollPane37.setViewportView(txtKodePos);

        jLabel55.setText("KODE POS");

        txtAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatKeyTyped(evt);
            }
        });
        jScrollPane38.setViewportView(txtAlamat);

        jLabel56.setText("ALAMAT");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel57.setText("ISI ALAMAT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane23)
                                    .addComponent(jScrollPane32)
                                    .addComponent(jScrollPane33, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane34, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane36, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane37, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane38, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel57)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel49)
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel52)
                    .addComponent(jScrollPane34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel54)
                    .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel55)
                    .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56)
                    .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblJudul.setText("Tambah Data Peserta");

        jLabel34.setText("Lengkapi data diri peserta dan informasi tambahan lainnya");

        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnKelolaBerkas.setText("Kelola berkas pendaftaran...");
        btnKelolaBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelolaBerkasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnSimpan)
                                .addComponent(btnKelolaBerkas)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblJudul)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 509, Short.MAX_VALUE)
                            .addComponent(btnKeluar)))
                    .addComponent(jLabel34))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJudul)
                            .addComponent(btnKeluar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKelolaBerkas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaSekolahSdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSekolahSdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSekolahSdActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void txtNamaSekolahSMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSekolahSMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSekolahSMPActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void txtNamaSekolahSMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaSekolahSMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaSekolahSMAActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtNamaKabupatenSMPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaKabupatenSMPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaKabupatenSMPActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        if (!inputValid()) {
            return;
        }

        try {
            try {
                conn.setAutoCommit(false);

                if (calon == null) {
                    Alamat alamat = new Alamat();
                    alamat.setRt(txtRt.getText().length() != 0 ? Integer.parseInt(txtRt.getText()) : 0);
                    alamat.setRw(txtRw.getText().length() != 0 ? Integer.parseInt(txtRw.getText()) : 0);
                    alamat.setKecamatan(txtKecamatan.getText());
                    alamat.setKabupaten(txtKabupaten.getText());
                    alamat.setProvinsi(txtProvinsi.getText());
                    alamat.setKodePos(txtKodePos.getText().length() != 0 ? Integer.parseInt(txtKodePos.getText()) : 0);
                    alamat.setAlamat(txtAlamat.getText());
                    int idAlamat = alamatManager.insert(alamat, conn);

                    txtIdAlamat.setText(String.valueOf(idAlamat));

                    CalonMahasiswa calonMahasiswa = new CalonMahasiswa();
                    calonMahasiswa.setNikKtp(txtNIKPeserta.getText());
                    calonMahasiswa.setNamaLengkap(txtNamaPeserta.getText());
                    calonMahasiswa.setTempatLahir(txtTempatLahir.getText());
                    calonMahasiswa.setTanggalLahir(calendarTglLahir.getDate());
                    calonMahasiswa.setAgama(comboAgama.getSelectedItem().toString());
                    calonMahasiswa.setStatusPerkawinan(comboStatusPerkawinan.getSelectedItem().toString());
                    calonMahasiswa.setJumlahAnak(txtJumlahAnak.getText().length() != 0 ? Integer.parseInt(txtJumlahAnak.getText()) : 0);
                    calonMahasiswa.setNisn(txtNISN.getText());
                    calonMahasiswa.setJenisKelamnin(comboJenisKelamin.getSelectedItem().toString());
                    calonMahasiswa.setNoTelepon(txtNoTelepon.getText());
                    calonMahasiswa.setEmail(txtEmail.getText());
                    ProgramStudi selected = (ProgramStudi) comboProgramStudi.getSelectedItem();
                    calonMahasiswa.setProgramStudi(selected.getKode());
                    calonMahasiswa.setTahunDaftar(numTahunDaftar.getYear());
                    calonMahasiswa.setStatusPendaftaran(comboStatusPendaftaran.getSelectedItem().toString());
                    calonMahasiswa.setIdAlamat(idAlamat);
                    int idCalon = calonMahasiswaManager.insert(calonMahasiswa, conn);

                    txtIdCalon.setText(String.valueOf(idCalon));
                    txtIDPeserta.setText(String.valueOf(idCalon));

                    RiwayatPendidikan riwayatSd = new RiwayatPendidikan();
                    riwayatSd.setSekolah("SD");
                    riwayatSd.setNamaSekolah(txtNamaSekolahSd.getText());
                    riwayatSd.setKabupaten(txtNamaKabupatenSD.getText());
                    riwayatSd.setProvinsi(txtNamaProvinsiSD.getText());
                    riwayatSd.setTahunLulus(numTahunLulusSD.getYear());
                    riwayatSd.setIdCalon(idCalon);
                    riwayatPendidikanManager.insert(riwayatSd, conn);

                    RiwayatPendidikan riwayatSmp = new RiwayatPendidikan();
                    riwayatSmp.setSekolah("SMP");
                    riwayatSmp.setNamaSekolah(txtNamaSekolahSMP.getText());
                    riwayatSmp.setKabupaten(txtNamaKabupatenSMP.getText());
                    riwayatSmp.setProvinsi(txtNamaProvinsiSMP.getText());
                    riwayatSmp.setTahunLulus(numTahunLulusSMP.getYear());
                    riwayatSmp.setIdCalon(idCalon);
                    riwayatPendidikanManager.insert(riwayatSmp, conn);

                    RiwayatPendidikan riwayatSma = new RiwayatPendidikan();
                    riwayatSma.setSekolah("SMA");
                    riwayatSma.setNamaSekolah(txtNamaSekolahSMA.getText());
                    riwayatSma.setKabupaten(txtNamaKabupatenSMA.getText());
                    riwayatSma.setProvinsi(txtNamaProvinsiSMA.getText());
                    riwayatSma.setTahunLulus(numTahunLulusSMA.getYear());
                    riwayatSma.setIdCalon(idCalon);
                    riwayatPendidikanManager.insert(riwayatSma, conn);

                    Alamat alamatAyah = new Alamat();
                    alamatAyah.setAlamat(txtAlamatAyah.getText());
                    int idAlamatAyah = alamatManager.insert(alamatAyah, conn);

                    Alamat alamatIbu = new Alamat();
                    alamatIbu.setAlamat(txtAlamatIbu.getText());
                    int idAlamatIbu = alamatManager.insert(alamatIbu, conn);

                    BiodataOrangTua biodataOrangTua = new BiodataOrangTua();
                    biodataOrangTua.setIdCalon(idCalon);
                    biodataOrangTua.setIdAlamatAyah(idAlamatAyah);
                    biodataOrangTua.setIdAlamatIbu(idAlamatIbu);

                    biodataOrangTua.setNamaAyah(txtNamaAyah.getText());
                    biodataOrangTua.setPendidikanAyah(txtPendidikanAyah.getText());
                    biodataOrangTua.setPekerjaanAyah(txtPekerjaanAyah.getText());
                    biodataOrangTua.setStatusAyah(comboStatusAyah.getSelectedItem().toString());
                    biodataOrangTua.setPangkatAyah(txtPangkatAyah.getText());
                    biodataOrangTua.setJabatanAyah(txtJabatanAyah.getText());
                    biodataOrangTua.setInstansiAyah(txtInstansiAyah.getText());
                    biodataOrangTua.setNoHpAyah(txtNoHpAyah.getText());
                    biodataOrangTua.setNipAyah(txtNIPAyah.getText());

                    biodataOrangTua.setNamaIbu(txtNamaIbu.getText());
                    biodataOrangTua.setPendidikanIbu(txtPendidikanIbu.getText());
                    biodataOrangTua.setPekerjaanIbu(txtPekerjaanIbu.getText());
                    biodataOrangTua.setStatusIbu(comboStatusIbu.getSelectedItem().toString());
                    biodataOrangTua.setPangkatIbu(txtPangkatIbu.getText());
                    biodataOrangTua.setJabatanIbu(txtJabatanIbu.getText());
                    biodataOrangTua.setInstansiIbu(txtInstansiIbu.getText());
                    biodataOrangTua.setNoHpIbu(txtNoHpIbu.getText());
                    biodataOrangTua.setNipIbu(txtNIPIbu.getText());

                    biodataOrangTuaManager.insert(biodataOrangTua, conn);

                    jadwalManager.insert(idCalon, conn);
                } else {
                    Alamat alamat = calon.getAlamat();
                    alamat.setRt(Integer.parseInt(txtRt.getText()));
                    alamat.setRw(Integer.parseInt(txtRw.getText()));
                    alamat.setKecamatan(txtKecamatan.getText());
                    alamat.setKabupaten(txtKabupaten.getText());
                    alamat.setProvinsi(txtProvinsi.getText());
                    alamat.setKodePos(Integer.parseInt(txtKodePos.getText()));
                    alamat.setAlamat(txtAlamat.getText());
                    alamatManager.update(alamat, conn);

                    CalonMahasiswa calonMahasiswa = calon;
                    calonMahasiswa.setNikKtp(txtNIKPeserta.getText());
                    calonMahasiswa.setNamaLengkap(txtNamaPeserta.getText());
                    calonMahasiswa.setTempatLahir(txtTempatLahir.getText());
                    calonMahasiswa.setTanggalLahir(calendarTglLahir.getDate());
                    calonMahasiswa.setAgama(comboAgama.getSelectedItem().toString());
                    calonMahasiswa.setStatusPerkawinan(comboStatusPerkawinan.getSelectedItem().toString());
                    calonMahasiswa.setJumlahAnak(Integer.parseInt(txtJumlahAnak.getText()));
                    calonMahasiswa.setNisn(txtNISN.getText());
                    calonMahasiswa.setJenisKelamnin(comboJenisKelamin.getSelectedItem().toString());
                    calonMahasiswa.setNoTelepon(txtNoTelepon.getText());
                    calonMahasiswa.setEmail(txtEmail.getText());
                    ProgramStudi selected = (ProgramStudi) comboProgramStudi.getSelectedItem();
                    calonMahasiswa.setProgramStudi(selected.getKode());
                    calonMahasiswa.setTahunDaftar(numTahunDaftar.getYear());
                    calonMahasiswa.setStatusPendaftaran(comboStatusPendaftaran.getSelectedItem().toString());
                    calonMahasiswaManager.update(calonMahasiswa, conn);

                    ArrayList<RiwayatPendidikan> riwayatPendidikanList = calonMahasiswa.getRiwayatPendidikan();

                    for (RiwayatPendidikan riwayat : riwayatPendidikanList) {
                        if (riwayat.getSekolah().equals("SD")) {
                            riwayat.setNamaSekolah(txtNamaSekolahSd.getText());
                            riwayat.setKabupaten(txtNamaKabupatenSD.getText());
                            riwayat.setProvinsi(txtNamaProvinsiSD.getText());
                            riwayat.setTahunLulus(numTahunLulusSD.getYear());
                            riwayatPendidikanManager.update(riwayat, conn);
                        }

                        if (riwayat.getSekolah().equals("SMP")) {
                            riwayat.setNamaSekolah(txtNamaSekolahSMP.getText());
                            riwayat.setKabupaten(txtNamaKabupatenSMP.getText());
                            riwayat.setProvinsi(txtNamaProvinsiSMP.getText());
                            riwayat.setTahunLulus(numTahunLulusSMP.getYear());
                            riwayatPendidikanManager.update(riwayat, conn);
                        }

                        if (riwayat.getSekolah().equals("SMA")) {
                            riwayat.setNamaSekolah(txtNamaSekolahSMA.getText());
                            riwayat.setKabupaten(txtNamaKabupatenSMA.getText());
                            riwayat.setProvinsi(txtNamaProvinsiSMA.getText());
                            riwayat.setTahunLulus(numTahunLulusSMA.getYear());
                            riwayatPendidikanManager.update(riwayat, conn);
                        }
                    }

                    Alamat alamatAyah = calonMahasiswa.getBiodataOrangTua().getAlamatAyah();
                    alamatAyah.setAlamat(txtAlamatAyah.getText());
                    alamatManager.update(alamatAyah, conn);

                    Alamat alamatIbu = calonMahasiswa.getBiodataOrangTua().getAlamatIbu();
                    alamatIbu.setAlamat(txtAlamatIbu.getText());
                    alamatManager.update(alamatIbu, conn);

                    BiodataOrangTua biodataOrangTua = calon.getBiodataOrangTua();

                    biodataOrangTua.setNamaAyah(txtNamaAyah.getText());
                    biodataOrangTua.setPendidikanAyah(txtPendidikanAyah.getText());
                    biodataOrangTua.setPekerjaanAyah(txtPekerjaanAyah.getText());
                    biodataOrangTua.setStatusAyah(comboStatusAyah.getSelectedItem().toString());
                    biodataOrangTua.setPangkatAyah(txtPangkatAyah.getText());
                    biodataOrangTua.setJabatanAyah(txtJabatanAyah.getText());
                    biodataOrangTua.setInstansiAyah(txtInstansiAyah.getText());
                    biodataOrangTua.setNoHpAyah(txtNoHpAyah.getText());
                    biodataOrangTua.setNipAyah(txtNIPAyah.getText());

                    biodataOrangTua.setNamaIbu(txtNamaIbu.getText());
                    biodataOrangTua.setPendidikanIbu(txtPendidikanIbu.getText());
                    biodataOrangTua.setPekerjaanIbu(txtPekerjaanIbu.getText());
                    biodataOrangTua.setStatusIbu(comboStatusIbu.getSelectedItem().toString());
                    biodataOrangTua.setPangkatIbu(txtPangkatIbu.getText());
                    biodataOrangTua.setJabatanIbu(txtJabatanIbu.getText());
                    biodataOrangTua.setInstansiIbu(txtInstansiIbu.getText());
                    biodataOrangTua.setNoHpIbu(txtNoHpIbu.getText());
                    biodataOrangTua.setNipIbu(txtNIPIbu.getText());

                    biodataOrangTuaManager.update(biodataOrangTua, conn);
                }
                conn.commit();

                Alert.info("Perubahan disimpan!");
                if (onFormClosed != null) {
                    onFormClosed.onClosed();
                }
                dispose();
            } catch (SQLException e) {
                conn.rollback();
                Logger.error(this, e.getMessage());

                txtIDPeserta.setText("");
                txtIdCalon.setText("");
                txtIdAlamat.setText("");

                Alert.warning("Terjadi kesalahan mengubah data. Mohon coba beberapa saat lagi");
            }
        } catch (SQLException ex) {
            Logger.error(this, ex.getMessage());

            txtIDPeserta.setText("");
            txtIdCalon.setText("");
            txtIdAlamat.setText("");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtNIKPesertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIKPesertaKeyTyped
        toNextField(evt, txtNamaPeserta);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNIKPesertaKeyTyped

    private void txtJumlahAnakKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJumlahAnakKeyTyped
        toNextField(evt, txtNISN);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtJumlahAnakKeyTyped

    private void txtNISNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNISNKeyTyped
        toNextField(evt, comboJenisKelamin);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNISNKeyTyped

    private void txtNoTeleponKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoTeleponKeyTyped
        toNextField(evt, txtEmail);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNoTeleponKeyTyped

    private void txtNoHpAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoHpAyahKeyTyped
        toNextField(evt, txtNIPAyah);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNoHpAyahKeyTyped

    private void txtNoHpIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNoHpIbuKeyTyped
        toNextField(evt, txtNIPIbu);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNoHpIbuKeyTyped

    private void txtRtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRtKeyTyped
        toNextField(evt, txtRw);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRtKeyTyped

    private void txtRwKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRwKeyReleased
        toNextField(evt, txtKecamatan);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRwKeyReleased

    private void txtKodePosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKodePosKeyTyped
        toNextField(evt, txtAlamat);
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtKodePosKeyTyped
    private void btnKelolaBerkasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelolaBerkasActionPerformed
        // TODO add your handling code here:
        Utils.openFrame(this, new FormBerkas(), false);
    }//GEN-LAST:event_btnKelolaBerkasActionPerformed

    private void txtNamaPesertaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaPesertaKeyTyped
        toNextField(evt, txtTempatLahir);
    }//GEN-LAST:event_txtNamaPesertaKeyTyped

    private void txtTempatLahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempatLahirKeyTyped
        toNextField(evt, calendarTglLahir);
    }//GEN-LAST:event_txtTempatLahirKeyTyped

    private void comboAgamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboAgamaKeyTyped
        toNextField(evt, comboStatusPerkawinan);
    }//GEN-LAST:event_comboAgamaKeyTyped

    private void comboStatusPerkawinanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboStatusPerkawinanKeyTyped
        toNextField(evt, txtJumlahAnak);
    }//GEN-LAST:event_comboStatusPerkawinanKeyTyped

    private void comboJenisKelaminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboJenisKelaminKeyTyped
        toNextField(evt, txtNoTelepon);
    }//GEN-LAST:event_comboJenisKelaminKeyTyped

    private void txtEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyTyped
        toNextField(evt, comboProgramStudi);
    }//GEN-LAST:event_txtEmailKeyTyped

    private void comboProgramStudiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboProgramStudiKeyTyped
        toNextField(evt, numTahunDaftar);
    }//GEN-LAST:event_comboProgramStudiKeyTyped

    private void numTahunDaftarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numTahunDaftarKeyTyped
        toNextField(evt, comboStatusPendaftaran);
    }//GEN-LAST:event_numTahunDaftarKeyTyped

    private void comboStatusPendaftaranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboStatusPendaftaranKeyTyped
        toNextField(evt, txtNamaAyah);
    }//GEN-LAST:event_comboStatusPendaftaranKeyTyped

    private void txtNamaAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaAyahKeyTyped
        toNextField(evt, txtAlamatAyah);
    }//GEN-LAST:event_txtNamaAyahKeyTyped

    private void txtAlamatAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatAyahKeyTyped
        toNextField(evt, txtPendidikanAyah);
    }//GEN-LAST:event_txtAlamatAyahKeyTyped

    private void txtPendidikanAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendidikanAyahKeyTyped
        toNextField(evt, txtPekerjaanAyah);
    }//GEN-LAST:event_txtPendidikanAyahKeyTyped

    private void txtPekerjaanAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPekerjaanAyahKeyTyped
        toNextField(evt, comboStatusAyah);
    }//GEN-LAST:event_txtPekerjaanAyahKeyTyped

    private void comboStatusAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboStatusAyahKeyTyped
        toNextField(evt, txtNoHpAyah);
    }//GEN-LAST:event_comboStatusAyahKeyTyped

    private void txtNIPAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIPAyahKeyTyped
        toNextField(evt, txtPangkatAyah);
    }//GEN-LAST:event_txtNIPAyahKeyTyped

    private void txtPangkatAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPangkatAyahKeyTyped
        toNextField(evt, txtJabatanAyah);
    }//GEN-LAST:event_txtPangkatAyahKeyTyped

    private void txtJabatanAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJabatanAyahKeyTyped
        toNextField(evt, txtInstansiAyah);
    }//GEN-LAST:event_txtJabatanAyahKeyTyped

    private void txtInstansiAyahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInstansiAyahKeyTyped
        toNextField(evt, txtNamaIbu);
    }//GEN-LAST:event_txtInstansiAyahKeyTyped

    private void txtNamaIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaIbuKeyTyped
        toNextField(evt, txtAlamatIbu);
    }//GEN-LAST:event_txtNamaIbuKeyTyped

    private void txtAlamatIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatIbuKeyTyped
        toNextField(evt, txtPendidikanIbu);
    }//GEN-LAST:event_txtAlamatIbuKeyTyped

    private void txtPendidikanIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPendidikanIbuKeyTyped
        toNextField(evt, txtPekerjaanIbu);
    }//GEN-LAST:event_txtPendidikanIbuKeyTyped

    private void txtPekerjaanIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPekerjaanIbuKeyTyped
        toNextField(evt, comboStatusIbu);
    }//GEN-LAST:event_txtPekerjaanIbuKeyTyped

    private void comboStatusIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboStatusIbuKeyTyped
        toNextField(evt, txtNoHpIbu);
    }//GEN-LAST:event_comboStatusIbuKeyTyped

    private void txtNIPIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIPIbuKeyTyped
        toNextField(evt, txtPangkatIbu);
    }//GEN-LAST:event_txtNIPIbuKeyTyped

    private void txtPangkatIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPangkatIbuKeyTyped
        toNextField(evt, txtJabatanIbu);
    }//GEN-LAST:event_txtPangkatIbuKeyTyped

    private void txtJabatanIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJabatanIbuKeyTyped
        toNextField(evt, txtInstansiIbu);
    }//GEN-LAST:event_txtJabatanIbuKeyTyped

    private void txtInstansiIbuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInstansiIbuKeyTyped
        toNextField(evt, txtRt);
    }//GEN-LAST:event_txtInstansiIbuKeyTyped

    private void txtKecamatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKecamatanKeyTyped
        toNextField(evt, txtKabupaten);
    }//GEN-LAST:event_txtKecamatanKeyTyped

    private void txtKabupatenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKabupatenKeyTyped
        toNextField(evt, txtProvinsi);
    }//GEN-LAST:event_txtKabupatenKeyTyped

    private void txtProvinsiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProvinsiKeyTyped
        toNextField(evt, txtKodePos);
    }//GEN-LAST:event_txtProvinsiKeyTyped

    private void txtAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatKeyTyped
        toNextField(evt, txtNamaSekolahSd);
    }//GEN-LAST:event_txtAlamatKeyTyped

    private void txtNamaSekolahSdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaSekolahSdKeyTyped
        toNextField(evt, txtNamaKabupatenSD);
    }//GEN-LAST:event_txtNamaSekolahSdKeyTyped

    private void txtNamaKabupatenSDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKabupatenSDKeyTyped
        toNextField(evt, txtNamaProvinsiSD);
    }//GEN-LAST:event_txtNamaKabupatenSDKeyTyped

    private void txtNamaProvinsiSDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaProvinsiSDKeyTyped
        toNextField(evt, numTahunLulusSD);
    }//GEN-LAST:event_txtNamaProvinsiSDKeyTyped

    private void numTahunLulusSDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numTahunLulusSDKeyTyped
        toNextField(evt, txtNamaSekolahSMP);
    }//GEN-LAST:event_numTahunLulusSDKeyTyped

    private void txtNamaSekolahSMPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaSekolahSMPKeyTyped
        toNextField(evt, txtNamaKabupatenSMP);
    }//GEN-LAST:event_txtNamaSekolahSMPKeyTyped

    private void txtNamaKabupatenSMPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKabupatenSMPKeyTyped
        toNextField(evt, txtNamaProvinsiSMP);
    }//GEN-LAST:event_txtNamaKabupatenSMPKeyTyped

    private void txtNamaProvinsiSMPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaProvinsiSMPKeyTyped
        toNextField(evt, numTahunLulusSMP);
    }//GEN-LAST:event_txtNamaProvinsiSMPKeyTyped

    private void numTahunLulusSMPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numTahunLulusSMPKeyTyped
        toNextField(evt, txtNamaSekolahSMA);
    }//GEN-LAST:event_numTahunLulusSMPKeyTyped

    private void txtNamaSekolahSMAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaSekolahSMAKeyTyped
        toNextField(evt, txtNamaKabupatenSMA);
    }//GEN-LAST:event_txtNamaSekolahSMAKeyTyped

    private void txtNamaKabupatenSMAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKabupatenSMAKeyTyped
        toNextField(evt, txtNamaProvinsiSMA);
    }//GEN-LAST:event_txtNamaKabupatenSMAKeyTyped

    private void txtNamaProvinsiSMAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaProvinsiSMAKeyTyped
        toNextField(evt, numTahunLulusSMA);
    }//GEN-LAST:event_txtNamaProvinsiSMAKeyTyped

    private void numTahunLulusSMAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numTahunLulusSMAKeyTyped
        toNextField(evt, btnSimpan);
    }//GEN-LAST:event_numTahunLulusSMAKeyTyped

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
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormTambahPeserta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTambahPeserta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKelolaBerkas;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private com.toedter.calendar.JCalendar calendarTglLahir;
    private javax.swing.JComboBox comboAgama;
    private javax.swing.JComboBox<String> comboJenisKelamin;
    private javax.swing.JComboBox comboProgramStudi;
    private javax.swing.JComboBox comboStatusAyah;
    private javax.swing.JComboBox comboStatusIbu;
    private javax.swing.JComboBox comboStatusPendaftaran;
    private javax.swing.JComboBox comboStatusPerkawinan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane34;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lblJudul;
    private com.toedter.calendar.JYearChooser numTahunDaftar;
    private com.toedter.calendar.JYearChooser numTahunLulusSD;
    private com.toedter.calendar.JYearChooser numTahunLulusSMA;
    private com.toedter.calendar.JYearChooser numTahunLulusSMP;
    private javax.swing.JTextPane txtAlamat;
    private javax.swing.JTextPane txtAlamatAyah;
    private javax.swing.JTextPane txtAlamatIbu;
    private javax.swing.JTextPane txtEmail;
    private javax.swing.JTextPane txtIDPeserta;
    private javax.swing.JTextPane txtIdAlamat;
    private javax.swing.JTextPane txtIdCalon;
    private javax.swing.JTextPane txtInstansiAyah;
    private javax.swing.JTextPane txtInstansiIbu;
    private javax.swing.JTextPane txtJabatanAyah;
    private javax.swing.JTextPane txtJabatanIbu;
    private javax.swing.JTextPane txtJumlahAnak;
    private javax.swing.JTextPane txtKabupaten;
    private javax.swing.JTextPane txtKecamatan;
    private javax.swing.JTextPane txtKodePos;
    private javax.swing.JTextPane txtNIKPeserta;
    private javax.swing.JTextPane txtNIPAyah;
    private javax.swing.JTextPane txtNIPIbu;
    private javax.swing.JTextPane txtNISN;
    private javax.swing.JTextPane txtNamaAyah;
    private javax.swing.JTextPane txtNamaIbu;
    private javax.swing.JTextField txtNamaKabupatenSD;
    private javax.swing.JTextField txtNamaKabupatenSMA;
    private javax.swing.JTextField txtNamaKabupatenSMP;
    private javax.swing.JTextPane txtNamaPeserta;
    private javax.swing.JTextField txtNamaProvinsiSD;
    private javax.swing.JTextField txtNamaProvinsiSMA;
    private javax.swing.JTextField txtNamaProvinsiSMP;
    private javax.swing.JTextField txtNamaSekolahSMA;
    private javax.swing.JTextField txtNamaSekolahSMP;
    private javax.swing.JTextField txtNamaSekolahSd;
    private javax.swing.JTextPane txtNoHpAyah;
    private javax.swing.JTextPane txtNoHpIbu;
    private javax.swing.JTextPane txtNoTelepon;
    private javax.swing.JTextPane txtPangkatAyah;
    private javax.swing.JTextPane txtPangkatIbu;
    private javax.swing.JTextPane txtPekerjaanAyah;
    private javax.swing.JTextPane txtPekerjaanIbu;
    private javax.swing.JTextPane txtPendidikanAyah;
    private javax.swing.JTextPane txtPendidikanIbu;
    private javax.swing.JTextPane txtProvinsi;
    private javax.swing.JTextPane txtRt;
    private javax.swing.JTextPane txtRw;
    private javax.swing.JTextPane txtTempatLahir;
    // End of variables declaration//GEN-END:variables
}
