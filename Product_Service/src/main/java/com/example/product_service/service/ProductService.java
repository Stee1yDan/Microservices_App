package com.example.product_service.service;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.dto.ProductDtoMapper;
import com.example.product_service.model.Product;
import com.example.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;

    public void createProduct(ProductDto productDTO)
    {
        Product product = ProductDtoMapper.toProduct(productDTO);
        System.out.println(product.getName() + " " + product.getPrice());
        productRepository.save(product);
        log.info("Product {} is saved: ",product.getId());
    }

    public List<ProductDto> getAllProducts()
    {
        return productRepository
                .findAll()
                .stream()
                .map(product -> ProductDtoMapper.fromProduct(product))
                .collect(Collectors.toList());

    }

}
