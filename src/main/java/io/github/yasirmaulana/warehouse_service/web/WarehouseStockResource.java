package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.service.WarehouseStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/warehousestock")
public class WarehouseStockResource {

    private WarehouseStockService warehouseStockService;

    @PostMapping
    public ResponseEntity<Void> createNewStock(@RequestBody @Valid StockCreateRequestDTO dto) {
        warehouseStockService.createStock(dto);
        return ResponseEntity.created(URI.create("/v1/warehousestock")).build();
    }
}
