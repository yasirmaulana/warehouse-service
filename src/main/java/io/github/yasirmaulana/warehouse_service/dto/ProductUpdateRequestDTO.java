package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class ProductUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= -8411203561445013629L;

    private String name;

    private String sku;

    private String description;

    private String category;
}
