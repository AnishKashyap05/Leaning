package com.learning.myspring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
//    public final UserService userService;

//    @GetMapping
//    public List<Users> getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping
//    public Users createUser(@RequestBody UserCreationRequestDTO user) {
//        return userService.createUser(user);
//    }
//
//    @GetMapping("/{name}")
//    public List<Users> getUserByName(@PathVariable("name") String name){
//        return userService.findUserByName(name);
//    }

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/sayHi")
    public String sayHii(){
        return "Hii";
    }

    @GetMapping("/sayBye")
    public String sayBye(){
        return "GoodBye";
    }

    @GetMapping("/sayGoodBye")
    public String sayGoodBye(){
        return "GoodBye";
    }
}
