package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.*;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void createProduct(List<ProductCreateRequestDTO> dtos);
    void updateProduct(String productId, ProductUpdateRequestDTO dto);
    void deleteProduct(String productId);
    ResultPageResponseDTO<ProductListResponseDTO> getProductLIst(Integer pages, Integer limit,
                                                                 String sortBy, String direction, String productName);
    Optional<Product> getProductBySecureId(String productId);

    // Cari produk yang habis stoknya di semua gudang
    List<Product> getOutOfStockProducts();

}