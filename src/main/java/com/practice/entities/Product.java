package com.practice.entities;

import com.practice.annotations.IsExistDb;
import com.practice.annotations.IsRequired;
import com.practice.config.Audit;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El producto no puede se vacio")
    //-> No puede ser vacio
    @Size(min = 3, max = 100)
    private String name;
    @Min(500)
    @Max(999)
    private Integer price;
    @IsRequired
    private String description;
    @IsExistDb
    @IsRequired
    private String sku;
    @Embedded
    private Audit audit;
}
