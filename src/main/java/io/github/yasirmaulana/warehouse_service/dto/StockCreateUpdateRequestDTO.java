package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class StockCreateUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4028213924401042038L;

    @NotNull
    private Integer quantity;
    @NotNull
    private Warehouse warehouse;
    @NotNull
    private Product product;

}
