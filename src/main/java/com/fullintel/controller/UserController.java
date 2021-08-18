package com.fullintel.controller;

import com.fullintel.user.Test;
import com.fullintel.user.dao.UserDao;
import com.fullintel.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping(value = "/api/v1")
    public ResponseEntity<?> getInfo()
    {
        List<User> user = userDao.getUser("blah@gmail.com", "dhfiudshfe");
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/api/v1")
    public ResponseEntity<?> postInfo(@RequestBody User user)
    {
        return ResponseEntity.ok(userDao);
    }

    @PutMapping(value = "/api/v1")
    public ResponseEntity<?> putInfo(@RequestBody User user)
    {
        System.out.println("Hi ");
        userDao.updateUser(user.getId(), user.getFirstName(), user.getLastName());
        return ResponseEntity.ok(userDao);

    }

    /*@DeleteMapping(value = "/api/v1")
    public ResponseEntity<?> deleteInfo(@RequestBody User user)
    {
        return ResponseEntity.ok(userDao);
    }*/
}
