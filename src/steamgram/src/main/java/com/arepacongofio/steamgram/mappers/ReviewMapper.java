package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.ReviewCreateRequest;
import com.arepacongofio.steamgram.domain.ReviewResponse;
import com.arepacongofio.steamgram.entities.Review;

@Mapper
public interface ReviewMapper {
    
    Review toEntity(ReviewCreateRequest review);

    ReviewResponse toResponse(Review review);

}
