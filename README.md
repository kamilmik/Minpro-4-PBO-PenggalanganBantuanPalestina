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
## Penerapan JDBC dan DAO
Program ini telah meninggalkan penggunaan penyimpanan ArrayList dan beralih sepenuhnya ke penyimpanan menggunakan database MySQL.
1. JDBC: \
   Diimplementasikan pada class Koneksi.java untuk membuka jalur komunikasi dengan database db_bantuan_palestina menggunakan driver mysql-connector-j.
3. DAO: \
   Diterapkan pada package dao. Class seperti DanaDAO menggunakan PreparedStatement untuk mengeksekusi operasi manipulasi data (DML) secara aman untuk mencegah SQL Injection, serta menggunakan klausa JOIN untuk menggabungkan tabel transaksi dengan tabel relasi lembaga.

``` java
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
```

## Output Program
Alur kerja Menu Logistik dan Menu Dana sangat identik, berikut adalah kedua tampilan CRUD dari program
1. Menu awal \
<img width="518" height="120" alt="image" src="https://github.com/user-attachments/assets/738ad804-705a-4e6e-a3f0-dace439ebab3" /> \
- Dana \
<img width="289" height="157" alt="image" src="https://github.com/user-attachments/assets/ac5f705f-57ea-4b25-8679-b588a88cf516" /> \

- Logistik \
  <img width="346" height="164" alt="image" src="https://github.com/user-attachments/assets/adb56f6a-6a89-443b-af40-c299211d587f" /> \


2. READ 
- Dana \
<img width="764" height="615" alt="image" src="https://github.com/user-attachments/assets/4dfd7cb9-e9e2-42ce-a202-23bd50be80c4" /> \

- Logistik \
  <img width="426" height="616" alt="image" src="https://github.com/user-attachments/assets/c6570555-1220-481e-9408-e630f325e33d" /> \

3. CREATE 
- Dana \
<img width="385" height="273" alt="image" src="https://github.com/user-attachments/assets/8addc219-71b8-431f-89c2-3e961f626a7a" /> \
- Logistik \
  <img width="422" height="294" alt="image" src="https://github.com/user-attachments/assets/44e066c5-636b-49a4-b48f-0ce411d205f7" /> \

4. UPDATE 
- Dana \
  <img width="387" height="95" alt="image" src="https://github.com/user-attachments/assets/c2409c23-1cf5-4159-aa74-aaa521bfe145" /> \

- Logistik \
  <img width="401" height="99" alt="image" src="https://github.com/user-attachments/assets/909ae311-46ea-4889-b469-53d125aa5b4f" /> \

5. DELETE 
- Dana \
  <img width="305" height="116" alt="image" src="https://github.com/user-attachments/assets/710067e5-0dfc-45c2-aaf9-26aad0ac3b7c" /> \

- Logistik \
  <img width="383" height="74" alt="image" src="https://github.com/user-attachments/assets/6d835097-f100-4be8-8908-038bfd1c467f" /> \

6. Validation Input \
   <img width="234" height="88" alt="image" src="https://github.com/user-attachments/assets/7560e768-26d4-4009-82df-89f8c00b6fe6" /> \
   <img width="315" height="77" alt="image" src="https://github.com/user-attachments/assets/a1ad3403-99f1-4456-a543-28866ae75d60" /> \
   <img width="383" height="93" alt="image" src="https://github.com/user-attachments/assets/0109d4c1-1cb5-409d-bff0-3df5d3ccaf8a" /> \
   <img width="344" height="71" alt="image" src="https://github.com/user-attachments/assets/60d19d47-d00e-4a42-ac44-ad06e6994e5b" /> \
   <img width="429" height="114" alt="image" src="https://github.com/user-attachments/assets/225ddef1-867a-4b5f-b5ee-cd522caba984" />

6. DATABASE \
   <img width="796" height="270" alt="image" src="https://github.com/user-attachments/assets/0f5dbffe-d909-470f-abce-d7578c488fe9" />
   



