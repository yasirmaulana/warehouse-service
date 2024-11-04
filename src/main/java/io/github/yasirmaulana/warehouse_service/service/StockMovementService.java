package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.StockMovement;

import java.util.List;

public interface StockMovementService {
    // Buat pergerakan stok baru
    // @Transactional
    StockMovement recordStockMovement(StockMovement movement);

    // Ambil pergerakan stok untuk produk tertentu
    List<StockMovement> getMovementsByProduct(Long productId);

    // Ambil total barang yang masuk atau keluar untuk produk tertentu
    int getTotalQuantityByMovementType(Long productId, String movementType);

    int calculateNewQuantity(StockMovement movement);
}
