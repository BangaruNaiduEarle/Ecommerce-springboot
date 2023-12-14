package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserDetailsModel;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetailsModel, Long> {

}
