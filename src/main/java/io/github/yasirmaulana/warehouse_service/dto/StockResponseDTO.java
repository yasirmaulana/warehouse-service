package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import io.github.yasirmaulana.warehouse_service.domain.WarehouseStock;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.class)
public class StockResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID= -8932929424827189399L;

    private Long id;
    private Integer quantity;
    private String warehouseId;
    private String warehouseName;
    private String productId;
    private String productName;

    public static StockResponseDTO fromStock(WarehouseStock stock) {
        return StockResponseDTO.builder()
                .id(stock.getId())
                .quantity(stock.getQuantity())
                .warehouseId(stock.getWarehouse().getSecureId())
                .warehouseName(stock.getWarehouse().getName())
                .productId(stock.getProduct().getSecureId())
                .productName(stock.getProduct().getName())
                .build();
    }


}
