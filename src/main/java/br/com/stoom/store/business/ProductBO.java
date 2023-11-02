package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.exception.BusinessException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.support.dto.ProductDto;
import br.com.stoom.store.support.mapper.BrandMapper;
import br.com.stoom.store.support.mapper.CategoryMapper;
import br.com.stoom.store.support.mapper.ProductMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductBO implements IProductBO {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ProductDto> findAll(){
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() -> new BusinessException("Entity not found!")));
    }

    @Override
    public List<ProductDto> findAllByBrand(Long brandId) {
        return productMapper.toDtoList(productRepository.findAllByBrandId(brandId));
    }

    @Override
    public List<ProductDto> findAllByCategory(Long categoryId) {
        return productMapper.toDtoList(productRepository.findAllByCategory(categoryId));
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product entity = productMapper.toEntity(productDto);
        entity.setActive(true);
        return productMapper.toDto(productRepository.save(entity));
    }

    @Override
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setBrand(brandMapper.toEntity(productDto.getBrand()));
        product.setCategories(categoryMapper.toEntityList(productDto.getCategories()));
        product.setActive(productDto.isActive());

        return productMapper.toDto(productRepository.save(product));
    }


    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductDto activate(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Entity not found!"));
        product.setActive(true);
        return productMapper.toDto(productRepository.save(product));
    }

}
