package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.repository.WarehouseStockRepository;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import io.github.yasirmaulana.warehouse_service.service.WarehouseStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseStockServiceImpl implements WarehouseStockService {

    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final WarehouseStockRepository warehouseStockRepository;

    @Autowired
    public WarehouseStockServiceImpl(ProductService productService, WarehouseService warehouseService,
                                     WarehouseStockRepository warehouseStockRepository) {
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.warehouseStockRepository = warehouseStockRepository;
    }

    @Override
    public void createStock(StockCreateRequestDTO dto) {
        Warehouse warehouse = warehouseService.getWarehouseBySecureId(dto.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("invalid.warehouse.id"));

        Product product = productService.getProductBySecureId(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("invalid.product.id"));

        WarehouseStock stock = new WarehouseStock();
        stock.setQuantity(dto.getQuantity());
        stock.setWarehouse(warehouse);
        stock.setProduct(product);

        warehouseStockRepository.save(stock);
    }

    @Override
    public List<WarehouseStock> getStocksByWarehouseId(Long warehouseId) {
        return null;
    }

    @Override
    public WarehouseStock updateStockQuantity(Long warehouseId, Long productId, int quantity) {
        return null;
    }

    @Override
    public boolean isProductInWarehouse(Long warehouseId, Long productId) {
        return false;
    }

    @Override
    public void deleteStock(Long stockId) {

    }
}
