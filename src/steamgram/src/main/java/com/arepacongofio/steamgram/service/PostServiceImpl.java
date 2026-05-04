package com.arepacongofio.steamgram.service;


import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IPostService;

@Service
public class PostServiceImpl extends AbstractService<Post,Integer> implements IPostService{

    PostJpaRepository postRepository;
    
    public PostServiceImpl(PostJpaRepository postRepository) {
        super(postRepository);
    }
    
}
