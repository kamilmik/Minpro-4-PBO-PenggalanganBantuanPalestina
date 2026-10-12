package model;

public class BantuanDana extends Bantuan {
    private double nominal;

    public BantuanDana(int idBantuan, String namaDonatur, double nominal, Lembaga lembagaPenyalur) {
        super(idBantuan, namaDonatur, lembagaPenyalur);
        this.nominal = nominal;
    }

    public double getNominal() { 
        return nominal; 
    }
    public void setNominal(double nominal) { 
        this.nominal = nominal; 
    }


    @Override
    public void cetakKategori() {
        System.out.println("Jenis Bantuan: DANA TUNAI");
    }

    @Override
    public void tampilkanInfo() {
        System.out.println("-------------------------");
        cetakKategori();
        System.out.println("ID Dana: " + idBantuan);
        System.out.println("Donatur: " + namaDonatur);
        System.out.println("Nominal: Rp" + nominal);
        System.out.println("Disalurkan oleh: " + lembagaPenyalur.getNamaLembaga());
        System.out.println("-------------------------");
    }

    public void tampilkanInfo(boolean tampilkanKontakLembaga) {
        tampilkanInfo();
        if (tampilkanKontakLembaga) {
            System.out.println("Kontak Lembaga: " + lembagaPenyalur.getKontak());
            System.out.println("-------------------------");
        }
    }
}