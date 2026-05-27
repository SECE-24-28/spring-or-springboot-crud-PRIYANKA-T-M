package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.User;
import com.eduhub.eduhub_backend.exception.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    static List<User> users=new ArrayList<>();
    static{
        users.add(new User("24CC012","Dharshini","dharsh"));
        users.add(new User("24CC042","Priyanka","preethi"));
        users.add(new User("24CC033","Nethra","nethu34"));
        users.add(new User("24CC036","Nivethini","nivi"));
        users.add(new User("24CC058","Tanyasri","tanya36"));
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get-user/{user-id}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        return users.stream().filter((User u)-> u.getUserId().equalsIgnoreCase(userId)).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("user","userId",userId));
    }

    @GetMapping("/search/get-user/{user-id}")
    public String searchUser(@PathVariable String userid){
        if(userid.contains(":")){
            throw new IllegalArgumentException("There is a semi colon in the userId");
        }
        return userid;
    }

    @GetMapping("/query/{user-id}")
    public ResponseEntity<User> queryUser(@RequestParam String userId){
        if(userId.contains("$")){
            throw new IllegalArgumentException("Contains special character");
        }
        return users.stream().filter((User u)->u.getUserId().equalsIgnoreCase(userId)).findFirst().map(ResponseEntity::ok)
                .orElseThrow(()->new ResourceNotFoundException("user","userId",userId));

    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        users.add(user);
        return ResponseEntity.ok(user);

    }

    @PutMapping("/update-user/{user-id}")
    public ResponseEntity<User> updateUser(@PathVariable String userid, @RequestBody User updatedUser){
        User user=users.stream().filter((User u)->u.getUserId().equalsIgnoreCase(userid)).findFirst()
                .orElseThrow(()->new ResourceNotFoundException("User","UserId",userid));
        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        return ResponseEntity.ok(user);
    }
}







