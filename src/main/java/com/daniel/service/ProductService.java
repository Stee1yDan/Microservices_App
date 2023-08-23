package com.daniel.service;

import com.daniel.dto.ProductDTO;
import com.daniel.dto.ProductDTOMapper;
import com.daniel.model.Product;
import com.daniel.repository.ProductRepository;
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

    public void createProduct(ProductDTO productDTO)
    {
        Product product = ProductDTOMapper.toProduct(productDTO);
        System.out.println(product.getName() + " " + product.getPrice());
        productRepository.save(product);
        log.info("Product {} is saved: ",product.getId());
    }

    public List<ProductDTO> getAllProducts()
    {
        return productRepository
                .findAll()
                .stream()
                .map(product -> ProductDTOMapper.fromProduct(product))
                .collect(Collectors.toList());

    }

}
