package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.ProductCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/product")
public class ProductResouce {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createNewProduct(@RequestBody @Valid List<ProductCreateRequestDTO> dtos) {
        productService.createProduct(dtos);
        return ResponseEntity.created(URI.create("/v1/product")).build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable String productId,
                                              @RequestBody @Valid ProductUpdateRequestDTO dto) {
        productService.updateProduct(productId, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<ProductResponseDTO>> findProductList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "productName", required = false) String productName) {
        return ResponseEntity.ok().body(productService.getProductLIst(pages, limit, sortBy, direction, productName));
    }
}
