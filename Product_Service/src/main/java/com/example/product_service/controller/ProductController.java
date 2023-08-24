package com.example.product_service.controller;

import com.example.product_service.dto.ProductDto;
import com.example.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductService productService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDto productDTO)
    {
        productService.createProduct(productDTO);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts()
    {
        return productService.getAllProducts();
    }
}
