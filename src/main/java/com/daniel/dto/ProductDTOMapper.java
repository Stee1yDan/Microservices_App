package com.daniel.dto;

import com.daniel.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOMapper
{
    public static Product toProduct(ProductDTO productDTO)
    {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }

    public static ProductDTO fromProduct(Product product)
    {
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        return productDTO;
    }
}
