package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class ProductCreateUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= 5867309767666441592L;

    @NotBlank
    private String name;

    @NotBlank
    private String sku;

    private String description;
    private String category;

    @NotNull
    private Integer capacity;
}
