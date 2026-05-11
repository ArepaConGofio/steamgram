package com.arepacongofio.steamgram.controllers;

import com.arepacongofio.steamgram.domain.requests.DeveloperRequest;
import com.arepacongofio.steamgram.domain.responses.DeveloperResponse;
import com.arepacongofio.steamgram.entities.Developer;
import com.arepacongofio.steamgram.mappers.DeveloperMapper;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeveloperControllerTest {

    @Mock
    private IDeveloperService developerService;

    @Mock
    private DeveloperMapper developerMapper;

    @InjectMocks
    private DeveloperController developerController;

    private List<Developer> developers;
    private List<DeveloperResponse> responses;
    private Developer developer;
    private DeveloperResponse developerResponse;
    private DeveloperRequest request;
    private Developer savedDeveloper;
    private Integer id;

    @BeforeEach
    void setUp() {
        developers = List.of(new Developer());
        responses = List.of(new DeveloperResponse());
        developer = new Developer();
        developerResponse = new DeveloperResponse();
        request = new DeveloperRequest();
        savedDeveloper = new Developer();
        id = 1;
    }

    @Test
    void findAllTest() {
        
        when(developerService.findAll(any(PageRequest.class))).thenReturn(developers);
        when(developerMapper.toResponseList(developers)).thenReturn(responses);
        
        ResponseEntity<List<DeveloperResponse>> response = developerController.findAll(0, 10);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responses, response.getBody());
    }

    @Test
    void findByIdNotFoundTest() {
        when(developerService.findById(id)).thenReturn(null);
        ResponseEntity<DeveloperResponse> response = developerController.findById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findByIdSuccessTest() {
        
        when(developerService.findById(1)).thenReturn(developer);
        when(developerMapper.toResponse(developer)).thenReturn(developerResponse);
        
        ResponseEntity<DeveloperResponse> response = developerController.findById(1);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(developerResponse, response.getBody());
    }

    @Test
    void saveTest() {
        
        when(developerMapper.toEntity(request)).thenReturn(developer);
        when(developerService.save(developer)).thenReturn(savedDeveloper);
        when(developerMapper.toResponse(savedDeveloper)).thenReturn(developerResponse);
        
        ResponseEntity<DeveloperResponse> response = developerController.save(request);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(developerResponse, response.getBody());
    }

    @Test
    void deleteByIdNotFoundTest() {
        when(developerService.deleteById(id)).thenReturn(false);

        ResponseEntity<Void> response = developerController.deleteById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteByIdSuccessTest() {
        when(developerService.deleteById(id)).thenReturn(true);
        
        ResponseEntity<Void> response = developerController.deleteById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
