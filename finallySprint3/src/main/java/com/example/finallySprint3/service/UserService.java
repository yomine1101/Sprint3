package com.example.finallySprint3.service;

import com.example.finallySprint3.controller.dto.request.CreateUserRequest;
import com.example.finallySprint3.controller.dto.request.UpdateRequest;
import com.example.finallySprint3.model.entriy.User;
import com.example.finallySprint3.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserService {

    @Autowired
    UserRepo userRepo;


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User getUserById(int id){
        return userRepo.findById(id);
    }

    public CreateUserRequest createUser(CreateUserRequest request){
        User checkId = userRepo.findById(request.getId());

        if(checkId == null){
            User user = new User();

            user.setName(request.getName());
            user.setAge(request.getAge());

            userRepo.save(user);
        }

        return request;
    }

    public String updateUser(int id, UpdateRequest request){
        try {
            User user = userRepo.findById(id);
            if(user != null){
                user.setAge(request.getAge());
                userRepo.save(user);
                return "異動成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data Not Found";
    }

    public String deleteUser(int id){
        User user = userRepo.findById(id);
        if(user == null){
            return "Data Not Found";
        }
        userRepo.deleteById(id);
        return "All Delete";
    }
}
