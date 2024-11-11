package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.StockCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.service.WarehouseStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/stock")
public class WarehouseStockResource {

    private final WarehouseStockService warehouseStockService;

    @PostMapping
    public ResponseEntity<Void> createStock(@RequestBody @Valid List<StockCreateRequestDTO> dto) {
        warehouseStockService.createStock(dto);
        return ResponseEntity.created(URI.create("/v1/stock")).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateStock(@RequestBody @Valid StockCreateRequestDTO dto) {
        warehouseStockService.updateStock(dto);
        return ResponseEntity.ok().build();
    }
}
