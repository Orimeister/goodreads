package com.orimeister.User.service.Controller;

import com.orimeister.User.service.entity.User;
import com.orimeister.User.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser= userService.createUser(user);
       return ResponseEntity.ok(newUser);
    }
    @GetMapping
    public ResponseEntity<?> fetchAllUsers(){
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()){
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> fetchUser(@PathVariable Integer id){
        return userService.getUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        boolean success = userService.deleteUser(id);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(404).build();
    }
}
