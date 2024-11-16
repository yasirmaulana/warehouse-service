package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.yasirmaulana.warehouse_service.domain.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6864571636339221707L;

    private Long id;
    private String warehouseId;
    private String name;
    private String location;
    private Integer capacity;

    public static WarehouseResponseDTO fromWarehouse(Warehouse warehouse) {
        return WarehouseResponseDTO.builder()
                .warehouseId(warehouse.getSecureId())
                .name(warehouse.getName())
                .location(warehouse.getLocation())
                .capacity(warehouse.getCapacity())
                .build();
    }
}
