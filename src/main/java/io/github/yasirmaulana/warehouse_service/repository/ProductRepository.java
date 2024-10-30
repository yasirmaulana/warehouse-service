package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Cari produk berdasarkan SKU
    Optional<Product> findBySku(String sku);

    // Cari produk berdasarkan kategori
    List<Product> findByCategory(String category);

    // Cari produk berdasarkan nama (menggunakan case-insensitive search)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Cari produk yang stoknya habis di semua gudang
    @Query("SELECT p FROM Product p JOIN p.stocks s WHERE s.quantity = 0")
    List<Product> findOutOfStockProducts();
}
