package controller;

import dao.DanaDAO;
import model.BantuanDana;
import model.Lembaga;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DanaController {
    private final DanaDAO danaDAO; 
    private final ArrayList<Lembaga> listLembaga;
    private final Scanner scanner;

    public DanaController(Scanner scanner, ArrayList<Lembaga> listLembaga) {
        this.scanner = scanner;
        this.listLembaga = listLembaga;
        this.danaDAO = new DanaDAO();
        
    }

    public void tambahDana() {
        System.out.print("ID Dana (Angka): ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return; 
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        if (danaDAO.cekIdAda(id)) { 
            System.out.println("!! Error: ID " + id + " sudah dipakai di Database!");
            return;
        }

        System.out.print("Nama Donatur: ");
        String nama = scanner.nextLine();

        System.out.print("Nominal (Angka): ");
        if (!scanner.hasNextDouble()) {
            System.out.println("!! Nominal harus angka!!");
            scanner.nextLine(); return;
        }
        double nominal = scanner.nextDouble();
        scanner.nextLine();

        if (nominal <= 0) {
            System.out.println("!! Error: Nominal tidak boleh minus/nol!");
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
            BantuanDana danaBaru = new BantuanDana(id, nama, nominal, listLembaga.get(indexLembaga));
            int idLembagaDiDatabase = indexLembaga + 1; // Karena ID di DB kita set 1, 2, 3, dst
            danaDAO.tambah(danaBaru, idLembagaDiDatabase); 
            System.out.println(">> Alhamdulillah Berhasil ditambah ke Database!");
        } else {
            System.out.println("!! Maaf, Pilihan lembaga salah!!");
        }
    }

    public void tampilkanDana() {
        List<BantuanDana> listDariDB = danaDAO.tampilSemua(); 
        if (listDariDB.isEmpty()) {
            System.out.println("Data kosong di Database.");
            return;
        }
        for (BantuanDana d : listDariDB) {
            d.tampilkanInfo(true); 
        }
    }

    public void ubahDana() {
        System.out.print("ID Dana yang diubah: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        if (!danaDAO.cekIdAda(idTarget)) {
            System.out.println("!! ID tidak ditemukan di Database !!");
            return;
        }

        System.out.print("Nominal Baru: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("!! Nominal harus angka!!");
            scanner.nextLine(); return;
        }
        double nominalBaru = scanner.nextDouble();
        scanner.nextLine();

        if (nominalBaru <= 0) {
            System.out.println("!! Nominal tidak boleh minus/nol!!");
            return;
        }
        
        danaDAO.ubahNominal(idTarget, nominalBaru); 
        System.out.println(">> Alhamdulillah Berhasil diupdate di Database!");
    }

    public void hapusDana() {
        System.out.print("ID Dana yang dihapus: ");
        if (!scanner.hasNextInt()) {
            System.out.println("!! ID harus angka!!");
            scanner.nextLine(); return;
        }
        int idTarget = scanner.nextInt();
        scanner.nextLine();

        if (danaDAO.cekIdAda(idTarget)) {
            danaDAO.hapus(idTarget); 
            System.out.println(">> Data dihapus dari Database!");
        } else {
            System.out.println("!! ID tidak ditemukan di Database. !!");
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