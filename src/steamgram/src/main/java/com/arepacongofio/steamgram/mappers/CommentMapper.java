package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.CommentCreateRequest;
import com.arepacongofio.steamgram.domain.responses.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;

@Mapper
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    Comment toEntity(CommentCreateRequest comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    List<Comment> toEntityList(List<CommentCreateRequest> commnetList);

    CommentResponse toResponse(Comment comment);

    List<CommentResponse> toResponseList(List<Comment> commentList);
}
