package io.github.yasirmaulana.warehouse_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 905122041950251207L;

    @Column(name = "secure_id", nullable = false, unique = true)
    private String secureId= UUID.randomUUID().toString();

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private boolean deleted;
}
