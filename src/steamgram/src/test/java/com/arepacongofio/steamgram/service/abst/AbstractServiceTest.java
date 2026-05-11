package com.arepacongofio.steamgram.service.abst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractServiceTest {

    @Mock
    private JpaRepository<String, Integer> jpaRepository;

    private AbstractService<String, Integer> abstractService;

    @BeforeEach
    void setUp() {
        abstractService = new AbstractService<String, Integer>(jpaRepository) {};
    }

    @Test
    void getRepoTest() {
        assertEquals(jpaRepository, abstractService.getRepo());
    }

    @Test
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 10);
        List<String> expectedList = List.of("Item1", "Item2");
        Page<String> dummyPage = new PageImpl<>(expectedList);
        
        when(jpaRepository.findAll(pageable)).thenReturn(dummyPage);
        
        List<String> result = abstractService.findAll(pageable);
        
        assertEquals(expectedList, result);
        verify(jpaRepository).findAll(pageable);
    }

    @Test
    void existsByIdTest() {
        when(jpaRepository.existsById(1)).thenReturn(true);
        when(jpaRepository.existsById(2)).thenReturn(false);
        
        assertTrue(abstractService.existsById(1));
        assertFalse(abstractService.existsById(2));
        
        verify(jpaRepository).existsById(1);
        verify(jpaRepository).existsById(2);
    }

    @Test
    void findByIdTest() {
        when(jpaRepository.findById(1)).thenReturn(Optional.of("Found"));
        when(jpaRepository.findById(2)).thenReturn(Optional.empty());
        
        assertEquals("Found", abstractService.findById(1));
        assertNull(abstractService.findById(2));
        
        verify(jpaRepository).findById(1);
        verify(jpaRepository).findById(2);
    }

    @Test
    void saveTest() {
        String entity = "NewEntity";
        when(jpaRepository.save(entity)).thenReturn(entity);
        
        String result = abstractService.save(entity);
        
        assertEquals(entity, result);
        verify(jpaRepository).save(entity);
    }

    @Test
    void deleteByIdEntityExistsTest() {
        when(jpaRepository.existsById(1)).thenReturn(true);
        
        boolean result = abstractService.deleteById(1);
        
        assertTrue(result);
        verify(jpaRepository).deleteById(1);
    }

    @Test
    void deleteByIdEntityDoesNotExistTest() {
        when(jpaRepository.existsById(1)).thenReturn(false);
        
        boolean result = abstractService.deleteById(1);
        
        assertFalse(result);
        verify(jpaRepository, never()).deleteById(anyInt());
    }
}
