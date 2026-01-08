package com.app.ecom.controller;

import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("")
    public ResponseEntity<List<User>> allUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(
                user
        ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id),  HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody User user){
        boolean updated = userService.updateUserById(id, user);
        return updated ? ResponseEntity.ok("Updated successfully") : ResponseEntity.notFound().build();
    }
}
