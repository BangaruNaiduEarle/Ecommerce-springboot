package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel,Long> {

	Optional<UserModel> findByEmailAndPassword(String email, String password);
	
	UserModel findByPhoneNo(Long phoneNo);

	UserModel findByEmail(String email);

    //Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findOptionalByUserName(String userName);
    UserModel findByUserName(String userName);
    //Optional<UserModel> findByOptionalUserName(String userName);

	UserModel findByPassword(String password);

	//Optional<UserModel> findByEmailNewMethod(String email);

}
