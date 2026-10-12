package model;

public class Lembaga {
    private String namaLembaga;
    private String asalNegara;
    private String kontak;

    public Lembaga(String namaLembaga, String asalNegara, String kontak) {
        this.namaLembaga = namaLembaga;
        this.asalNegara = asalNegara;
        this.kontak = kontak;
    }

    public String getNamaLembaga() { 
        return namaLembaga; 
    }
    public void setNamaLembaga(String namaLembaga) { 
        this.namaLembaga = namaLembaga; 
    }
    
    public String getAsalNegara() { 
        return asalNegara; 
    }
    public void setAsalNegara(String asalNegara) { 
        this.asalNegara = asalNegara; 
    }
    public String getKontak() { 
        return kontak; 
    
    }
    public void setKontak(String kontak) { 
        this.kontak = kontak; 
    }
}