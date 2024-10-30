package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {

    // Cari stok berdasarkan warehouse ID dan product ID
    Optional<WarehouseStock> findByWarehouseIdAndProductId(Long warehouseId, Long productId);

    // Cari semua stok untuk gudang tertentu
    List<WarehouseStock> findByWarehouseId(Long warehouseId);

    // Cari semua stok untuk produk tertentu
    List<WarehouseStock> findByProductId(Long productId);

    // Cari stok yang kuantitasnya di bawah jumlah tertentu
    List<WarehouseStock> findByQuantityLessThan(int quantity);

    // Cek apakah produk tertentu ada stok di suatu gudang
    boolean existsByWarehouseIdAndProductId(Long warehouseId, Long productId);
}
