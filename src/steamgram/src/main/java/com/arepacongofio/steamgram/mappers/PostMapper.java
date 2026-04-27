package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.PostCreateRequest;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.entities.Post;

@Mapper
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "publicationDate", ignore = true)
    Post toEntity(PostCreateRequest post);
    
    PostResponse toResponse(Post post);
}
