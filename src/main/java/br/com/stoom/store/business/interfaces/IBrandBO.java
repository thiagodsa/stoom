package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.support.dto.BrandDto;

import java.util.List;

public interface IBrandBO {
    List<BrandDto> findAll();

    BrandDto findById(Long id);

    BrandDto save(BrandDto brandDto);

    BrandDto update(Long id, BrandDto brandDto);

    void deleteById(Long id);

    BrandDto activate(Long id);

}
