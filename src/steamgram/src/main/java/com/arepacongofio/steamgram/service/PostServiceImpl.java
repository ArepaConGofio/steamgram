package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Post;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.service.interfaces.IService;

@Service
public class PostServiceImpl implements IService<Post,Integer> {

    PostJpaRepository repository;
    

    public PostServiceImpl(PostJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Post findById(Integer id) {
        return repository.findById(id).orElse(null); 
    }

    @Override
    public Post save(Post entity) {
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (!existsById(id)) {
            return false;
        }
        Post deletePost = findById(id);
        repository.delete(deletePost);
        return true;
    }
    
}
