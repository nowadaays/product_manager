package org.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
