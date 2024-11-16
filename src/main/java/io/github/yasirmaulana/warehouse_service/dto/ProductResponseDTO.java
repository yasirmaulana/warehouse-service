package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.class)
public class ProductResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= 3758190957697444423L;

    private Long id;
    private String productId;
    private String name;
    private String sku;
    private String description;
    private String category;
    private Integer capacity;

    public static ProductResponseDTO fromProduct(Product product) {
        return ProductResponseDTO.builder()
                .productId(product.getSecureId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .category(product.getCategory())
                .capacity(product.getCapacity())
                .build();
    }

}
