package dao;

import database.Koneksi;
import model.Lembaga;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LembagaDAO {
    
    public ArrayList<Lembaga> tampilSemuaLembaga() {
        ArrayList<Lembaga> listLembaga = new ArrayList<>();
        String sql = "SELECT * FROM lembaga";
        
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Lembaga lembaga = new Lembaga(
                    rs.getString("nama_lembaga"), 
                    rs.getString("asal_negara"), 
                    rs.getString("kontak")
                );
                listLembaga.add(lembaga);
            }
        } catch (SQLException e) {
            System.out.println("Gagal tarik data lembaga dari DB: " + e.getMessage());
        }
        return listLembaga;
    }
}