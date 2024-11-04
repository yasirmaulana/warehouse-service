package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.StockMovement;
import io.github.yasirmaulana.warehouse_service.service.StockMovementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StockMovementServiceImpl implements StockMovementService {
    @Override
    public StockMovement recordStockMovement(StockMovement movement) {
        return null;
    }

    @Override
    public List<StockMovement> getMovementsByProduct(Long productId) {
        return null;
    }

    @Override
    public int getTotalQuantityByMovementType(Long productId, String movementType) {
        return 0;
    }

    @Override
    public int calculateNewQuantity(StockMovement movement) {
        return 0;
    }
}
