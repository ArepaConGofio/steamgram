package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Comment;
import com.arepacongofio.steamgram.repository.CommentJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service                                                                
public class CommentServiceImpl extends AbstractService<Comment,Integer>{

    CommentJpaRepository repository;
    
    public CommentServiceImpl(CommentJpaRepository repository){
        super(repository);
    }    
}
