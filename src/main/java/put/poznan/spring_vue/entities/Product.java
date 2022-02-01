package org.example;

import javax.persistence.Column;
import javax.persistence.Id;

public class Product {

    @Id
    @Column(name = "PRODUCT_NAME", unique = true)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private float productPrice;

    @Column(name = "FLOOR", nullable = false)
    private String floor;

    @Column(name = "PRICE_PER_NIGHT", nullable = false)
    private float pricePerNight;

    @Column(name = "SINGLE_BEDS", nullable = false)
    private int singleBeds;

    @Column(name = "DOUBLE_BEDS", nullable = false)
    private int doubleBeds;
}
