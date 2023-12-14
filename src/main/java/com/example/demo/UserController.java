package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.JwtHelper;
//import com.example.demo.configuration.UserAuthenticationProvider;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping
public class UserController {
//	@Autowired
//	private UserService userservice;
	
	@Autowired
	private JwtHelper jwtHelper;
	private final UserService userservice;

	@Autowired
	public UserController(UserService userService) {
	    this.userservice = userService;
	}
//	@Autowired
//    private UserAuthenticationProvider userAuthenticationProvider;
	
	
	
//	@PostMapping("/user/register")
//	public ResponseEntity<?> addUser(@RequestBody UserModel u){
//		//UserModel user=userservice.addUser(u);
//		 UserModelResponse response = userservice.addUser(u);
//		 if (response.getUser() != null) {
//		        return new ResponseEntity<>(response.getUser(), HttpStatus.CREATED);
//		 } 
//		 else 
//		 {
//		        Map<String, String> responseBody = new HashMap<>();
//		        responseBody.put("message", response.getMessage());
//		        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
//		 }
//			/*
//			 * if(user!=null) { return new ResponseEntity<>(user,HttpStatus.CREATED); } else
//			 * { Map<String, String> response = new HashMap<>(); response.put("message",
//			 * "Invalid user data or user already exists"); // Customize the message as
//			 * needed return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//			 * //return new ResponseEntity<UserModel>(user,HttpStatus.BAD_REQUEST); }
//			 */
//	}
	
	
	 
//	@PostConstruct
//    public void initRoleAndUser() {
//        // Your initialization logic here, you might want to get the UserModel from somewhere
//        UserModel user = userservice.getUserByUsername(null);
//        userservice.initRoleAndUser(user.getUserName(), user.getPassword(), user.getEmail(), user.getPhoneNo());
//    }
	
//	@PostConstruct
//    public void initRoleAndUser(@RequestBody UserModel request) {
//        userservice.initRoleAndUser(request.getUserName(), request.getPassword(), request.getEmail(),request.getPhoneNo());
//    }
//	
//	@PostMapping({"/registerNewUser"})
//	public UserModel registerNewUser(@RequestBody UserModel user) {
//	        return userservice.registerNewUser(user);
//	}

	
	
	
	
	@GetMapping("/user/getAllUser")
	public List<UserModel> getAllUser(){
		return userservice.getallUser();
	}
	@GetMapping("/user/getUserById/{userId}")
	public ResponseEntity<UserModel> getUserById(@PathVariable Long userId){
		UserModel user=userservice.getUserById(userId);
		
		if(user!=null) {
			return new ResponseEntity<UserModel>(user,HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<UserModel>(user,HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/user/updateUser/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable Long userId,@RequestBody UserModel newUser){
		 ResponseEntity<String> response = userservice.updateUser(userId, newUser);
		 return response;
		   
		/*
		 *UserModel user=userservice.updateUser(userId,newUser);
		 * 
		 * if(user!=null) { return new ResponseEntity<Object>(user,HttpStatus.OK); }
		 * else { return new
		 * ResponseEntity<Object>("No user available to update",HttpStatus.BAD_REQUEST);
		 * }
		 */
	}
	@DeleteMapping("/user/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId){
		boolean result=userservice.deleteUser(userId);
		
		if(result) {
			return new ResponseEntity<String>("Object Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No User Found",HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody @Valid SignUpDto signUpDto) {
        ResponseEntity<String> response = userservice.addUser(signUpDto);
        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.created(URI.create("/users/" + signUpDto.getUserName()))
                    .body("User registered successfully.");
        } else {
            return response;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        ResponseEntity<?> response = userservice.login(credentialsDto);
        if (response.getStatusCode().is2xxSuccessful()) {
            UserDto userDto = (UserDto) response.getBody();	
            String token = jwtHelper.generateToken(userDto);
            userDto.setToken(token);

//            userDto.setToken(userAuthenticationProvider.createToken(userDto.getUserName()));
            return ResponseEntity.ok(userDto);
        } else {
            return response;
        }
    }
	
}

