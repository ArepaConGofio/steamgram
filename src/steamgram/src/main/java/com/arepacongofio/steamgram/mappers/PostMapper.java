package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.PostRequest;
import com.arepacongofio.steamgram.domain.responses.PostResponse;
import com.arepacongofio.steamgram.entities.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "publicationDate", ignore = true)
    Post toEntity(PostRequest post);
    
    PostResponse toResponse(Post post);

    List<PostResponse> toResponseList(List<Post> posts);
}
