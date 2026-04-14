package com.arepacongofio.steamgram.mappers;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.DeveloperCreateRequest;
import com.arepacongofio.steamgram.entities.Developer;

@Mapper
public interface DeveloperMapper {
    Developer toEntity(DeveloperCreateRequest developer);

    //TODO DeveloperResponse
}
