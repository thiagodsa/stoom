package br.com.stoom.store.business;

import br.com.stoom.store.model.CategoryFactory;
import br.com.stoom.store.repository.CategoryRepository;
import br.com.stoom.store.support.dto.CategoryDto;
import br.com.stoom.store.support.dto.CategoryDtoFactory;
import br.com.stoom.store.support.mapper.CategoryMapper;
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

public class CategoryBOTest {

    @InjectMocks
    private CategoryBO service;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(categoryMapper.toDtoList(any())).thenReturn(Collections.singletonList(CategoryDtoFactory.shoes()));
        when(categoryMapper.toDto(any())).thenReturn(CategoryDtoFactory.shoes());
        when(categoryMapper.toEntity(any())).thenReturn(CategoryFactory.shoes());

    }

    @Test
    void testFindAll() {
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(CategoryFactory.shoes()));
        List<CategoryDto> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryFactory.shoes()));
        CategoryDto result = service.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testSave() {
        when(categoryRepository.save(any())).thenReturn(CategoryFactory.shoes());
        CategoryDto result = service.save(CategoryDtoFactory.shoes());
        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryFactory.shoes()));
        when(categoryRepository.save(any())).thenReturn(CategoryFactory.shoes());
        service.update(id, CategoryDtoFactory.shoes());
        verify(categoryRepository, times(1)).save(any());
    }



    @Test
    void testDeleteById() {
        Long id = 1L;
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(CategoryFactory.shoes()));
        when(categoryRepository.save(any())).thenReturn(CategoryFactory.shoes());
        service.deleteById(id);

        verify(categoryRepository, times(1)).save(any());
    }

    @Test
    void testActivated() {
        when(categoryRepository.findById(any())).thenReturn(Optional.of(CategoryFactory.accessory()));
        when(categoryRepository.save(CategoryFactory.accessory())).thenReturn(CategoryFactory.accessory());

        service.activate(2L);
        verify(categoryRepository, times(1)).save(any());
    }

}