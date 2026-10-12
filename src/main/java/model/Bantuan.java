package model;


public abstract class Bantuan implements KategoriBantuan {

    protected final int idBantuan; 
    protected String namaDonatur;
    protected Lembaga lembagaPenyalur;

    public Bantuan(int idBantuan, String namaDonatur, Lembaga lembagaPenyalur) {
        this.idBantuan = idBantuan;
        this.namaDonatur = namaDonatur;
        this.lembagaPenyalur = lembagaPenyalur;
    }

    public int getIdBantuan() { 
        return idBantuan; 
    }


    public String getNamaDonatur() { 
        return namaDonatur; 
    }
    public void setNamaDonatur(String namaDonatur) { 
        this.namaDonatur = namaDonatur; 
    }

    public Lembaga getLembagaPenyalur() { 
        return lembagaPenyalur; 
    }
    public void setLembagaPenyalur(Lembaga lembagaPenyalur) { 
        this.lembagaPenyalur = lembagaPenyalur; 
    }


    public abstract void tampilkanInfo();
}