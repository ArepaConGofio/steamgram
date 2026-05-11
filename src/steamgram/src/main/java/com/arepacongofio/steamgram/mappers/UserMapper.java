package com.arepacongofio.steamgram.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "games", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User toEntity(UserRequest user);

    @Mapping(source = "games", target = "gamesCount")
    @Mapping(source = "reviews", target = "reviewsCount")
    @Mapping(source = "posts", target = "postsCount")
    UserResponse toResponse(User user);

    List<UserResponse> toResponseList(List<User> users);

    default int mapListToInt(List<?> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
