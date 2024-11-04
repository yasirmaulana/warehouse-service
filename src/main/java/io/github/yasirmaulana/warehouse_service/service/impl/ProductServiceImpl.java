package io.github.yasirmaulana.warehouse_service.service.impl;

import io.github.yasirmaulana.warehouse_service.domain.Product;
import io.github.yasirmaulana.warehouse_service.dto.ProductCreateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductListResponseDTO;
import io.github.yasirmaulana.warehouse_service.dto.ProductUpdateRequestDTO;
import io.github.yasirmaulana.warehouse_service.dto.ResultPageResponseDTO;
import io.github.yasirmaulana.warehouse_service.repository.ProductRepository;
import io.github.yasirmaulana.warehouse_service.service.ProductService;
import io.github.yasirmaulana.warehouse_service.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void createProduct(List<ProductCreateRequestDTO> dtos) {
        List<Product> products = dtos.stream().map(p -> {
            Product product = new Product();
            product.setName(p.getName());
            product.setSku(p.getSku());
            product.setDescription(p.getDescription());
            product.setCategory(p.getCategory());

            return product;
        }).toList();

        productRepository.saveAll(products);

    }

    @Override
    public void updateProduct(String productId, ProductUpdateRequestDTO dto) {
        Product product = productRepository.findBySecureId(productId)
                .orElseThrow(() -> new RuntimeException("invalid.product.id"));
        product.setName(dto.getName()==null|| dto.getName().isBlank()?product.getName(): dto.getName());
        product.setSku(dto.getSku()==null|| dto.getSku().isBlank()?product.getSku(): dto.getSku());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String productId) {

    }

    @Override
    public ResultPageResponseDTO<ProductListResponseDTO> getProductLIst(Integer pages, Integer limit, String sortBy, String direction, String productName) {
        productName = StringUtils.isBlank(productName)?"%":productName+"%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Product> pageResult = productRepository.findByNameLikeIgnoreCase(productName, pageable);
        List<ProductListResponseDTO> dtos = pageResult.stream().map(p -> {
            ProductListResponseDTO dto = new ProductListResponseDTO();
            dto.setProductId(p.getSecureId());
            dto.setName(p.getName());
            dto.setSku(p.getSku());
            dto.setDescription(p.getDescription());
            dto.setCategory(p.getCategory());

            return dto;
        }).toList();
        return PaginationUtil.createResultPageDTO(dtos, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    @Override
    public Optional<Product> getProductBySecureId(String productId) {
        return productRepository.findBySecureId(productId);
    }

    @Override
    public List<Product> getOutOfStockProducts() {
        return null;
    }


}
