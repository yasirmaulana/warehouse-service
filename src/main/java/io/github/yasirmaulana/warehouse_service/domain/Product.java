package io.github.yasirmaulana.warehouse_service.domain;

import io.github.yasirmaulana.warehouse_service.dto.ProductCreateUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity
@Table(name = "products")
public class Product extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(length = 1000)
    private String description;

    private String category;

    @Column(nullable = false)
    private Integer capacity;  // volume meter kubik

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarehouseStock> stocks;

    public static Product fromDTO(ProductCreateUpdateRequestDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .sku(dto.getSku())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .capacity(dto.getCapacity())
                .build();
    }

    public void updateFromDTO(ProductCreateUpdateRequestDTO dto) {
        setName(dto.getName());
        setSku(dto.getSku());
        setDescription(dto.getDescription());
        setCategory(dto.getCategory());
        setCapacity(dto.getCapacity());
    }

}
