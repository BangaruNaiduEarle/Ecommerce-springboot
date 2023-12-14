package com.example.demo.service;

import java.net.URI;
import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.AppException;
import com.example.demo.mappers.UserMapper;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.model.UserModel;
import com.example.demo.repo.UserRepo;

//import jakarta.transaction.Transactional;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {
	
	  @Autowired
      private UserRepo userrepo;
	  
	  @Autowired
	  private PasswordEncoder passwordEncoder;
	  
	 
	  
	  private final UserMapper userMapper;

	    @Autowired
	    public UserService(UserMapper userMapper) {
	        this.userMapper = userMapper;
	    }

	   
//    private final PasswordEncoder passwordEncoder;
//	
//    @Autowired
//    public UserService(UserRepo userrepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
//        this.userrepo = userrepo;
//        this.roleRepo = roleRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
	
//    @Transactional
//	 public void initRoleAndUser(String userName, String password, String email,Long phoneNo) {
////	        Role userRole = new Role();
////
////		   if (!roleRepo.existsById("User")) {
////	        userRole.setRoleName("User");
////	        userRole.setRoleDescription("Default role for newly created record");
////	        roleRepo.save(userRole);
////		    }
//	        UserModel user = new UserModel();
//	        user.setUserName(userName);
//	        if (password != null) {
//	            user.setPassword(getEncodedPassword(password));
//	        } else {
//	            user.setPassword(getEncodedPassword("defaultPassword"));
//             }
//	        //user.setPassword(getEncodedPassword(password));
//	        user.setEmail(email);
//	        user.setPhoneNo(phoneNo);
////	        Set<Role> userRoles = new HashSet<>();
////	        userRoles.add(userRole);
////	        user.setRole(userRoles);
//	        userrepo.save(user);
//	    }

//	    public UserModel registerNewUser(UserModel user) {
//	        Role role = roleRepo.findById("User").orElseThrow(() -> new RuntimeException("Role 'User' not found"));
//	        Set<Role> userRoles = new HashSet<>();
//	        userRoles.add(role);
//	        user.setRole(userRoles);
//	        if (user.getPassword() != null) {
//	            user.setPassword(getEncodedPassword(user.getPassword()));
//	        } else {
//	            user.setPassword(getEncodedPassword("defaultPassword"));
//	        }
//	        //user.setPassword(getEncodedPassword(user.getPassword()));
//
//	        return userrepo.save(user);
//	    }
//
//	    public String getEncodedPassword(String password) {
//	        return passwordEncoder.encode(password);
//	    }
	    
    
	  
	  public UserDto findByUserName(String userName) {
	        UserModel user = userrepo.findOptionalByUserName(userName)
	                .orElseThrow();
	        return userMapper.toUserDto(user);
	    }

	
	
	public class UserModelResponse {
	    private UserDto user;
		private String message;

	    public UserDto getUser() {
			return user;
		}
		public void setUser(UserDto user) {
			this.user = user;
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}

	}
//	public UserDetailsService userDetailsService() {
//	      return new UserDetailsService() {
//	          @Override
//	          public UserDetails loadUserByUsername(String username) {
//	              return userrepo.findOptionalByUserName(username)
//	                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//	          }
//	      };
//	  }
	
	
	public ResponseEntity<String> addUser(SignUpDto signUpDto) {
        Optional<UserModel> optionalUser = userrepo.findOptionalByUserName(signUpDto.getUserName());

        if (optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("UserName is already in use by another user.");
        }

        // Check if email is already associated with another user
        if (userrepo.findByEmail(signUpDto.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email is already in use by another user.");
        }

        UserModel user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        UserModel savedUser = userrepo.save(user);

        // You can customize the success message as per your requirement
        String successMessage = "User registered successfully.";

        return ResponseEntity.created(URI.create("/users/" + savedUser.getUserId()))
                .body(successMessage);
    }

	
	
	
	 public ResponseEntity<?> login(CredentialsDto credentialsDto) {
	        UserModel user = userrepo.findOptionalByUserName(credentialsDto.getUserName())
	                .orElse(null);

	        if (user == null) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("User not found");
	        }

	        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
	            UserDto userDto = userMapper.toUserDto(user);
	            return ResponseEntity.ok(userDto);
	        }

	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid password");
	    }

	
	
	
