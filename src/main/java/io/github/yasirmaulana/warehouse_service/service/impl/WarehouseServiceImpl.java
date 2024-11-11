package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseUpdateRequestDTO;
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
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    @Override
    public void createWarehouse(List<WarehouseCreateRequestDTO> dtos) {
        List<Warehouse> warehouses = dtos.stream().map(p -> {
            Warehouse warehouse = new Warehouse();
            warehouse.setName(p.getName());
            warehouse.setLocation(p.getLocation());
            warehouse.setCapacity(p.getCapacity());

            return warehouse;
        }).toList();

        warehouseRepository.saveAll(warehouses);
    }

    @Override
    public void updateWarehouse(String warehouseId, WarehouseUpdateRequestDTO dto) {
        Warehouse warehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new NotFoundException("invalid.warehouse.id"));

        warehouse.setName(dto.getName()==null|| dto.getName().isBlank()?warehouse.getName(): dto.getName());
        warehouse.setLocation(dto.getLocation()==null|| dto.getLocation().isBlank()?warehouse.getLocation(): dto.getLocation());
        warehouse.setCapacity(dto.getCapacity()==null ?warehouse.getCapacity(): dto.getCapacity());

        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(String warehouseId) {
        Warehouse warehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new NotFoundException("invalid.warehouse.id"));
        warehouse.setDeleted(true);

        warehouseRepository.save(warehouse);
    }

    @Override
    public ResultPageResponseDTO<WarehouseResponseDTO> getWarehouseList(Integer pages, Integer limit, String sortBy,
                                                                        String direction, String warehouseName) {
        warehouseName = StringUtils.isBlank(warehouseName)?"%":warehouseName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Warehouse> pageResult = warehouseRepository.findByNameLikeIgnoreCase(warehouseName, pageable);
        List<WarehouseResponseDTO> dtos = pageResult.stream().map(p -> {
            WarehouseResponseDTO dto = new WarehouseResponseDTO();
            dto.setWarehouseId(p.getSecureId());
            dto.setName(p.getName());
            dto.setLocation(p.getLocation());
            dto.setCapacity(p.getCapacity());

            return dto;
        }).toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public List<WarehouseResponseDTO> getAllWarehouse() {
        return warehouseRepository.findByDeletedFalse()
                .stream().map(p -> {
                    WarehouseResponseDTO dto = new WarehouseResponseDTO();
                    dto.setWarehouseId(p.getSecureId());
                    dto.setName(p.getName());
                    dto.setCapacity(p.getCapacity());
                    dto.setLocation(p.getLocation());
                    return dto;
                }).toList();
    }

    @Override
    public Map<String, Warehouse> getAllWarehousesAsMap() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses.stream()
                .collect(Collectors.toMap(Warehouse::getSecureId, Function.identity()));
    }

    @Override
    public Optional<Warehouse> getWarehouseBySecureId(String warehouseId) {
        return warehouseRepository.findBySecureId(warehouseId);
    }

}
