package br.com.stoom.store.support.mapper;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.support.dto.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CategoryMapper implements IMapper<Category, CategoryDto> {

    @Override
    public CategoryDto toDto(Category entity) {
        return Objects.isNull(entity) ? null : CategoryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .build();
    }

    @Override
    public Category toEntity(CategoryDto dto) {
        return Objects.isNull(dto) ? null : Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.isActive())
                .build();
    }

    public List<CategoryDto> toDtoList(List<Category> categories) {
        return Objects.isNull(categories) ? null : categories.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Category> toEntityList(List<CategoryDto> dtos) {
        return Objects.isNull(dtos) ? null : dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
