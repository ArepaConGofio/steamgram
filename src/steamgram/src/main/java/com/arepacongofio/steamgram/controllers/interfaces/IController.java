package com.arepacongofio.steamgram.controllers.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IController<T,request,ID> {
    
    ResponseEntity<List<T>> findAll();
    ResponseEntity<T> findById(ID id);
    ResponseEntity<T> save(request entity);
    ResponseEntity<Void> deleteById(ID id);
}
