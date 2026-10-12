package dao;

import database.Koneksi;
import model.BantuanLogistik;
import model.Lembaga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogistikDAO {

    public void tambah(BantuanLogistik logistik, int idLembaga) {
        String sql = "INSERT INTO logistik (id_bantuan, donatur, nama_barang, berat_kg, id_lembaga) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, logistik.getIdBantuan());
            stmt.setString(2, logistik.getNamaDonatur());
            stmt.setString(3, logistik.getNamaBarang());
            stmt.setInt(4, logistik.getBeratKg());
            stmt.setInt(5, idLembaga); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal tambah data ke DB: " + e.getMessage());
        }
    }

    public List<BantuanLogistik> tampilSemua() {
        List<BantuanLogistik> list = new ArrayList<>();
        
        String sql = "SELECT log.id_bantuan, log.donatur, log.nama_barang, log.berat_kg, l.nama_lembaga, l.asal_negara, l.kontak " +
                     "FROM logistik log JOIN lembaga l ON log.id_lembaga = l.id_lembaga";
                     
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Lembaga lembaga = new Lembaga(rs.getString("nama_lembaga"), rs.getString("asal_negara"), rs.getString("kontak"));
                BantuanLogistik logistik = new BantuanLogistik(rs.getInt("id_bantuan"), rs.getString("donatur"), rs.getString("nama_barang"), rs.getInt("berat_kg"), lembaga);
                list.add(logistik);
            }
        } catch (SQLException e) {
            System.out.println("Gagal tampil data dari DB: " + e.getMessage());
        }
        return list;
    }

    public void ubahBerat(int id, int beratBaru) {
        String sql = "UPDATE logistik SET berat_kg = ? WHERE id_bantuan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, beratBaru);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal ubah data di DB: " + e.getMessage());
        }
    }

    public void hapus(int id) {
        String sql = "DELETE FROM logistik WHERE id_bantuan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal hapus data di DB: " + e.getMessage());
        }
    }

    public boolean cekIdAda(int id) {
        String sql = "SELECT id_bantuan FROM logistik WHERE id_bantuan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }
}