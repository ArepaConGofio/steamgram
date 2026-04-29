package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.domain.requests.ReviewRequest;
import com.arepacongofio.steamgram.entities.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Review toEntity(ReviewRequest review);

    ReviewResponse toResponse(Review review);

    List<ReviewResponse> toResponseList(List<Review> reviews);

}
