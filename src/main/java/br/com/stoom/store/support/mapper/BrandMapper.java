package br.com.stoom.store.support.mapper;

import br.com.stoom.store.model.Brand;
import br.com.stoom.store.support.dto.BrandDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BrandMapper implements IMapper<Brand, BrandDto> {

    @Override
    public BrandDto toDto(Brand entity) {
        return Objects.isNull(entity) ? null : BrandDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .active(entity.isActive())
                .build();
    }

    @Override
    public Brand toEntity(BrandDto dto) {
        return Objects.isNull(dto) ? null : Brand.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .active(dto.isActive())
                .build();
    }

    public List<BrandDto> toDtoList(List<Brand> brands) {
        return Objects.isNull(brands) ? null : brands.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<Brand> toEntityList(List<BrandDto> dtos) {
        return Objects.isNull(dtos) ? null : dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
