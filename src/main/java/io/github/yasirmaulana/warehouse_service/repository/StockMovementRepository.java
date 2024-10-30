package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    // Cari pergerakan stok berdasarkan tipe (INBOUND atau OUTBOUND)
    List<StockMovement> findByMovementType(String movementType);

    // Cari pergerakan stok untuk produk tertentu
    List<StockMovement> findByProductId(Long productId);

    // Cari pergerakan stok untuk gudang tertentu
    List<StockMovement> findByWarehouseId(Long warehouseId);

    // Cari pergerakan stok terbaru untuk produk di gudang tertentu
    List<StockMovement> findTop10ByProductIdAndWarehouseIdOrderByCreatedAtDesc(Long productId, Long warehouseId);

    // Cari total jumlah barang yang masuk atau keluar untuk produk tertentu
    @Query("SELECT SUM(sm.quantity) FROM StockMovement sm WHERE sm.product.id = :productId AND sm.movementType = :movementType")
    Integer findTotalQuantityByProductIdAndMovementType(Long productId, String movementType);
}
