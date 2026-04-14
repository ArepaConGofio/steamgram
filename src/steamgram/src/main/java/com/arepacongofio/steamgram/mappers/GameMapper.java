package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.GameCreateRequest;
import com.arepacongofio.steamgram.domain.GameCreationResponse;
import com.arepacongofio.steamgram.entities.Game;

@Mapper
public interface GameMapper {
    
    Game toEntity(GameCreateRequest game);

    GameCreationResponse toResponse( Game game);
}
