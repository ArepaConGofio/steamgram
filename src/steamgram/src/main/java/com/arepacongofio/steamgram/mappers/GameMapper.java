package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.GameRequest;
import com.arepacongofio.steamgram.domain.responses.GameCreationResponse;
import com.arepacongofio.steamgram.entities.Game;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "developer", ignore = true)
    Game toEntity(GameRequest game);

    @Mapping(target = "idDeveloper", ignore = true)
    @Mapping(target = "name", source = "title")
    GameCreationResponse toResponse( Game game);

    List<GameCreationResponse> toResponseList(List<Game> games);
}
