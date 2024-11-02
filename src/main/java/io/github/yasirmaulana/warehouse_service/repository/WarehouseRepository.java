package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Optional<Warehouse> findBySecureId(String secureId);
    Page<Warehouse> findByNameLikeIgnoreCase(String warehousename, Pageable pageable);
    Optional<Warehouse> findByName(String name);
    List<Warehouse> findByLocation(String location);
    List<Warehouse> findByCapacityGreaterThanEqual(Integer capacity);
    @Query("SELECT w FROM Warehouse w JOIN w.stocks s WHERE s.quantity < :minQuantity")
    List<Warehouse> findWarehousesWithLowStock(int minQuantity);
}
