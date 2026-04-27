package com.arepacongofio.steamgram.mappers;

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

    CommentResponse toResponse(Comment comment);
}
