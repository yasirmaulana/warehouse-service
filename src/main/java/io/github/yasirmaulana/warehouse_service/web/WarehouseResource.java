package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseListResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.WarehouseUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class WarehouseResource {

    private final WarehouseService warehouseService;

    @PostMapping("/v1/warehouse")
    public ResponseEntity<Void> createNewWarehouse(@RequestBody @Valid List<WarehouseCreateRequestDTO> dtos) {
        warehouseService.createWarehouse(dtos);
        return ResponseEntity.created(URI.create("/v1/warehouse")).build();
    }

    @PutMapping("/v1/warehouse/{warehouseId}")
    public ResponseEntity<Void> updateWarehouse(@PathVariable String warehouseId, @RequestBody @Valid WarehouseUpdateRequestDTO dto) {
        warehouseService.updateWarehouse(warehouseId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/warehouse")
    public ResponseEntity<ResultPageResponseDTO<WarehouseListResponseDTO>> findWarehouseList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "warehouseName", required = false) String warehouseName) {
        return ResponseEntity.ok().body(warehouseService.getWarehouseList(pages, limit, sortBy, direction, warehouseName));
    }
}
