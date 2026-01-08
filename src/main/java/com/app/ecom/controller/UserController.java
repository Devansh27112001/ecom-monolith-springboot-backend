package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
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
    public ResponseEntity<List<UserResponse>> allUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id),  HttpStatus.OK);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserRequest
            userRequest){
        boolean updated = userService.updateUserById(id, userRequest);
        return updated ? ResponseEntity.ok("Updated successfully") : ResponseEntity.notFound().build();
    }
}
