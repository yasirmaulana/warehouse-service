package io.github.yasirmaulana.warehouse_service.web;

import io.github.yasirmaulana.warehouse_service.dto.ProductCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductListResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ProductResouce {

    private final ProductService productService;

    @PostMapping("/v1/product")
    public ResponseEntity<Void> createNewProduct(@RequestBody @Valid ProductCreateRequestDTO dto) {
        productService.createProduct(dto);
        return ResponseEntity.created(URI.create("/v1/product")).build();
    }

    @PutMapping("/v1/product/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable String productId,
                                              @RequestBody @Valid ProductUpdateRequestDTO dto) {
        productService.updateProduct(productId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/product")
    public ResponseEntity<ResultPageResponseDTO<ProductListResponseDTO>> findProductList(
            @RequestParam(name = "page", required = true, defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
            @RequestParam(name = "productName", required = false) String productName) {
        return ResponseEntity.ok().body(productService.getProductLIst(pages, limit, sortBy, direction, productName));
    }
}
