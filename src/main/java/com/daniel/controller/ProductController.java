package com.daniel.controller;

import com.daniel.dto.ProductDTO;
import com.daniel.service.ProductService;
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
    public void createProduct(@RequestBody ProductDTO productDTO)
    {
        productService.createProduct(productDTO);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts()
    {
        return productService.getAllProducts();
    }
}
