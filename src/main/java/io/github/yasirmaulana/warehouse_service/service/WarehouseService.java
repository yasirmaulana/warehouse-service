package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseUpdateRequestDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface WarehouseService {
    void createWarehouse(List<WarehouseCreateRequestDTO> dtos);
    void updateWarehouse(String warehouseId, WarehouseUpdateRequestDTO dto);
    void deleteWarehouse(String warehouseId);
    ResultPageResponseDTO<WarehouseResponseDTO> getWarehouseList(Integer pages, Integer limit, String sortBy,
                                                                 String direction, String warehouseName);
    List<WarehouseResponseDTO> getAllWarehouse();
    Map<String, Warehouse> getAllWarehousesAsMap();
    Optional<Warehouse> getWarehouseBySecureId(String warehouseId);

}
