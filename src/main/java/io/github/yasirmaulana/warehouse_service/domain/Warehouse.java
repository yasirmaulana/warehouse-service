package io.github.yasirmaulana.warehouse_service.domain;

import io.github.yasirmaulana.warehouse_service.dto.WarehouseCreateUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "warehouses")
public class Warehouse extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer capacity; // volume meter kubik

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarehouseStock> stocks;

    public static Warehouse fromDTO(WarehouseCreateUpdateRequestDTO dto) {
        return Warehouse.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .capacity(dto.getCapacity())
                .build();
    }

    public void updateFromDTO(WarehouseCreateUpdateRequestDTO dto) {
        setName(dto.getName());
        setLocation(dto.getLocation());
        setCapacity(dto.getCapacity());
    }
}
