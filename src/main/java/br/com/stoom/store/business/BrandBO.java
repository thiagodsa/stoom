package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.exception.BusinessException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.support.dto.BrandDto;
import br.com.stoom.store.support.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandBO implements IBrandBO {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandDto> findAll() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }

    @Override
    public BrandDto findById(Long id) {
        return brandMapper.toDto(brandRepository.findById(id).orElseThrow(() -> new BusinessException("Entity not found!")));
    }

    @Override
    public BrandDto save(BrandDto brandDto) {
        Brand entity = brandMapper.toEntity(brandDto);
        entity.setActive(true);
        return brandMapper.toDto(brandRepository.save(entity));
    }

    @Override
    public BrandDto update(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));

        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
        brand.setActive(brand.isActive());

        return brandMapper.toDto(brandRepository.save(brand));
    }

    @Override
    public void deleteById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));
        brand.setActive(false);
        brandRepository.save(brand);
    }

    @Override
    public BrandDto activate(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));
        brand.setActive(true);
        return brandMapper.toDto(brandRepository.save(brand));
    }

}
