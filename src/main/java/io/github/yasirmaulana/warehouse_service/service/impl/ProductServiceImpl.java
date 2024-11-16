package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.ProductCreateUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.extention.NotFoundException;
import io.github.yasirmaulana.warehouse_service.repository.ProductRepository;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import io.github.yasirmaulana.warehouse_service.util.PaginationUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private static final String INVALID_PRODUCT_ID = "invalid.product.id";

    @Override
    public void createProduct(List<ProductCreateUpdateRequestDTO> dtos) {
        List<Product> products = dtos.stream()
                .map(Product::fromDTO)
                .toList();
        productRepository.saveAll(products);
    }

    @Override
    public void updateProduct(String productId, ProductCreateUpdateRequestDTO dto) {
        Product existingProduct = productRepository.findBySecureId(productId)
                .orElseThrow(() -> new NotFoundException(INVALID_PRODUCT_ID));
        existingProduct.updateFromDTO(dto);
        productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        Product existingProduct = productRepository.findBySecureId(productId)
                .orElseThrow(() -> new NotFoundException(INVALID_PRODUCT_ID));
        existingProduct.setDeleted(true);
        productRepository.save(existingProduct);
    }

    @Override
    public ProductResponseDTO getProductById(String productId) {
        Product existingProduct = productRepository.findBySecureId(productId)
                .orElseThrow(() -> new NotFoundException(INVALID_PRODUCT_ID));
        return ProductResponseDTO.fromProduct(existingProduct);
    }

    @Override
    public ResultPageResponseDTO<ProductResponseDTO> getProductLIst(Integer pages, Integer limit, String sortBy, String direction, String productName) {
        productName = StringUtils.isBlank(productName)?"%":productName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Product> pageResult = productRepository.findByNameLikeIgnoreCase(productName, pageable);
        List<ProductResponseDTO> dtos = pageResult.stream()
                .map(ProductResponseDTO::fromProduct)
                .toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public Map<String, Product> getAllProductsAsMap() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .collect(Collectors.toMap(Product::getSecureId, Function.identity()));
    }

}
