package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockResponseDTO;
import io.github.yasirmaulana.warehouse_service.extention.NotFoundException;
import io.github.yasirmaulana.warehouse_service.repository.WarehouseStockRepository;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseStockService;
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

@Service
@AllArgsConstructor
public class WarehouseStockServiceImpl implements WarehouseStockService {

    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final WarehouseStockRepository warehouseStockRepository;

    @Override
    public void createStock(List<StockCreateUpdateRequestDTO> dtos) {
        Map<String, Product> productsById = productService.getAllProductsAsMap();
        Map<String, Warehouse> warehousesById = warehouseService.getAllWarehousesAsMap();

        List<WarehouseStock> warehouseStocks = dtos.stream().map(dto -> {
            WarehouseStock stock = new WarehouseStock();
            stock.setQuantity(dto.getQuantity());

            Product product = productsById.get(dto.getProduct().getSecureId());
            stock.setProduct(product);

            Warehouse warehouse = warehousesById.get(dto.getWarehouse().getSecureId());
            stock.setWarehouse(warehouse);

            return stock;
        }).toList();

        warehouseStockRepository.saveAll(warehouseStocks);
    }

    @Override
    public void updateStock(StockCreateUpdateRequestDTO dto) {
        WarehouseStock warehouseStock = warehouseStockRepository.findByWarehouseIdAndProductId(dto.getWarehouse().getId() , dto.getProduct().getId())
                .orElseThrow(() -> new NotFoundException("invalid.warehouse.id.or.product.id"));

        WarehouseStock stock = new WarehouseStock();
        stock.setQuantity(warehouseStock.getQuantity());

        warehouseStockRepository.save(stock);
    }


    // comparisonOperator:
    // > G | >= GT | < L | <= LT |
    @Override
    public ResultPageResponseDTO<StockResponseDTO> getStockList(Integer pages, Integer limit, String sortBy, String direction,
                                                                String comparisonOperator, Integer quantity) {
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages,limit,sort);
        comparisonOperator = StringUtils.isBlank(comparisonOperator)? "%" : comparisonOperator.toUpperCase();
        Page<WarehouseStock> pageResult = getPageResult(comparisonOperator, quantity, pageable);
        List<StockResponseDTO> dtos = pageResult.stream()
                .map(StockResponseDTO::fromStock)
                .toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    private Page<WarehouseStock> getPageResult(String comparisonOperator, Integer quantity, Pageable pageable) {
        switch (comparisonOperator) {
            case "G": return warehouseStockRepository.findByQuantityGreaterThan(quantity, pageable);
            case "GE": return warehouseStockRepository.findByQuantityGreaterThanEqual(quantity, pageable);
            case "L": return warehouseStockRepository.findByQuantityLessThan(quantity, pageable);
            case "LE": return warehouseStockRepository.findByQuantityLessThanEqual(quantity, pageable);
            default: return warehouseStockRepository.findByDeletedFalse(pageable);
        }
    }

}
