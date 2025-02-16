package com.capitole.productcatalogmanager.infrastructure.persistance.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Table(name = "product")
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String sku;
    private BigDecimal price;
    private String description;
    private String category;

}
