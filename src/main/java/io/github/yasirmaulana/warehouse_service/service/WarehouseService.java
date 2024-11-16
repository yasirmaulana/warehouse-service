package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseResponseDTO;

import java.util.List;
import java.util.Map;

public interface WarehouseService {
    void createWarehouse(List<WarehouseCreateUpdateRequestDTO> dtos);
    void updateWarehouse(String warehouseId, WarehouseCreateUpdateRequestDTO dto);
    void deleteWarehouse(String warehouseId);
    WarehouseResponseDTO getWarehouseById(String warehouseId);
    ResultPageResponseDTO<WarehouseResponseDTO> getWarehouseList(Integer pages, Integer limit,
                                                                 String sortBy, String direction, String warehouseName);
    Map<String, Warehouse> getAllWarehousesAsMap();

}
