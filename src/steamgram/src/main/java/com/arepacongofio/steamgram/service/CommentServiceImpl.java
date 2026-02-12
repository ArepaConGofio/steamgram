package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Comment;
import com.arepacongofio.steamgram.repository.CommentJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

@Service                                                                
public class CommentServiceImpl implements IService<Comment,Integer>{

    CommentJpaRepository repository;
    
    public CommentServiceImpl(CommentJpaRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Comment findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Comment save(Comment entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        Comment deleteComment = findById(id);
        repository.delete(deleteComment);
        return true;
    }
    
}
