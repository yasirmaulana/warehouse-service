package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    void createProduct(List<ProductCreateRequestDTO> dtos);
    void updateProduct(String productId, ProductUpdateRequestDTO dto);
    ResultPageResponseDTO<ProductResponseDTO> getProductLIst(Integer pages, Integer limit,
                                                             String sortBy, String direction, String productName);
    List<ProductResponseDTO> getAllProduct();
    Map<String, Product> getAllProductsAsMap();
    Optional<Product> getProductBySecureId(String productId);
}