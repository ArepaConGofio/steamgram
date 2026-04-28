package com.arepacongofio.steamgram.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface IController<T,E,K> {
    
    ResponseEntity<List<T>> findAll(@RequestParam int page, @RequestParam(value = "10") int pageSize);
    ResponseEntity<T> findById(K id);
    ResponseEntity<T> save(E entity);
    ResponseEntity<Void> deleteById(K id);
}
