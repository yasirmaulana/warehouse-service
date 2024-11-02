package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    List<StockMovement> findByMovementType(String movementType);
    List<StockMovement> findByProductId(Long productId);
    List<StockMovement> findByWarehouseId(Long warehouseId);
    List<StockMovement> findTop10ByProductIdAndWarehouseIdOrderByCreatedAtDesc(Long productId, Long warehouseId);
    @Query("SELECT SUM(sm.quantity) FROM StockMovement sm WHERE sm.product.id = :productId AND sm.movementType = :movementType")
    Integer findTotalQuantityByProductIdAndMovementType(Long productId, String movementType);
}
