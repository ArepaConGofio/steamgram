package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.models.Like;
import com.arepacongofio.steamgram.repository.LikeJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class LikeServiceImpl extends AbstractService<Like,Integer>{

    LikeJpaRepository repository;

    public LikeServiceImpl(LikeJpaRepository repository){
        super(repository);
    }
    
}
