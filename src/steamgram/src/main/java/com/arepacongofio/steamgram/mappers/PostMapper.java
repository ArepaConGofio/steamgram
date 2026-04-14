package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.PostCreateRequest;
import com.arepacongofio.steamgram.domain.PostResponse;
import com.arepacongofio.steamgram.entities.Post;

@Mapper
public interface PostMapper {
    
    Post toEntity(PostCreateRequest post);
    
    PostResponse toResponse(Post post);
}
