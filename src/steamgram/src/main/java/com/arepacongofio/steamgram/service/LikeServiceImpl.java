package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Like;
import com.arepacongofio.steamgram.repository.LikeJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;

@Service
public class LikeServiceImpl extends AbstractService<Like,Integer>{

    LikeJpaRepository likeRepository;

    public LikeServiceImpl(LikeJpaRepository likeRepository){
        super(likeRepository);
    }
    
}
