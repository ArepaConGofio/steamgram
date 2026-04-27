package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.GameCreateRequest;
import com.arepacongofio.steamgram.domain.responses.GameCreationResponse;
import com.arepacongofio.steamgram.entities.Game;

@Mapper
public interface GameMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "developer", ignore = true)
    Game toEntity(GameCreateRequest game);

    @Mapping(target = "idDeveloper", ignore = true)
    @Mapping(target = "name", source = "title")
    GameCreationResponse toResponse( Game game);
}
