package com.example.finallySprint3.model;

import com.example.finallySprint3.model.entriy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    User findById(int id);
}
