package com.example.finallySprint3.controller;

import com.example.finallySprint3.controller.dto.request.CreateUserRequest;
import com.example.finallySprint3.controller.dto.request.UpdateRequest;
import com.example.finallySprint3.model.entriy.User;
import com.example.finallySprint3.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final Logger log = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        try {
            log.info("已經發送到 Queue: " + id);
            jmsMessagingTemplate.convertAndSend("UserTest", id);

            return userService.getUserById(id);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @PostMapping
    public CreateUserRequest createUser(@RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UpdateRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }
}
