package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseListResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.repository.WarehouseRepository;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import io.github.yasirmaulana.warehouse_service.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void createWarehouse(WarehouseCreateRequestDTO dto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setCapacity(dto.getCapacity());

        warehouseRepository.save(warehouse);
    }

    @Override
    public void updateWarehouse(String warehouseId, WarehouseUpdateRequestDTO dto) {
        Warehouse warehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new RuntimeException("invalid.warehouse.id"));

        warehouse.setName(dto.getName()==null|| dto.getName().isBlank()?warehouse.getName(): dto.getName());
        warehouse.setLocation(dto.getLocation()==null|| dto.getLocation().isBlank()?warehouse.getLocation(): dto.getLocation());
        warehouse.setCapacity(dto.getCapacity()==null ?warehouse.getCapacity(): dto.getCapacity());

        warehouseRepository.save(warehouse);
    }

    @Override
    public void deleteWarehouse(String warehouseId) {
        Warehouse warehouse = warehouseRepository.findBySecureId(warehouseId)
                .orElseThrow(() -> new RuntimeException("invalid.warehouse.id"));
        warehouse.setDeleted(true);

        warehouseRepository.save(warehouse);
    }

    @Override
    public ResultPageResponseDTO<WarehouseListResponseDTO> getWarehouseList(Integer pages, Integer limit, String sortBy,
                                                                            String direction, String warehouseName) {
        warehouseName = StringUtils.isBlank(warehouseName)?"%":warehouseName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Warehouse> pageResult = warehouseRepository.findByNameLikeIgnoreCase(warehouseName, pageable);
        List<WarehouseListResponseDTO> dtos = pageResult.stream().map(p -> {
            WarehouseListResponseDTO dto = new WarehouseListResponseDTO();
            dto.setWarehouseId(p.getSecureId());
            dto.setName(p.getName());
            dto.setLocation(p.getLocation());
            dto.setCapacity(p.getCapacity());

            return dto;
        }).toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return null;
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return Optional.empty();
    }


    @Override
    public List<Warehouse> getWarehousesWithLowStock(int minQuantity) {
        return null;
    }
}