//	public UserModelResponse addUser(SignUpDto signUpDto) {
//        UserModelResponse response = new UserModelResponse();
//
//		if (userrepo.findByUserName(signUpDto.getUserName()) != null) {
//            // Handle duplicate phone number
//	        response.setMessage("UserName is already in use by another user.");
//	        return response;
//
//        }
//
//        // Check if email is already associated with another user
//        if (userrepo.findByEmail(signUpDto.getEmail()) != null) {
//            // Handle duplicate email
//             response.setMessage("Email is already in use by another user.");
//             return response;
//                    
//        }
//        Optional<UserModel> optionalUser = userrepo.findOptionalByUserName(signUpDto.getUserName());
//        UserModel user = userMapper.signUpToUser(signUpDto);
//        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
//
//        UserModel savedUser = userrepo.save(user);
//
//        UserModelResponse successResponse = new UserModelResponse();
//        successResponse.setUser(userMapper.toUserDto(savedUser));
//        successResponse.setMessage("User registered successfully.");
//
//        return successResponse;
//
//        // If everything is successful
////        UserModelResponse response = new UserModelResponse();
////        response.setUser(savedUser);
////        return response;
//		//return userrepo.save(u);
//	}
	
	
	
	public List<UserModel> getallUser(){
		return userrepo.findAll();
	}
	
	public UserModel getUserById(Long userId) {
		if(userrepo.findById(userId).isPresent()) {
			return userrepo.findById(userId).get();
		}
		else {
			return null;
		}
	}
	
	
	
	 public ResponseEntity<String> updateUser(Long userId, UserModel newUser) {
	        UserModel oldUser = userrepo.findById(userId).orElse(null);
	        if (oldUser == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                    .body("User not found.");
	        }

	        UserModel existingUserWithUpdatedPhone = userrepo.findByPhoneNo(newUser.getPhoneNo());
	        if (existingUserWithUpdatedPhone != null && !existingUserWithUpdatedPhone.getUserId().equals(userId)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Phone number is already in use by another user.");
	        }

	        UserModel existingUserWithUpdatedEmail = userrepo.findByEmail(newUser.getEmail());
	        if (existingUserWithUpdatedEmail != null && !existingUserWithUpdatedEmail.getUserId().equals(userId)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Email is already in use by another user.");
	        }

	        oldUser.setName(newUser.getName());
	        oldUser.setEmail(newUser.getEmail());
	        oldUser.setPassword(newUser.getPassword());
	        oldUser.setPhoneNo(newUser.getPhoneNo());

	        UserModel savedUser = userrepo.save(oldUser);

	        String successMessage = "User updated successfully.";
	        return ResponseEntity.ok(successMessage);
	    }
	
	
	
	
	
	
	
//	public UserModelResponse updateUser(Long userId,UserModel newUser) {
//		
//            UserModel oldUser = userrepo.findById(userId).orElse(null);
//		    if (oldUser == null) {
//	            // Handle user not found
//	            throw new RuntimeException("User not found.");
//	        }
//
//	        // Check if the updated phone number is already associated with another user
//	        UserModel existingUserWithUpdatedPhone = userrepo.findByPhoneNo(newUser.getPhoneNo());
//	        if (existingUserWithUpdatedPhone != null && !existingUserWithUpdatedPhone.getUserId().equals(userId)) {
//	            // Handle duplicate phone number during update
//	            //throw new RuntimeException("Phone number is already in use by another user.");
//	        	UserModelResponse response = new UserModelResponse();
//
//		        response.setMessage("Phone number is already in use by another user.");
//		        return response;
//	        }
//
//	        // Check if the updated email is already associated with another user
//	        UserModel existingUserWithUpdatedEmail = userrepo.findByEmail(newUser.getEmail());
//	        if (existingUserWithUpdatedEmail != null && !existingUserWithUpdatedEmail.getUserId().equals(userId)) {
//	            // Handle duplicate email during update
//	            //throw new RuntimeException("Email is already in use by another user.");
//	        	UserModelResponse response = new UserModelResponse();
//	            response.setMessage("Email is already in use by another user.");
//	            return response;
//	        }
//		    //if(userrepo.findById(userId).isPresent()) {
//			//UserModel oldUser=userrepo.findById(userId).get();
//			oldUser.setName(newUser.getName());
//			oldUser.setEmail(newUser.getEmail());
//			oldUser.setPassword(newUser.getPassword());
//			oldUser.setPhoneNo(newUser.getPhoneNo());
//			
//			//return userrepo.save(oldUser);
//			UserModel savedUser = userrepo.save(oldUser);
//	        
//	        // If everything is successful
//	        UserModelResponse response = new UserModelResponse();
//	        response.setUser(savedUser);
//	        return response; 
//			
//		}
		
	public boolean deleteUser(Long userId) {
		if(userrepo.findById(userId).isPresent()) {
			userrepo.deleteById(userId);
			return true;
		}
		else {
			return false;
		}
	}
//	public boolean isValidUser(UserModel user) {
//        return userrepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) != null;
//    }
	public ResponseEntity<String> isValidUser(UserModel user) {
        if (userrepo.findByEmailAndPassword(user.getEmail(), user.getPassword()) != null) {
            return ResponseEntity.ok("User is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials.");
        }
    }
	 public UserModel getUserByUsername(String username) {
	        return userrepo.findByUserName(username);
	    }
}
