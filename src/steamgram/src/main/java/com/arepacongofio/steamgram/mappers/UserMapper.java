package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.domain.requests.UserCreateRequest;
import com.arepacongofio.steamgram.entities.User;

@Mapper
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateRequest user);
    
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}
