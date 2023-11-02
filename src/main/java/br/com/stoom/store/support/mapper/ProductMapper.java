package br.com.stoom.store.support.mapper;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.support.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ProductMapper implements IMapper<Product, ProductDto> {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .brand(brandMapper.toDto(entity.getBrand()))
                .categories(categoryMapper.toDtoList(entity.getCategories()))
                .build();
    }

    @Override
    public Product toEntity(ProductDto dto) {
        return Product.builder()
                .id(dto.getId())
                .sku(dto.getSku())
                .name(dto.getName())
                .description(dto.getDescription())
                .brand(brandMapper.toEntity(dto.getBrand()))
                .categories(categoryMapper.toEntityList(dto.getCategories()))
                .active(dto.isActive())
                .build();
    }

    public List<ProductDto> toDtoList(List<Product> products) {
        return Objects.isNull(products) ? null : products.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Product> toEntityList(List<ProductDto> dtos) {
        return Objects.isNull(dtos) ? null : dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
