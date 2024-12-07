-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2024 at 07:06 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pmbmania`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_telepon` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`, `nama_lengkap`, `email`, `no_telepon`, `created_at`, `updated_at`) VALUES
(1, 'admin', '123456789', 'Admin Riil', 'admin@pmb.delimare.ac.id', NULL, '2024-11-09 22:16:41', '2024-11-09 22:16:41');

-- --------------------------------------------------------

--
-- Table structure for table `alamat`
--

CREATE TABLE `alamat` (
  `id` int(11) NOT NULL,
  `rt` int(5) NOT NULL,
  `rw` int(5) NOT NULL,
  `kecamatan` int(100) NOT NULL,
  `kabupaten` int(100) NOT NULL,
  `provinsi` int(100) NOT NULL,
  `kode_pos` int(10) NOT NULL,
  `alamat` int(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `berkas_pendaftaran`
--

CREATE TABLE `berkas_pendaftaran` (
  `id_berkas` int(11) NOT NULL,
  `id_calon` int(11) NOT NULL,
  `jenis_berkas` varchar(100) NOT NULL,
  `nama_file` varchar(255) NOT NULL,
  `path_file` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `biodata_orangtua`
--

CREATE TABLE `biodata_orangtua` (
  `id` int(11) NOT NULL,
  `id_calon` int(11) NOT NULL,
  `nama_ayah` varchar(50) NOT NULL,
  `id_alamat_ayah` int(11) NOT NULL,
  `pendidikan_ayah` varchar(50) NOT NULL,
  `pekerjaan_ayah` varchar(50) NOT NULL,
  `status_ayah` varchar(50) NOT NULL,
  `no_hp_ayah` int(20) NOT NULL,
  `nip_ayah` int(30) NOT NULL,
  `pangkat_ayah` varchar(50) NOT NULL,
  `jabatan_ayah` varchar(50) NOT NULL,
  `instansi_ayah` varchar(65) NOT NULL,
  `nama_ibu` varchar(50) NOT NULL,
  `id_alamat_ibu` int(11) NOT NULL,
  `pendidikan_ibu` varchar(50) NOT NULL,
  `pekerjaan_ibu` varchar(50) NOT NULL,
  `status_ibu` varchar(50) NOT NULL,
  `no_hp_ibu` int(20) NOT NULL,
  `nip_ibu` int(30) NOT NULL,
  `pangkat_ibu` varchar(50) NOT NULL,
  `jabatan_ibu` varchar(50) NOT NULL,
  `instansi_ibu` varchar(65) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `calon_mahasiswa`
--

CREATE TABLE `calon_mahasiswa` (
  `id_calon` int(11) NOT NULL,
  `nik_ktp` varchar(20) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `tempat_lahir` varchar(50) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `agama` enum('Islam','Kristen Protestan','Kristen Katolik','Hindu','Buddha') DEFAULT NULL,
  `status_perkawinan` enum('Belum Menikah','Menikah','Janda','Duda') DEFAULT NULL,
  `jumlah_anak` int(11) DEFAULT NULL,
  `nisn` varchar(20) DEFAULT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL,
  `no_telepon` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `program_studi` varchar(100) DEFAULT NULL,
  `tahun_daftar` year(4) DEFAULT NULL,
  `status_pendaftaran` enum('pending','lulus','tidak lulus') DEFAULT 'pending',
  `id_alamat` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `fakultas`
--

CREATE TABLE `fakultas` (
  `kode_fakultas` varchar(20) NOT NULL,
  `nama_fakultas` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `log_aktivitas`
--

CREATE TABLE `log_aktivitas` (
  `id_log` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `aktivitas` text NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nilai_seleksi`
--

