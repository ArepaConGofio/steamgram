package com.arepacongofio.steamgram.service.interfaces;

import com.arepacongofio.steamgram.domain.requests.UserRequest;
import com.arepacongofio.steamgram.domain.responses.UserResponse;
import com.arepacongofio.steamgram.entities.User;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public interface IUserService extends IGenericService<User, Integer> {

    public UserResponse createUser(UserRequest request);

}
