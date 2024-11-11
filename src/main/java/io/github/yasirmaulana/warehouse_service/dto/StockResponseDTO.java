package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.class)
public class StockResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID= 7842546043718221887L;

    private String stockId;
    private Integer quantity;
    private Warehouse warehouse;
    private Product product;
}
