package io.github.yasirmaulana.warehouse_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ProductCreateUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= 5867309767666441592L;

    @NotBlank(message = "name must not blank")
    private String name;

    @NotBlank(message = "sku must not blank")
    private String sku;

    @Size(min = 5, max = 255, message = "description must be between 5 and 255 characters")
    private String description;

    @NotBlank(message = "category must not blank")
    private String category;

    @NotNull
    private Integer capacity;
}
