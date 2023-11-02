package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.support.dto.ProductDto;

import java.util.List;

public interface IProductBO {

    List<ProductDto> findAll();

    ProductDto findById(Long id);

    List<ProductDto> findAllByBrand(Long brandId);

    List<ProductDto> findAllByCategory(Long categoryId);

    ProductDto save(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    void deleteById(Long id);

    ProductDto activate(Long id);

}
