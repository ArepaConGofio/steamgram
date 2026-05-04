package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.responses.ReviewResponse;
import com.arepacongofio.steamgram.domain.requests.ReviewRequest;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.service.interfaces.IGameService;
import com.arepacongofio.steamgram.service.interfaces.IUserService;

@Mapper(componentModel = "spring", uses = {IUserService.class, IGameService.class})
public interface ReviewMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "user", source = "idUser")
    @Mapping(target = "game", source = "idGame")
    Review toEntity(ReviewRequest review);

    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idGame", source = "game.id")
    @Mapping(target = "nicknameUser", source = "user.nickname")
    @Mapping(target = "gameName", source = "game.title")
    ReviewResponse toResponse(Review review);

    List<ReviewResponse> toResponseList(List<Review> reviews);

}
