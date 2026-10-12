package view;

import controller.DanaController;
import controller.LogistikController;
import java.util.Scanner;

public class BantuanView {
    private final Scanner scanner;
    private final DanaController danaController;
    private final LogistikController logistikController;

    public BantuanView(Scanner scanner, DanaController danaController, LogistikController logistikController) {
        this.scanner = scanner;
        this.danaController = danaController;
        this.logistikController = logistikController;
    }

    public void tampilkanMenuUtama() {
        boolean berjalan = true;

        while (berjalan) {
            System.out.println("\n==== SISTEM PENGGALANGAN BANTUAN PALESTINA ====");
            System.out.println("1. Kelola Bantuan Dana");
            System.out.println("2. Kelola Bantuan Logistik");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            
            if (scanner.hasNextInt()) {
                int pilihanUtama = scanner.nextInt();
                scanner.nextLine(); 

                switch (pilihanUtama) {
                    case 1 -> menuDana();
                    case 2 -> menuLogistik();
                    case 3 -> berjalan = false;
                    default -> System.out.println("Pilihan tidak valid!");
                }
            } else {
                System.out.println("!! Masukkan angka 1-3 !!");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }

    private void menuDana() {
        boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n=== MENU BANTUAN DANA ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Ubah Nominal");
            System.out.println("4. Hapus Data");
            System.out.println("5. Kembali");
            System.out.print("Pilih (1-5): ");
            
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                scanner.nextLine();
                switch (menu) {
                    case 1 -> danaController.tambahDana();
                    case 2 -> danaController.tampilkanDana();
                    case 3 -> danaController.ubahDana();
                    case 4 -> danaController.hapusDana();
                    case 5 -> subMenu = false;
                    default -> System.out.println("Pilihan tidak valid!");
                }
            } else {
                System.out.println("!! Masukkan angka 1-5 !!");
                scanner.nextLine();
            }
        }
    }

    private void menuLogistik() {
        boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n=== MENU BANTUAN LOGISTIK ===");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Ubah Berat");
            System.out.println("4. Hapus Data");
            System.out.println("5. Kembali");
            System.out.print("Pilih (1-5): ");
            
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                scanner.nextLine();
                switch (menu) {
                    case 1 -> logistikController.tambahLogistik();
                    case 2 -> logistikController.tampilkanLogistik();
                    case 3 -> logistikController.ubahLogistik();
                    case 4 -> logistikController.hapusLogistik();
                    case 5 -> subMenu = false;
                    default -> System.out.println("Pilihan tidak valid!");
                }
            } else {
                System.out.println("!! Masukkan angka 1-5 !!");
                scanner.nextLine();
            }
        }
    }
}