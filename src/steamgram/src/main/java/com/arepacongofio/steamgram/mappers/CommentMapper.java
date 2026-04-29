package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.CommentRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Comment toEntity(CommentRequest comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    List<Comment> toEntityList(List<CommentRequest> commnetList);

    CommentResponse toResponse(Comment comment);

    List<CommentResponse> toResponseList(List<Comment> commentList);
}
