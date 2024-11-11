package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseCreateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3250975271090428957L;

    @NotBlank
    private String name;
    @NotBlank
    private String location;
    private Integer capacity;


}
