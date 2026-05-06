package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.GameRequest;
import com.arepacongofio.steamgram.domain.responses.GameCreationResponse;
import com.arepacongofio.steamgram.domain.responses.GameGeneralResponse;
import com.arepacongofio.steamgram.domain.responses.GameResponse;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

@Mapper(componentModel = "spring", uses = { IDeveloperService.class })
public interface GameMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "developer", source = "developerId")
    Game toEntity(GameRequest game);

    @Mapping(target = "idDeveloper", source = "developer.id")
    @Mapping(target = "name", source = "title")
    GameCreationResponse toResponse(Game game);

    @Mapping(target = "developerName", source = "developer.name")
    @Mapping(target = "developerId", source = "developer.id")
    GameGeneralResponse toDetailsResponse(Game game);

    List<GameResponse> toResponseList(List<Game> games);

    List<GameGeneralResponse> toDetailsResponseList(List<Game> games);
}
