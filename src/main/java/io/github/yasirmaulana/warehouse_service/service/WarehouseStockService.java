package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;

import java.util.List;

public interface WarehouseStockService {
    void createStock(StockCreateRequestDTO dto);

    // Ambil semua stok di gudang tertentu
    List<WarehouseStock> getStocksByWarehouseId(Long warehouseId);

    // Update stok berdasarkan ID gudang dan ID produk
    // @Transactional
    WarehouseStock updateStockQuantity(Long warehouseId, Long productId, int quantity);

    // Cek apakah stok tersedia di suatu gudang
    boolean isProductInWarehouse(Long warehouseId, Long productId);

    // Hapus stok berdasarkan ID stok
    // @Transactional
    void deleteStock(Long stockId);
}
