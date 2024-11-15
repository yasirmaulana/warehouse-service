package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
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

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Product product) {
        this.productId = product.getSecureId();
        this.name = product.getName();
        this.sku = product.getSku();
        this.description = product.getDescription();
        this.category = product.getCategory();
        this.capacity = product.getCapacity();
    }

    public static ProductResponseDTO fromProduct(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.productId = product.getSecureId();
        dto.name = product.getName();
        dto.sku = product.getSku();
        dto.description = product.getDescription();
        dto.category = product.getCategory();
        dto.capacity = product.getCapacity();
        return dto;
    }

}
