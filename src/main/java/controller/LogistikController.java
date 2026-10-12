package controller;

import dao.LogistikDAO;
import model.BantuanLogistik;
import model.Lembaga;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LogistikController {
    private final LogistikDAO logistikDAO; 
    private final ArrayList<Lembaga> listLembaga;
    private final Scanner scanner;

    public LogistikController(Scanner scanner, ArrayList<Lembaga> listLembaga) {
        this.scanner = scanner;
        this.listLembaga = listLembaga;
        this.logistikDAO = new LogistikDAO();
        
    }

    public void tambahLogistik() {
        System.out.print("ID Logistik (Angka): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        if (logistikDAO.cekIdAda(id)) { 
            System.out.println("!! Error: ID " + id + " sudah dipakai di Database!!");
            return;
        }

        System.out.print("Nama Donatur: ");
        String donatur = scanner.nextLine();
        System.out.print("Nama Barang: ");
        String barang = scanner.nextLine();

        System.out.print("Berat (Kg): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! Berat harus angka!!");
            scanner.nextLine(); return;
        }
        int berat = scanner.nextInt();
        scanner.nextLine();

        if (berat <= 0) {
            System.out.println("!! Berat tidak boleh minus/nol!");
            return;
        }
        
        tampilPilihanLembaga();
        if (!scanner.hasNextInt()) {
            System.out.println("!! Pilihan harus angka!!");
            scanner.nextLine(); return;
        }
        int indexLembaga = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (indexLembaga >= 0 && indexLembaga < listLembaga.size()) {
            BantuanLogistik logistikBaru = new BantuanLogistik(id, donatur, barang, berat, listLembaga.get(indexLembaga));
            int idLembagaDiDatabase = indexLembaga + 1;
            logistikDAO.tambah(logistikBaru, idLembagaDiDatabase);
            System.out.println(">> Alhamdulillah Berhasil ditambah ke Database");
        } else {
            System.out.println("!! maaf, Pilihan salah");
        }
    }

    public void tampilkanLogistik() {
        List<BantuanLogistik> listDariDB = logistikDAO.tampilSemua();
        if (listDariDB.isEmpty()) {
            System.out.println("Data kosong di Database.");
            return;
        }
        for (BantuanLogistik l : listDariDB) {
            l.tampilkanInfo("Segera dikirimkan via udara.");
        }
    }

    public void ubahLogistik() {
        System.out.print("ID Logistik yang diubah: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        if (!logistikDAO.cekIdAda(idTarget)) {
            System.out.println("!! ID tidak ditemukan di Database !!");
            return;
        }

        System.out.print("Berat Baru (Kg): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! Berat harus angka!!");
            scanner.nextLine(); return;
        }
        int beratBaru = scanner.nextInt();
        scanner.nextLine();

        if (beratBaru <= 0) {
            System.out.println("!! Error: Berat tidak boleh minus/nol!");
            return;
        }

        logistikDAO.ubahBerat(idTarget, beratBaru); 
        System.out.println(">> Alhamdulillah Berhasil diupdate di Database");
    }

    public void hapusLogistik() {
        System.out.print("ID Logistik yang dihapus: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        if (logistikDAO.cekIdAda(idTarget)) {
            logistikDAO.hapus(idTarget);
            System.out.println(">> Data dihapus dari Database");
        } else {
            System.out.println("!! ID tidak ditemukan di Database !!");
        }
    }

    private void tampilPilihanLembaga() {
        System.out.println("Pilih Lembaga Penyalur:");
        for (int i = 0; i < listLembaga.size(); i++) {
            System.out.println((i + 1) + ". " + listLembaga.get(i).getNamaLembaga());
        }
        System.out.print("Pilihan: ");
    }
}