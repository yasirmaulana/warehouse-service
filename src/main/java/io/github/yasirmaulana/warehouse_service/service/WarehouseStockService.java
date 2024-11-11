package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;

import java.util.List;


public interface WarehouseStockService {
    void createStock(List<StockCreateRequestDTO> dtos);
    void updateStock(StockCreateRequestDTO dto);
}
