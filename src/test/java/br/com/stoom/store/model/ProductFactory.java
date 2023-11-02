package br.com.stoom.store.model;

import java.util.Collections;

public class ProductFactory {
    public static Product airForce() {
        return Product.builder()
                .id(1L)
                .name("Air Force")
                .description("")
                .brand(BrandFactory.nike())
                .categories(Collections.singletonList(CategoryFactory.shoes()))
                .active(true)
                .build();
    }

}