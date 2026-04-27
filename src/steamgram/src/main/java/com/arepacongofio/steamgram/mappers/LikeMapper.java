package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.LikeCreateRequest;
import com.arepacongofio.steamgram.entities.Like;

@Mapper
public interface LikeMapper {
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "date", ignore= true)
    Like toEntity(LikeCreateRequest like);

}
