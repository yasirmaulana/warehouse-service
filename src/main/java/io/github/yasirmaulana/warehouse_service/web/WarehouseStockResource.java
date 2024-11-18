package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.StockResponseDTO;
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
    public ResponseEntity<Void> createStock(@RequestBody @Valid List<StockCreateUpdateRequestDTO> dto) {
        warehouseStockService.createStock(dto);
        return ResponseEntity.created(URI.create("/v1/stock")).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateStock(@RequestBody @Valid StockCreateUpdateRequestDTO dto) {
        warehouseStockService.updateStock(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<StockResponseDTO>> findStockList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "quantity") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "comparisonOperator", required = true, defaultValue = "G") String comparisonOperator,
            @RequestParam(name = "quantity", required = true, defaultValue = "0") Integer quantity) {
        return ResponseEntity.ok().body(warehouseStockService.getStockList(pages, limit, sortBy, direction, comparisonOperator, quantity));
    }
}
