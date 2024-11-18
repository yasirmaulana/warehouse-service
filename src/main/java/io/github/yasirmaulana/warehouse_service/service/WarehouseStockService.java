package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockResponseDTO;

import java.util.List;


public interface WarehouseStockService {
    void createStock(List<StockCreateUpdateRequestDTO> dtos);
    void updateStock(StockCreateUpdateRequestDTO dto);
    ResultPageResponseDTO<StockResponseDTO> getStockList(Integer pages, Integer limit, String sortBy,
                                                         String direction, String comparisonOperator,
                                                         Integer quantity);
}
