//package com.example.demo;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.model.UserDetailsModel;
//import com.example.demo.service.UserDetailsService;
//
//@RestController
//@RequestMapping
//public class UserDetailsController {
////	 private final UserDetailsService userDetailsService;
////
////	    @Autowired
////	    public UserDetailsController(UserDetailsService userDetailsService) {
////	        this.userDetailsService = userDetailsService;
////	    }
//
//	    @GetMapping("/userDetails/getAllUserDetails")
//	    public ResponseEntity<List<UserDetailsModel>> getAllUserDetails() {
//	        List<UserDetailsModel> userDetails = userDetailsService.getAllUserDetails();
//	        return new ResponseEntity<>(userDetails, HttpStatus.OK);
//	    }
//
//	    @GetMapping("/userDetails/getUserDetailsById/{userDetailsId}")
//	    public ResponseEntity<UserDetailsModel> getUserDetailsById(@PathVariable Long userDetailsId) {
//	        Optional<UserDetailsModel> userDetails = userDetailsService.getUserDetailsById(userDetailsId);
//	        return userDetails.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	    }
//
//		/*
//		 * @PostMapping("/userDetails/addUserDetails") public
//		 * ResponseEntity<UserDetailsModel> addUserDetails(@RequestBody UserDetailsModel
//		 * userDetails) { UserDetailsModel newUserDetails =
//		 * userDetailsService.saveUserDetails(userDetails); return new
//		 * ResponseEntity<>(newUserDetails, HttpStatus.CREATED); }
//		 */
//	    
//	    @PostMapping("/userDetails/saveUserDetails")
//	    public String saveUserDetails(@RequestParam("file") MultipartFile file,
//	            @RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
//	            @RequestParam("lastName") String lastName, @RequestParam("gender") String gender,
//	            @RequestParam("userId") Long userId) throws IOException {
//	        return userDetailsService.saveUserDetails(file, firstName, middleName, lastName, gender, userId);
//	    }
//	    
//	    
//	    @PutMapping("/userDetails/updateUserDetails/{userDetailsId}")
//	    public String updateUserDetails(@PathVariable Long userDetailsId, @RequestParam("file") MultipartFile file,
//	            @RequestParam("firstName") String firstName, @RequestParam("middleName") String middleName,
//	            @RequestParam("lastName") String lastName, @RequestParam("gender") String gender) throws IOException {
//	        return userDetailsService.updateUserDetails(userDetailsId, file, firstName, middleName, lastName, gender);
//	    }
//	    
////	    public ResponseEntity<?> saveUserDetails(@PathVariable Long userId,@ModelAttribute UserDetailsModel userDetails,
////	                                                            @RequestParam("profilePicture") MultipartFile profilePicture) {
////	    	 try {
////	    	        // Convert MultipartFile to byte[]
////	    	        byte[] profilePictureBytes = profilePicture.getBytes();
////
////	    	        // Set the byte[] to the userDetailsModel
////	    	        userDetails.setProfilePicture(profilePictureBytes);
////
////	    	        // Save user details
////	    	        String savedUserDetails = userDetailsService.saveUserDetails(userId, userDetails,profilePicture);
////
////	    	        return ResponseEntity.ok(savedUserDetails);
////	        } catch (IOException e) {
////	            // Handle the exception appropriately (e.g., log it, return an error response)
////	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
////	        }
////	    	
////	    	/*
////			 * UserDetailsModel savedUserDetails =
////			 * userDetailsService.saveUserDetails(userId,userDetails, profilePicture);
////			 * return ResponseEntity.ok(savedUserDetails);
////			 */
////	    }
//
//
////	    @PutMapping("/userDetails/updateUserDetails")
////	    public ResponseEntity<UserDetailsModel> updateUserDetails(
////	            @RequestBody UserDetailsModel userDetails,
////	            @RequestParam("profilePicture") MultipartFile profilePictureFile
////	            ) {
////	        UserDetailsModel updatedUserDetails = userDetailsService.updateUserDetails(userDetails,profilePictureFile);
////	        return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
////	    }
//	    
//
//	    @DeleteMapping("/userDetails/deleteUserDetails/{userDetailsId}")
//	    public ResponseEntity<String> deleteUserDetails(@PathVariable Long userDetailsId) {
//	        userDetailsService.deleteUserDetails(userDetailsId);
//	        return new ResponseEntity<>("User details deleted successfully", HttpStatus.OK);
//	    }
//	    
//}
