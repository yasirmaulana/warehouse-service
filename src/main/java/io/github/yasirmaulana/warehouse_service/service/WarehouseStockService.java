package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseStockResponseDTO;

import java.util.List;


public interface WarehouseStockService {
    void createStock(List<StockCreateRequestDTO> dtos);
    void updateStock(StockCreateRequestDTO dto);
    ResultPageResponseDTO<WarehouseStockResponseDTO> getStockList(Integer pages, Integer limit, String sortBy,
                                                                  String direction, String comparisonOperator,
                                                                  Integer quantity);
}
