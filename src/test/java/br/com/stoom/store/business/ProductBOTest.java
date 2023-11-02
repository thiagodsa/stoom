package br.com.stoom.store.business;

import br.com.stoom.store.model.BrandFactory;
import br.com.stoom.store.model.CategoryFactory;
import br.com.stoom.store.model.ProductFactory;
import br.com.stoom.store.repository.ProductRepository;
import br.com.stoom.store.support.dto.ProductDto;
import br.com.stoom.store.support.dto.ProductDtoFactory;
import br.com.stoom.store.support.mapper.BrandMapper;
import br.com.stoom.store.support.mapper.CategoryMapper;
import br.com.stoom.store.support.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductBOTest {

    @InjectMocks
    private ProductBO service;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private BrandMapper brandMapper;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(productMapper.toDtoList(any())).thenReturn(Collections.singletonList(ProductDtoFactory.airForce()));
        when(productMapper.toDto(any())).thenReturn(ProductDtoFactory.airForce());
        when(productMapper.toEntity(any())).thenReturn(ProductFactory.airForce());
        when(brandMapper.toEntity(any())).thenReturn(BrandFactory.nike());
        when(categoryMapper.toEntityList(any())).thenReturn(Collections.singletonList(CategoryFactory.shoes()));

    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(ProductFactory.airForce()));
        List<ProductDto> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindAllByBrand() {
        when(productRepository.findAllByBrandId(anyLong())).thenReturn(Collections.singletonList(ProductFactory.airForce()));
        List<ProductDto> result = service.findAllByBrand(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testFindAllByCategory() {
        when(productRepository.findAllByCategory(anyLong())).thenReturn(Collections.singletonList(ProductFactory.airForce()));
        List<ProductDto> result = service.findAllByCategory(1L);
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFactory.airForce()));
        ProductDto result = service.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testSave() {
        when(productRepository.save(any())).thenReturn(ProductFactory.airForce());
        ProductDto result = service.save(ProductDtoFactory.airForce());
        verify(productRepository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFactory.airForce()));
        when(productRepository.save(any())).thenReturn(ProductFactory.airForce());
        service.update(id, ProductDtoFactory.airForce());
        verify(productRepository, times(1)).save(any());
    }



    @Test
    void testDeleteById() {
        Long id = 1L;
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(ProductFactory.airForce()));
        when(productRepository.save(any())).thenReturn(ProductFactory.airForce());
        service.deleteById(id);

        verify(productRepository, times(1)).save(any());
    }

    @Test
    void testActivated() {
        when(productRepository.findById(any())).thenReturn(Optional.of(ProductFactory.airForce()));
        when(productRepository.save(ProductFactory.airForce())).thenReturn(ProductFactory.airForce());

        service.activate(1L);
        verify(productRepository, times(1)).save(any());
    }

}