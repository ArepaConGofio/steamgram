package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.DeveloperRequest;
import com.arepacongofio.steamgram.domain.responses.DeveloperResponse;
import com.arepacongofio.steamgram.entities.Developer;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {

    // es posible que no se use

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "developedGames", ignore = true)
    Developer toEntity(DeveloperRequest developer);

    DeveloperResponse toResponse(Developer developer);

    List<DeveloperResponse> toResponseList(List<Developer> developers);
}
