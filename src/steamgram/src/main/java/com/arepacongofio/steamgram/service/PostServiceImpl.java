package com.arepacongofio.steamgram.service;


import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Post;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class PostServiceImpl extends AbstractService<Post,Integer> {

    PostJpaRepository repository;
    
    public PostServiceImpl(PostJpaRepository repository) {
        super(repository);
    }
    
}
