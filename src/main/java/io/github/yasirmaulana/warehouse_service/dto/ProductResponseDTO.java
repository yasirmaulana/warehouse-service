package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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


}
