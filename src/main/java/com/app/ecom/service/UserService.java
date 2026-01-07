package com.app.ecom.service;

import com.app.ecom.dao.UserRepo;
import com.app.ecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repository;

    List<User> users = new ArrayList<>();
    private Long userId = 1L;


    public List<User> getAllUsers()
    {
        return repository.findAll();
    }

    public User addUser(User user){
        user.setId(userId++);
        return repository.save(user);
    }

    public User getUserById(Long id){
        return repository.findById(id).orElse(null);

    }

    public boolean updateUserById(Long id, User user){
        return repository.findById(id).map(existingUser -> {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            return true;
        }).orElse(false);
    }
}
