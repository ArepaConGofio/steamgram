package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.CommentCreateRequest;
import com.arepacongofio.steamgram.domain.CommentResponse;
import com.arepacongofio.steamgram.entities.Comment;

@Mapper
public interface CommentMapper {

    Comment toEntity(CommentCreateRequest comment);
        
    CommentResponse toResponse(Comment comment);
}
