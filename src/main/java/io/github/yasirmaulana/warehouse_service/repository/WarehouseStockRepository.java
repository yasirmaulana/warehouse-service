package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {

    Optional<WarehouseStock> findByWarehouseIdAndProductId(Long warehouseId, Long productId);
    List<WarehouseStock> findByWarehouseId(Long warehouseId);
    List<WarehouseStock> findByProductId(Long productId);
    List<WarehouseStock> findByQuantityLessThan(int quantity);
    boolean existsByWarehouseIdAndProductId(Long warehouseId, Long productId);
}
