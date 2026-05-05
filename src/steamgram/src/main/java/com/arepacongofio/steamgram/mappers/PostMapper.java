package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.PostRequest;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.service.interfaces.IGameService;
import com.arepacongofio.steamgram.service.interfaces.IPostService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

@Mapper(componentModel = "spring", uses = { IUserService.class, IGameService.class, IPostService.class })
public interface PostMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "publicationDate", ignore = true)
    @Mapping(target = "user", source = "idUser")
    @Mapping(target = "game", source = "idGame")
    Post toEntity(PostRequest post);

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idGame", source = "game.id")
    @Mapping(target = "nicknameUser", source = "user.nickname")
    @Mapping(target = "gameTitle", source = "game.title")
    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);
}
