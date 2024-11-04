package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseListResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseUpdateRequestDTO;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    void createWarehouse(List<WarehouseCreateRequestDTO> dtos);
    void updateWarehouse(String warehouseId, WarehouseUpdateRequestDTO dto);
    void deleteWarehouse(String warehouseId);

    ResultPageResponseDTO<WarehouseListResponseDTO> getWarehouseList(Integer pages, Integer limit, String sortBy, String direction, String warehouseName);

    Optional<Warehouse> getWarehouseBySecureId(String warehouseId);

    List<Warehouse> getAllWarehouses();

    List<Warehouse> getWarehousesWithLowStock(int minQuantity);
}
