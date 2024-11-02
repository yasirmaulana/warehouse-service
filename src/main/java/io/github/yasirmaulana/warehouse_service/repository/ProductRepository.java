package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySecureId(String productId);
    Page<Product> findByNameLikeIgnoreCase(String productName, Pageable pageable);
    Optional<Product> findBySku(String sku);
    List<Product> findByCategory(String category);
    List<Product> findByNameContainingIgnoreCase(String name);
    @Query("SELECT p FROM Product p JOIN p.stocks s WHERE s.quantity = 0")
    List<Product> findOutOfStockProducts();
}
