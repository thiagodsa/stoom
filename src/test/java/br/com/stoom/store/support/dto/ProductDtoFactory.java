package br.com.stoom.store.support.dto;

import br.com.stoom.store.model.BrandFactory;
import br.com.stoom.store.model.CategoryFactory;
import br.com.stoom.store.model.Product;

import java.util.Collections;

public class ProductDtoFactory {
    public static ProductDto airForce() {
        return ProductDto.builder()
                .id(1L)
                .name("Air Force")
                .description("")
                .brand(BrandDtoFactory.nike())
                .categories(Collections.singletonList(CategoryDtoFactory.shoes()))
                .active(true)
                .build();
    }

}