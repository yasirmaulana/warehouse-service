package io.github.yasirmaulana.warehouse_service.extention;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
