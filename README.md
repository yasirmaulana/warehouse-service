# Warehouse Service

Warehouse Service adalah aplikasi backend berbasis Spring Boot yang digunakan untuk mengelola informasi gudang dan stok barang di dalam setiap gudang. Aplikasi ini mendukung pengelolaan produk, gudang, stok barang per gudang, serta pergerakan stok barang.

## Fitur

Warehouse Service memiliki beberapa fitur utama, di antaranya:

1.  **Manajemen Produk**
    -   Tambah, update, hapus dan lihat data produk.
2.  **Manajemen Gudang**
    -   Tambah, update, hapus dan lihat data gudang.
3.  **Manajemen Stok Gudang**
    -   Melacak stok produk di setiap gudang, termasuk menambahkan stok baru atau mengubah jumlah stok produk yang ada.
4.  **Pergerakan Stok**
    -   Menyimpan riwayat pergerakan stok seperti masuk dan keluar barang dari gudang tertentu.

## Arsitektur

Aplikasi ini menggunakan desain berbasis microservices dengan lima layanan utama, yaitu:

1.  **Product Service** - Mengelola informasi produk.
2.  **Warehouse Service** - Mengelola informasi gudang.
3.  **Warehouse Stock Service** - Melacak jumlah stok produk di setiap gudang.
4.  **Stock Movement Service** - Menyimpan pergerakan stok, termasuk informasi produk yang masuk dan keluar dari gudang tertentu.
5.  **[In Progress] Inventory Management Service** - Menyediakan integrasi untuk semua layanan lain serta menampilkan laporan stok dan pergerakan barang.

## Teknologi yang Digunakan

-   **Spring Boot 3.3.4** - Framework utama untuk membangun aplikasi backend.
-   **Spring Data JPA** - Digunakan untuk akses data dan manajemen database relasional.
-   **PostgreSQL** - Database utama yang digunakan untuk menyimpan data aplikasi.
-   **Springdoc OpenAPI** - Untuk mendokumentasikan API dengan standar OpenAPI dan menyediakan antarmuka Swagger.
-   **Lombok** - Mengurangi _boilerplate code_ dengan anotasi _getter_, _setter_, dan lain-lain.

## Instalasi dan Menjalankan Aplikasi

1.  **Kloning repositori ini** ke lokal Anda.
    
    bash
    
    Copy code
    
    `git clone https://github.com/username/warehouse-service.git`
    
    `cd warehouse-service` 
    
2.  **Konfigurasi Database**  
    Pastikan Anda sudah mengatur database PostgreSQL, lalu sesuaikan detail koneksi di file `application.properties`.
    
3.  **Bangun dan Jalankan Aplikasi** Aplikasi dapat dijalankan dengan menggunakan perintah Maven berikut:
    
    bash
    
    Copy code
    
    `mvn clean install`
    `mvn spring-boot:run` 
    
4.  **Akses Dokumentasi API** Setelah aplikasi berjalan, dokumentasi API dapat diakses di: `http://localhost:9000/swagger-ui/index.html`
    

## Struktur Database

Beberapa tabel utama dalam aplikasi ini meliputi:

-   **Produk (Product)**: Menyimpan informasi produk seperti `id`, `name`, dan `description`.
-   **Gudang (Warehouse)**: Menyimpan data gudang seperti `id`, `name`, `location`, dan `capacity`.
-   **Stok Gudang (WarehouseStock)**: Menyimpan jumlah stok produk di gudang tertentu.
-   **Pergerakan Stok (StockMovement)**: Menyimpan informasi tentang pergerakan barang (masuk atau keluar).

## Dependencies

Dependencies yang digunakan pada aplikasi ini, seperti yang didefinisikan dalam `pom.xml`:

-   `spring-boot-starter-web` - Untuk membangun REST API.
-   `spring-boot-starter-data-jpa` - Integrasi JPA dan Hibernate untuk manajemen data.
-   `spring-boot-starter-validation` - Memastikan validasi pada input pengguna.
-   `lombok` - Mengurangi _boilerplate code_.
-   `postgresql` - JDBC driver untuk database PostgreSQL.
-   `springdoc-openapi-starter-webmvc-ui` - Menyediakan antarmuka Swagger untuk dokumentasi API.

## Endpoint Utama

1.  **ProductController**
    
    -   `POST /v1/product` - Membuat produk baru
    -   `GET /v1/product` - Mendapatkan semua produk
    -   `GET /v1/product/{productId}` - Mendapatkan detail produk berdasarkan ID
    -   `PUT /v1/product/{productId}` - Memperbarui produk berdasarkan ID
    -   `PATCH /v1/product/{productId}` - Menghapus (soft delete) produk berdasarkan ID
2.  **WarehouseController**
    
    -   `POST /v1/warehouse` - Membuat gudang baru
    -   `GET /v1/warehouse` - Mendapatkan semua gudang
    -   `GET /v1/warehouse/{warehouseId}` - Mendapatkan detail gudang berdasarkan ID
    -   `PUT /v1/warehouse/{warehouseId}` - Memperbarui data gudang berdasarkan ID
    -   `DELETE /v1/warehouse/{warehouseId}` - Menghapus (soft delete) gudang berdasarkan ID
3.  **WarehouseStockController**
    
    -   `POST /v1/stock` - Menambahkan stok ke gudang
    -   `GET /v1/stock/{warehouseId}` - Mendapatkan stok produk di gudang tertentu
    -   `PUT /v1/stock/{warehouseId}/{productId}` - Memperbarui stok produk di gudang tertentu
    -   `DELETE /v1/stock/{warehouseId}/{productId}` - Menghapus stok produk di gudang tertentu
4.  **StockMovementController**
    
    -   `POST /v1/stock-movement` - Mencatat pergerakan stok baru
    -   `GET /v1/stock-movement` - Mendapatkan seluruh riwayat pergerakan stok
    -   `GET /v1/stock-movement/product/{productId}` - Mendapatkan riwayat pergerakan berdasarkan ID produk
    -   `GET /v1/stock-movements/warehouse/{warehouseId}` - Mendapatkan riwayat pergerakan berdasarkan ID gudang

## Lisensi

Proyek ini tidak memiliki lisensi khusus yang ditentukan. Anda bebas menggunakannya untuk kebutuhan belajar atau pengembangan.
