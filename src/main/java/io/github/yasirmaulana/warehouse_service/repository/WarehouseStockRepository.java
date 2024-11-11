package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseStockRepository extends JpaRepository<WarehouseStock, Long> {

    Optional<WarehouseStock> findByWarehouseIdAndProductId(Long warehouseId, Long productId);
    Page<WarehouseStock> findByQuantityLessThan(Integer quantity, Pageable pageable);
    Page<WarehouseStock> findByQuantityLessThanEqual(Integer quantity, Pageable pageable);
    Page<WarehouseStock> findByQuantityGreaterThan(Integer quantity, Pageable pageable);
    Page<WarehouseStock> findByQuantityGreaterThanEqual(Integer quantity, Pageable pageable);
}
