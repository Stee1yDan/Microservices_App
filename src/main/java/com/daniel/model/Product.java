package com.daniel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@Data
@SuperBuilder
@NoArgsConstructor
public class Product
{
    @Id
    private String id;
    @NotEmpty
    @NotNull
    private String name;
    private String description;
    @NotEmpty
    private BigDecimal price;
}
