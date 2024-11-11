package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class WarehouseUpdateRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4065652690482009985L;

    private String name;
    private String location;
    private Integer capacity;

}
