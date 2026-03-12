package com.arepacongofio.steamgram.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arepacongofio.steamgram.controllers.interfaces.IController;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "Complete user management")
public class UserController implements IController<User, Integer> {

    IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    @Operation(summary = "List users", description = "Lists all users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<User> findById(@Valid @PathVariable Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ResponseEntity<User> save(@Valid User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Delete an User by ID")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
