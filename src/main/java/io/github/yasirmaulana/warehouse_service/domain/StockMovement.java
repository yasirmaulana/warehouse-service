package io.github.yasirmaulana.warehouse_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movement_type", nullable = false)
    private String movementType; // Inbound, Outbound, Picking, Transfer, Shipping

    @Column(nullable = false)
    private Integer quantity;

    private Integer capacity; // volume meter kubik

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String source;

    private String destination;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected  void  onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /*
    INBOUND
    Source: Lokasi pemasok atau pengirim.
    Destination: Lokasi penyimpanan di gudang.

    OUTBOUND
    Source: Lokasi penyimpanan di gudang.
    Destination: Lokasi pelanggan atau tujuan pengiriman.

    PICKING
    Source: Lokasi penyimpanan di gudang.
    Destination: Area pengemasan atau area pengiriman.

    TRANSFER
    Source: Lokasi asal.
    Destination: Lokasi tujuan.

    SHIPPING
    Source: Area pengemasan atau gudang.
    Destination: Lokasi tujuan akhir (pelanggan, distributor).
     */
}
