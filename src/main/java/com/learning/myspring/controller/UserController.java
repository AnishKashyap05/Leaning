package com.learning.myspring.controller;

import com.learning.myspring.DTO.UserCreationRequestDTO;
import com.learning.myspring.Entity.Users;
import com.learning.myspring.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Users createUser(@RequestBody UserCreationRequestDTO user) {
        return userService.createUser(user);
    }

    @GetMapping("/{name}")
    public List<Users> getUserByName(@PathVariable("name") String name){
        return userService.findUserByName(name);
    }
}
