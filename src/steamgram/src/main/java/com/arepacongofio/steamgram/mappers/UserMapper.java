package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.arepacongofio.steamgram.domain.UserCreateRequest;
import com.arepacongofio.steamgram.domain.UserResponse;
import com.arepacongofio.steamgram.entities.User;

@Mapper
public interface UserMapper {
    
    User toEntity(UserCreateRequest user);
    
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);
}
