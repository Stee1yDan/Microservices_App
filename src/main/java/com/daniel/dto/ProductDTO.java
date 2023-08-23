package com.daniel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class ProductDTO
{
    @NotEmpty
    @NotNull
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
}
