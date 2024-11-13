package com.learning.myspring.services.impl;

import com.learning.myspring.DTO.UserCreationRequestDTO;
import com.learning.myspring.Entity.Users;
import com.learning.myspring.repository.UserRepository;
import com.learning.myspring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users createUser(UserCreationRequestDTO newUser) {
        Users user = new Users();
        user.setName(newUser.getName());
        user.setAge(newUser.getAge());
        return userRepository.save(user);
    }

    @Override
    public List<Users> findUserByName(String name){
        return userRepository.findByName(name);
    }
}
