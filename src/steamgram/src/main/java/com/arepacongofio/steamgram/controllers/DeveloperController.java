package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.domain.requests.DeveloperRequest;
import com.arepacongofio.steamgram.domain.responses.DeveloperResponse;
import com.arepacongofio.steamgram.mappers.DeveloperMapper;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/developer")
@Tag(name = "Developer", description = "Complete developer management")
public class DeveloperController implements IController<DeveloperResponse, DeveloperRequest, Integer> {

    private final IDeveloperService developerService;
    private final DeveloperMapper developerMapper;

    public DeveloperController(IDeveloperService developerService, DeveloperMapper developerMapper) {
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }

    @Override
    @GetMapping
    @Operation(summary = "List developers", description = "Lists all developers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developers listed successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<DeveloperResponse>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(developerMapper.toResponseList(developerService.findAll(PageRequest.of(page, pageSize))));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Find a Developer by their Id", description = "Find a Developer by their Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer found successfully"),
            @ApiResponse(responseCode = "404", description = "Developer not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<DeveloperResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Developer developer = developerService.findById(id);
        if (developer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(developerMapper.toResponse(developer));
    }

    @Override
    @PostMapping
    @Operation(summary = "Save a Developer", description = "Save a Developer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer saved successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<DeveloperResponse> save(@Valid @RequestBody DeveloperRequest developerRequest) {
        return ResponseEntity.ok(developerMapper.toResponse(developerService.save(developerMapper.toEntity(developerRequest))));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Developer", description = "Delete a Developer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Developer not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        if (!developerService.deleteById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
