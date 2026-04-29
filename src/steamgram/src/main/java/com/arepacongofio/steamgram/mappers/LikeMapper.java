package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.LikeRequest;
import com.arepacongofio.steamgram.domain.responses.LikeResponse;
import com.arepacongofio.steamgram.entities.Like;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "id", ignore = true )
    @Mapping(target = "date", ignore= true)
    Like toEntity(LikeRequest like);

    LikeResponse toResponse(Like like);

    List<LikeResponse> toResponseList(List<Like> likes);

}