CREATE TABLE `nilai_seleksi` (
  `id_nilai` int(11) NOT NULL,
  `id_calon` int(11) NOT NULL,
  `mata_uji` varchar(100) NOT NULL,
  `nilai` decimal(5,2) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `program_studi`
--

CREATE TABLE `program_studi` (
  `kode_prodi` varchar(10) NOT NULL,
  `nama_prodi` varchar(100) NOT NULL,
  `fakultas` varchar(100) NOT NULL,
  `kuota` int(11) NOT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `updated_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `program_studi`
--

INSERT INTO `program_studi` (`kode_prodi`, `nama_prodi`, `fakultas`, `kuota`, `created_at`, `updated_at`) VALUES
('BD', 'Bisnis Digital', 'Bisnis dan Ilmu Sosial', 400, '2024-12-07 00:47:34', '2024-12-07 00:47:34'),
('IF', 'Informatika', 'Ilmu Komputer', 400, '2024-12-07 00:47:13', '2024-12-07 00:47:13'),
('IK', 'Ilmu Komunikasi', 'Bisnis dan Ilmu Sosial', 400, '2024-12-07 00:47:54', '2024-12-07 00:52:40'),
('SI', 'Sistem Informasi', 'Ilmu Komputer', 400, '2024-12-07 00:47:02', '2024-12-07 00:47:02'),
('TI', 'Teknologi Informasi', 'Ilmu Komputer', 400, '2024-12-07 00:47:23', '2024-12-07 00:47:23');

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_pendidikan`
--

CREATE TABLE `riwayat_pendidikan` (
  `id` int(11) NOT NULL,
  `id_calon` int(11) NOT NULL,
  `sekolah` enum('SD','SMP','SMA') NOT NULL,
  `nama_sekolah` varchar(100) NOT NULL,
  `kabupaten` varchar(100) NOT NULL,
  `provinsi` varchar(100) NOT NULL,
  `tahun_lulus` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `alamat`
--
ALTER TABLE `alamat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `berkas_pendaftaran`
--
ALTER TABLE `berkas_pendaftaran`
  ADD PRIMARY KEY (`id_berkas`),
  ADD KEY `id_calon` (`id_calon`);

--
-- Indexes for table `biodata_orangtua`
--
ALTER TABLE `biodata_orangtua`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_alamat_ayah_alamat` (`id_alamat_ayah`),
  ADD KEY `fk_id_alamat_ibu_alamat` (`id_alamat_ibu`);

--
-- Indexes for table `calon_mahasiswa`
--
ALTER TABLE `calon_mahasiswa`
  ADD PRIMARY KEY (`id_calon`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `fk_id_alamat_alamat` (`id_alamat`);

--
-- Indexes for table `fakultas`
--
ALTER TABLE `fakultas`
  ADD PRIMARY KEY (`kode_fakultas`);

--
-- Indexes for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Indexes for table `nilai_seleksi`
--
ALTER TABLE `nilai_seleksi`
  ADD PRIMARY KEY (`id_nilai`),
  ADD KEY `id_calon` (`id_calon`);

--
-- Indexes for table `program_studi`
--
ALTER TABLE `program_studi`
  ADD PRIMARY KEY (`kode_prodi`);

--
-- Indexes for table `riwayat_pendidikan`
--
ALTER TABLE `riwayat_pendidikan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `alamat`
--
ALTER TABLE `alamat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `berkas_pendaftaran`
--
ALTER TABLE `berkas_pendaftaran`
  MODIFY `id_berkas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `biodata_orangtua`
--
ALTER TABLE `biodata_orangtua`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `calon_mahasiswa`
--
ALTER TABLE `calon_mahasiswa`
  MODIFY `id_calon` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `nilai_seleksi`
--
ALTER TABLE `nilai_seleksi`
  MODIFY `id_nilai` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `riwayat_pendidikan`
--
ALTER TABLE `riwayat_pendidikan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `berkas_pendaftaran`
--
ALTER TABLE `berkas_pendaftaran`
  ADD CONSTRAINT `berkas_pendaftaran_ibfk_1` FOREIGN KEY (`id_calon`) REFERENCES `calon_mahasiswa` (`id_calon`) ON DELETE CASCADE;

--
-- Constraints for table `biodata_orangtua`
--
ALTER TABLE `biodata_orangtua`
  ADD CONSTRAINT `fk_id_alamat_ayah_alamat` FOREIGN KEY (`id_alamat_ayah`) REFERENCES `alamat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_alamat_ibu_alamat` FOREIGN KEY (`id_alamat_ibu`) REFERENCES `alamat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `calon_mahasiswa`
--
ALTER TABLE `calon_mahasiswa`
  ADD CONSTRAINT `fk_id_alamat_alamat` FOREIGN KEY (`id_alamat`) REFERENCES `alamat` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `log_aktivitas`
--
ALTER TABLE `log_aktivitas`
  ADD CONSTRAINT `log_aktivitas_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE;

--
-- Constraints for table `nilai_seleksi`
--
ALTER TABLE `nilai_seleksi`
  ADD CONSTRAINT `nilai_seleksi_ibfk_1` FOREIGN KEY (`id_calon`) REFERENCES `calon_mahasiswa` (`id_calon`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
