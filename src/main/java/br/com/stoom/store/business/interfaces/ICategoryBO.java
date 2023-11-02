package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.support.dto.CategoryDto;

import java.util.List;

public interface ICategoryBO {
    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);

    CategoryDto activate(Long id);

}
