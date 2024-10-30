package io.github.yasirmaulana.warehouse_service.repository;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    // Cari gudang berdasarkan nama
    Optional<Warehouse> findByName(String name);

    // Ambil semua gudang di lokasi tertentu
    List<Warehouse> findByLocation(String location);

    // Cek apakah gudang dengan kapasitas tertentu tersedia
    List<Warehouse> findByCapacityGreaterThanEqual(Integer capacity);

    // Custom query untuk mencari gudang dengan stok produk di bawah minimum
    @Query("SELECT w FROM Warehouse w JOIN w.stocks s WHERE s.quantity < :minQuantity")
    List<Warehouse> findWarehousesWithLowStock(int minQuantity);
}
