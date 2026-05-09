package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.CommentRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;
import com.arepacongofio.steamgram.service.interfaces.IPostService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

@Mapper(componentModel = "spring", uses = { IPostService.class, IUserService.class })
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "post", source = "idPost")
    @Mapping(target = "user", source = "idUser")
    Comment toEntity(CommentRequest comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "post", source = "idPost")
    @Mapping(target = "user", source = "idUser")
    List<Comment> toEntityList(List<CommentRequest> commnetList);

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idPost", source = "post.id")
    @Mapping(target = "nicknameUser", source = "user.nickname")
    @Mapping(target = "postTitle", source = "post.title")
    CommentResponse toResponse(Comment comment);

    List<CommentResponse> toResponseList(List<Comment> commentList);
}
