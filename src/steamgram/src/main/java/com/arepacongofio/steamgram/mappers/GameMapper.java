package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.GameRequest;
import com.arepacongofio.steamgram.domain.responses.GameDetailsResponse;
import com.arepacongofio.steamgram.domain.responses.GameResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

@Mapper(componentModel = "spring", uses = { IDeveloperService.class })
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "developer", source = "developerId")
    @Mapping(target = "idIgdb", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "screenshots", ignore = true)
    @Mapping(target = "platforms", ignore = true)
    Game toEntity(GameRequest game);

    @Mapping(target = "idGame", source = "id")
    @Mapping(target = "idDeveloper", source = "developer.id")
    @Mapping(target = "developerName", source = "developer.name")
    GameResponse toResponse(Game game);

    @Mapping(target = "developerName", source = "developer.name")
    @Mapping(target = "developerId", source = "developer.id")
    @Mapping(target = "platforms", source = "platforms")
    @Mapping(target = "reviewsCount", expression = "java(game.getReviews() != null ? game.getReviews().size() : 0)")
    GameDetailsResponse toDetailsResponse(Game game);

    List<GameResponse> toResponseList(List<Game> games);

    List<GameDetailsResponse> toDetailsResponseList(List<Game> games);
}
