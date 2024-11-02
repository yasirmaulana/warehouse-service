package io.github.yasirmaulana.warehouse_service.service;

import io.github.yasirmaulana.warehouse_service.dto.*;

public interface ProductService {
    void createProduct(ProductCreateRequestDTO dto);
    void updateProduct(String productId, ProductUpdateRequestDTO dto);
    ResultPageResponseDTO<ProductListResponseDTO> getProductLIst(Integer pages, Integer limit,
                                                                 String sortBy, String direction, String productName);
}