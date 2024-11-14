package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseStockResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= -8932929424827189399L;

    private Long id;
    private Integer quantity;
    private Warehouse warehouse;
    private Product product;



}
