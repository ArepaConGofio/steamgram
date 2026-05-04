package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.LikeRequest;
import com.arepacongofio.steamgram.domain.responses.LikeResponse;
import com.arepacongofio.steamgram.entities.Like;
import com.arepacongofio.steamgram.service.interfaces.IPostService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

@Mapper(componentModel = "spring", uses = { IUserService.class, IPostService.class })
public interface LikeMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "user", source = "idUser")
    @Mapping(target = "post", source = "idPost")
    Like toEntity(LikeRequest like);

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idPost", source = "post.id")
    LikeResponse toResponse(Like like);

    List<LikeResponse> toResponseList(List<Like> likes);

}
