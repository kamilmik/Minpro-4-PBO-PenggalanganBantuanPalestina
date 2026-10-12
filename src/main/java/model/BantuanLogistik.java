package model;

public class BantuanLogistik extends Bantuan {
    private String namaBarang;
    private int beratKg;

    public BantuanLogistik(int idBantuan, String namaDonatur, String namaBarang, int beratKg, Lembaga lembagaPenyalur) {
        super(idBantuan, namaDonatur, lembagaPenyalur);
        this.namaBarang = namaBarang;
        this.beratKg = beratKg;
    }

    public String getNamaBarang() { 
        return namaBarang; 
    }
    public void setNamaBarang(String namaBarang) { 
        this.namaBarang = namaBarang; 
    }
    public int getBeratKg() { 
        return beratKg; 
    }
    public void setBeratKg(int beratKg) { 
        this.beratKg = beratKg; 
    }

    @Override
    public void cetakKategori() {
        System.out.println("Jenis Bantuan: LOGISTIK FISIK");
    }


    @Override
    public void tampilkanInfo() {
        System.out.println("-------------------------");
        cetakKategori();
        System.out.println("ID Logistik: " + idBantuan);
        System.out.println("Donatur: " + namaDonatur);
        System.out.println("Barang: " + namaBarang + " (" + beratKg + " Kg)");
        System.out.println("Disalurkan oleh: " + lembagaPenyalur.getNamaLembaga());
        System.out.println("-------------------------");
    }

    public void tampilkanInfo(String catatanTambahan) {
        tampilkanInfo();
        System.out.println("Catatan: " + catatanTambahan);
        System.out.println("-------------------------");
    }
}