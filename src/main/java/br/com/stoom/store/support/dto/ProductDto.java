package br.com.stoom.store.support.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDto {

    private Long id;
    private String sku;
    private String name;
    private String description;
    private BrandDto brand;
    private List<CategoryDto> categories;
    private boolean active;
}
