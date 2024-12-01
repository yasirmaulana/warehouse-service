package io.github.yasirmaulana.warehouse_service.mapper;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.ProductCreateUpdateRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductCreateUpdateRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .capacity(dto.getCapacity())
                .build();

    }

    public void updateEntity(Product existingProduct, ProductCreateUpdateRequestDTO dto) {
        if (dto == null || existingProduct == null) {
            return;
        }
        existingProduct.setName(dto.getName());
        existingProduct.setSku(dto.getSku());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setCategory(dto.getCategory());
        existingProduct.setCapacity(dto.getCapacity());
    }

}
