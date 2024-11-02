package io.github.yasirmaulana.warehouse_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.class)
public class ResultPageResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 2548992266596190806L;

    private List<T> result;
    private Integer page;
    private Long element;
}
