package br.com.stoom.store.business;

import br.com.stoom.store.exception.BusinessException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.BrandFactory;
import br.com.stoom.store.repository.BrandRepository;
import br.com.stoom.store.support.dto.BrandDto;
import br.com.stoom.store.support.dto.BrandDtoFactory;
import br.com.stoom.store.support.mapper.BrandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
public class BrandBOTest {

    @InjectMocks
    private BrandBO service;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandMapper brandMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(brandMapper.toDtoList(any())).thenReturn(Collections.singletonList(BrandDtoFactory.nike()));
        when(brandMapper.toDto(any())).thenReturn(BrandDtoFactory.nike());
        when(brandMapper.toEntity(any())).thenReturn(BrandFactory.nike());

    }

    @Test
    void testFindAll() {
        when(brandRepository.findAll()).thenReturn(Collections.singletonList(BrandFactory.nike()));
        List<BrandDto> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void testFindById() {
        Long id = 1L;

        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(BrandFactory.nike()));
        BrandDto result = service.findById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testSave() {
        when(brandRepository.save(any())).thenReturn(BrandFactory.nike());
        BrandDto result = service.save(BrandDtoFactory.nike());
        verify(brandRepository, times(1)).save(any());
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(BrandFactory.nike()));
        when(brandRepository.save(any())).thenReturn(BrandFactory.nike());
        service.update(id, BrandDtoFactory.nike());
        verify(brandRepository, times(1)).save(any());
    }



    @Test
    void testDeleteById() {
        Long id = 1L;
        when(brandRepository.findById(anyLong())).thenReturn(Optional.of(BrandFactory.nike()));
        when(brandRepository.save(any())).thenReturn(BrandFactory.nike());
        service.deleteById(id);

        verify(brandRepository, times(1)).save(any());
    }

    @Test
    void testActivated() {
        when(brandRepository.findById(any())).thenReturn(Optional.of(BrandFactory.adidas()));
        when(brandRepository.save(BrandFactory.adidas())).thenReturn(BrandFactory.adidas());

        service.activate(2L);
        verify(brandRepository, times(1)).save(any());
    }

}