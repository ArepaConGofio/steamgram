package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.domain.requests.ReviewCreateRequest;
import com.arepacongofio.steamgram.entities.Review;

@Mapper
public interface ReviewMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Review toEntity(ReviewCreateRequest review);

    ReviewResponse toResponse(Review review);

}
