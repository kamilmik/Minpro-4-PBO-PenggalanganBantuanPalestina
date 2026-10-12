# Mini Project 4 PBO - Sistem Penggalangan Bantuan Palestina

Nama: Muhammad Ibrahim Kamil \
NIM: 2509116012

## Deskripsi Program
Program ini adalah aplikasi Terminal Java untuk mengelola donasi kemanusiaan yang terbagi menjadi dua kategori:
Bantuan Dana dan Bantuan Logistik. Pada Mini Project 4, program telah di disambungkan dengan Database MySQL 
menggunakan JDBC. Struktur program dipertahankan menggunakan MVC yang kini 
dikombinasikan dengan DAO untuk memisahkan akses basis data dari logika teknis, menjadikan 
program bersifat berelasi.

## Struktur Package
Struktur package dalam proyek ini berisi direktori untuk MVC dan DAO:

``` java
BantuanPalestina4
│
├── 📂 com.mycompany.bantuanpalestina4
│   └── 📄 BantuanPalestina.java        (main)
│
├── 📂 controller
│   ├── 📄 DanaController.java          (Logika Dana)
│   └── 📄 LogistikController.java      (Logika Logistik)
│
├── 📂 dao
│   ├── 📄 DanaDAO.java                 (Operasi CRUD Dana ke Database)
│   ├── 📄 LembagaDAO.java              (Operasi penarikan data Lembaga dari Database)
│   └── 📄 LogistikDAO.java             (Operasi CRUD Logistik ke Database)
│
├── 📂 database
│   └── 📄 Koneksi.java                 (koneksi JDBC ke MySQL)
│
├── 📂 model
│   ├── 📄 Bantuan.java                 (Abstract Superclass entitas)
│   ├── 📄 BantuanDana.java             (Subclass entitas Dana)
│   ├── 📄 BantuanLogistik.java         (Subclass entitas Logistik)
│   ├── 📄 KategoriBantuan.java         (Interface klasifikasi)
│   └── 📄 Lembaga.java                 (Entitas master Lembaga)
│
└── 📂 view
    └── 📄 BantuanView.java             (Antarmuka Terminal/CLI interaktif)

```
## Alur Program
1. Inisialisasi Database: Saat program dijalankan, LembagaDAO akan menarik master data daftar lembaga penyalur dari database MySQL.
2. Penyuntikkan Data: Data lembaga tersebut kemudian disuntikkan oleh class Main ke dalam DanaController dan LogistikController.
3. Navigasi View: Pengguna dihadapkan pada antarmuka terminal BantuanView untuk memilih jalur pengelolaan bantuan (Dana atau Logistik).
4. Validasi Controller & Eksekusi DAO: Saat pengguna melakukan operasi CRUD, Controller akan melakukan validasi. Jika validasi berhasil, instruksi akan diteruskan ke DAO yang akan mengeksekusi instruksi SQL (INSERT, SELECT, UPDATE, DELETE) langsung ke dalam database.


## Penerapan 4 Pilar
1. Enscapsulation \
Penerapan encapsulation dilakukan dengan memberikan access modifier private pada atribut-atribut penting, sehingga data tidak bisa diubah sembarangan dari luar class. Akses dan modifikasi hanya bisa dilakukan melalui method getter dan setter.
``` java
    private String namaLembaga;
    private String asalNegara;
    private String kontak;

    public String getNamaLembaga() { 
        return namaLembaga; 
    }
    public void setNamaLembaga(String namaLembaga) { 
        this.namaLembaga = namaLembaga; 
    }
```
2. Inheritance \
Class Bantuan bertindak sebagai Superclass, sementara BantuanDana dan BantuanLogistik bertindak sebagai Subclass. Subclass menggunakan keyword extends untuk mewarisi atribut induknya dan memakai super() pada konstruktor, sehingga kode lebih terpusat dan tidak berulang.
``` java
public class BantuanDana extends Bantuan {
    private double nominal;

    public BantuanDana(int idBantuan, String namaDonatur, double nominal, Lembaga lembagaPenyalur) {
        super(idBantuan, namaDonatur, lembagaPenyalur); // Memanggil constructor Superclass
        this.nominal = nominal;
    }
```
3. Polymorphism \
Program menerapkan dua bentuk Polymorphism sekaligus pada method tampilkanInfo di dalam subclass. \
- Overriding Menimpa method abstrak dari superclass.
``` java
@Override
    public void tampilkanInfo() {
        System.out.println("-------------------------");
        cetakKategori();
        System.out.println("ID Dana: " + idBantuan);
        System.out.println("Donatur: " + namaDonatur);
        System.out.println("Nominal: Rp" + nominal);
        System.out.println("-------------------------");
    }
```
- Overloading Membuat method tampilkanInfo, dengan parameter boolean tampilkanKontakLembaga
``` java
public void tampilkanInfo(boolean tampilkanKontakLembaga) {
        tampilkanInfo(); // Memanggil method override di atas
        if (tampilkanKontakLembaga) {
            System.out.println("Kontak Lembaga: " + lembagaPenyalur.getKontak());
            System.out.println("-------------------------");
        }
    }
```
4. Abstraction \
   Class Bantuan dideklarasikan sebagai abstract class, yang berarti class ini hanya menjadi cetakan dan tidak bisa dibuat menjadi objek langsung. Class ini memiliki abstract method bernama tampilkanInfo() yang tidak memiliki bodi
   ``` java
   public abstract class Bantuan implements KategoriBantuan {
   ```
