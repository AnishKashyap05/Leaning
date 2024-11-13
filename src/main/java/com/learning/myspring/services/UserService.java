package com.learning.myspring.services;

import com.learning.myspring.DTO.UserCreationRequestDTO;
import com.learning.myspring.Entity.Users;

import java.util.List;

public interface UserService {
    List<Users> getAllUsers();

    Users createUser(UserCreationRequestDTO user);

    List<Users> findUserByName(String name);
}
