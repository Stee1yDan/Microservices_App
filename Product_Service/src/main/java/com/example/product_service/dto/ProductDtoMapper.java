package com.example.product_service.dto;

import com.example.product_service.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper
{
    public static Product toProduct(ProductDto productDTO)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }

    public static ProductDto fromProduct(Product product)
    {
        ProductDto productDTO = new ProductDto();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
