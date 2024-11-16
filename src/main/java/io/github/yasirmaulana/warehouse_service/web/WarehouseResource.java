package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseResponseDTO;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/warehouse")
public class WarehouseResource {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Void> createWarehouse(@RequestBody @Valid List<WarehouseCreateUpdateRequestDTO> dtos) {
        warehouseService.createWarehouse(dtos);
        return ResponseEntity.created(URI.create("/v1/warehouse")).build();
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable String warehouseId, @RequestBody @Valid WarehouseCreateUpdateRequestDTO dto) {
        warehouseService.updateWarehouse(warehouseId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable String warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{warehouseId}")
    public ResponseEntity<WarehouseResponseDTO> findWarehouse(@PathVariable String warehouseId) {
        return ResponseEntity.ok().body(warehouseService.getWarehouseById(warehouseId));
    }

    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<WarehouseResponseDTO>> findWarehouseList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "warehouseName", required = false) String warehouseName) {
        return ResponseEntity.ok().body(warehouseService.getWarehouseList(pages, limit, sortBy, direction, warehouseName));
    }
}
