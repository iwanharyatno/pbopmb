CREATE TABLE `admin` (
    id_admin INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    nama_lengkap VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    no_telepon VARCHAR(20),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE calon_mahasiswa (
    id_calon INT AUTO_INCREMENT PRIMARY KEY,
    nama_lengkap VARCHAR(100) NOT NULL,
    tempat_lahir VARCHAR(50),
    tanggal_lahir DATE,
    jenis_kelamin ENUM('L', 'P') NOT NULL,
    alamat TEXT,
    no_telepon VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    program_studi VARCHAR(100),
    tahun_daftar YEAR,
    status_pendaftaran ENUM('pending', 'lulus', 'tidak lulus') DEFAULT 'pending',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE berkas_pendaftaran (
    id_berkas INT AUTO_INCREMENT PRIMARY KEY,
    id_calon INT NOT NULL,
    jenis_berkas VARCHAR(100) NOT NULL,
    nama_file VARCHAR(255) NOT NULL,
    path_file VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_calon) REFERENCES calon_mahasiswa(id_calon) ON DELETE CASCADE
);

CREATE TABLE program_studi (
    id_prodi INT AUTO_INCREMENT PRIMARY KEY,
    nama_prodi VARCHAR(100) NOT NULL,
    kode_prodi VARCHAR(10) NOT NULL UNIQUE,
    fakultas VARCHAR(100) NOT NULL,
    kuota INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE nilai_seleksi (
    id_nilai INT AUTO_INCREMENT PRIMARY KEY,
    id_calon INT NOT NULL,
    mata_uji VARCHAR(100) NOT NULL,
    nilai DECIMAL(5,2) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_calon) REFERENCES calon_mahasiswa(id_calon) ON DELETE CASCADE
);

CREATE TABLE log_aktivitas (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    id_admin INT NOT NULL,
    aktivitas TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_admin) REFERENCES ADMIN(id_admin) ON DELETE CASCADE
);
