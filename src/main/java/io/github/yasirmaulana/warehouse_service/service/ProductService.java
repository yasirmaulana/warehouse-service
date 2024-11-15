package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.*;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void createProduct(List<ProductCreateUpdateRequestDTO> dtos);
    void updateProduct(String productId, ProductCreateUpdateRequestDTO dto);
    void deleteProduct(String productId);
    ProductResponseDTO getProductById(String productId);
    ResultPageResponseDTO<ProductResponseDTO> getProductLIst(Integer pages, Integer limit,
                                                             String sortBy, String direction, String productName);
    Map<String, Product> getAllProductsAsMap();
}