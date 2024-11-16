package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseResponseDTO;
import io.github.yasirmaulana.warehouse_service.extention.NotFoundException;
import io.github.yasirmaulana.warehouse_service.repository.WarehouseRepository;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import io.github.yasirmaulana.warehouse_service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    private static final String INVALID_WAREHOUSE_ID = "invalid.warehouse.id";

    @Override
    public void createWarehouse(List<WarehouseCreateUpdateRequestDTO> dtos) {
        List<Warehouse> warehouses = dtos.stream()
                .map(Warehouse::fromDTO)
                .toList();
        warehouseRepository.saveAll(warehouses);
    }

    @Override
    public void updateWarehouse(String warehouseId, WarehouseCreateUpdateRequestDTO dto) {
        Warehouse existingWarehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new NotFoundException(INVALID_WAREHOUSE_ID));
        existingWarehouse.updateFromDTO(dto);
        warehouseRepository.save(existingWarehouse);
    }

    @Override
    public void deleteWarehouse(String warehouseId) {
        Warehouse existingWarehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new NotFoundException(INVALID_WAREHOUSE_ID));
        existingWarehouse.setDeleted(true);
        warehouseRepository.save(existingWarehouse);
    }

    @Override
    public WarehouseResponseDTO getWarehouseById(String warehouseId) {
        Warehouse existingWarehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new NotFoundException(INVALID_WAREHOUSE_ID));
        return WarehouseResponseDTO.fromWarehouse(existingWarehouse);
    }

    @Override
    public ResultPageResponseDTO<WarehouseResponseDTO> getWarehouseList(Integer pages, Integer limit, String sortBy,
                                                                        String direction, String warehouseName) {
        warehouseName = StringUtils.isBlank(warehouseName)?"%":warehouseName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Warehouse> pageResult = warehouseRepository.findByNameLikeIgnoreCase(warehouseName, pageable);
        List<WarehouseResponseDTO> dtos = pageResult.stream()
                .map(WarehouseResponseDTO::fromWarehouse)
                .toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public Map<String, Warehouse> getAllWarehousesAsMap() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .collect(Collectors.toMap(Warehouse::getSecureId, Function.identity()));
    }

}
