package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.LikeCreateRequest;
import com.arepacongofio.steamgram.entities.Like;

@Mapper
public interface LikeMapper {
    
    Like toEntity(LikeCreateRequest like);

}
