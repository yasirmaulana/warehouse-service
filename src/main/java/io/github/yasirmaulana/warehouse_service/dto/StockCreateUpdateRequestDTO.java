package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class StockCreateUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4028213924401042038L;

    @PositiveOrZero(message = "quantity must be zero or positive")
    private Integer quantity;

    @NotNull(message = "warehouse must not null")
    private Warehouse warehouse;

    @NotNull(message = "product must not null")
    private Product product;

}
