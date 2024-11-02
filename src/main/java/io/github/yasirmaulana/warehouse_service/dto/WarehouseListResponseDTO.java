package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseListResponseDTO implements Serializable {

    private static final long serialVersionUID = -6864571636339221707L;

    private String warehouseId;
    private String name;
    private String location;
    private Integer capacity;
}
