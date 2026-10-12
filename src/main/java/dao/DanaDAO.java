package dao;

import database.Koneksi;
import model.BantuanDana;
import model.Lembaga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanaDAO {
    
    public void tambah(BantuanDana dana, int idLembaga) {
        String sql = "INSERT INTO dana (id_bantuan, donatur, nominal, id_lembaga) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dana.getIdBantuan());
            stmt.setString(2, dana.getNamaDonatur());
            stmt.setDouble(3, dana.getNominal());
            stmt.setInt(4, idLembaga); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal tambah data ke DB: " + e.getMessage());
        }
    }

    public List<BantuanDana> tampilSemua() {
        List<BantuanDana> list = new ArrayList<>();
        
        String sql = "SELECT d.id_bantuan, d.donatur, d.nominal, l.nama_lembaga, l.asal_negara, l.kontak " +
                     "FROM dana d JOIN lembaga l ON d.id_lembaga = l.id_lembaga";
                     
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Lembaga lembaga = new Lembaga(rs.getString("nama_lembaga"), rs.getString("asal_negara"), rs.getString("kontak"));
                BantuanDana dana = new BantuanDana(rs.getInt("id_bantuan"), rs.getString("donatur"), rs.getDouble("nominal"), lembaga);
                list.add(dana);
            }
        } catch (SQLException e) {
            System.out.println("Gagal tampil data dari DB: " + e.getMessage());
        }
        return list;
    }

    public void ubahNominal(int id, double nominalBaru) {
        String sql = "UPDATE dana SET nominal = ? WHERE id_bantuan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, nominalBaru);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal ubah data di DB: " + e.getMessage());
        }
    }

    public void hapus(int id) {
        String sql = "DELETE FROM dana WHERE id_bantuan = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal hapus data di DB: " + e.getMessage());
        }
    }

    public boolean cekIdAda(int id) {
        String sql = "SELECT id_bantuan FROM dana WHERE id_bantuan = ?";
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