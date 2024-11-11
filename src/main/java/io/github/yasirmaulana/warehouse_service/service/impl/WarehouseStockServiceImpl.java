package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.extention.NotFoundException;
import io.github.yasirmaulana.warehouse_service.repository.WarehouseStockRepository;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseStockService;
import lombok.AllArgsConstructor;
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
    public void createStock(List<StockCreateRequestDTO> dtos) {
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
    public void updateStock(StockCreateRequestDTO dto) {
        WarehouseStock warehouseStock = warehouseStockRepository.findByWarehouseIdAndProductId(dto.getWarehouse().getId() , dto.getProduct().getId())
                .orElseThrow(() -> new NotFoundException("invalid.warehouse.id.or.product.id"));

        WarehouseStock stock = new WarehouseStock();
        stock.setQuantity(warehouseStock.getQuantity());

        warehouseStockRepository.save(stock);
    }

    @Override
    public void deleteStock(String stockId) {
    }


}
