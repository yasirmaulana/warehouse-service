package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseCreateUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3250975271090428957L;

    @NotBlank(message = "name must not blank")
    private String name;

    @NotBlank(message = "location must not blank")
    private String location;

    @PositiveOrZero(message = "capacity must zero or positive")
    private Integer capacity;


}
