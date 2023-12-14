//package com.example.demo.service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.example.demo.model.UserDetailsModel;
//import com.example.demo.model.UserModel;
//import com.example.demo.repo.UserDetailsRepo;
//@Primary
//@Service
//public class UserDetailsService {
//	private final UserDetailsRepo userDetailsRepo;
//    private static final long MAX_ALLOWED_SIZE_BYTES = 10 * 1024 * 1024; // 10 MB
//
//    @Autowired
//    public UserDetailsService(UserDetailsRepo userDetailsRepo) {
//        this.userDetailsRepo = userDetailsRepo;
//    }
//
//    public List<UserDetailsModel> getAllUserDetails() {
//        return userDetailsRepo.findAll();
//    }
//
//    public Optional<UserDetailsModel> getUserDetailsById(Long userDetailsId) {
//        return userDetailsRepo.findById(userDetailsId);
//    }
//
//	/*
//	 * public UserDetailsModel saveUserDetails(UserDetailsModel userDetails) {
//	 * return userDetailsRepo.save(userDetails); }
//	 */
//    
//	/*
//	 * public String saveUserDetails(Long userId, UserDetailsModel userDetails,
//	 * MultipartFile profilePicture) throws IOException { if
//	 * (profilePicture.getSize() > MAX_ALLOWED_SIZE_BYTES) { // Handle error: Image
//	 * size is too large return "Error: Image size is too large"; } UserModel user =
//	 * new UserModel(); user.setUserId(userId); userDetails.setUser(user);
//	 * 
//	 * try { userDetails.setProfilePicture(profilePicture.getBytes()); } catch
//	 * (IOException e) { throw new
//	 * RuntimeException("Failed to save profile picture.", e); }
//	 * 
//	 * userDetailsRepo.save(userDetails); return "Image saved in DB"; }
//	 */
//    public String saveUserDetails(MultipartFile file, String firstName, String middleName, String lastName,
//            String gender, Long userId) {
//        UserDetailsModel userDetails = new UserDetailsModel();
//        userDetails.setFirstName(firstName);
//        userDetails.setMiddleName(middleName);
//        userDetails.setLastName(lastName);
//        userDetails.setGender(gender);
//        try {
//        	if(file.getSize() > MAX_ALLOWED_SIZE_BYTES) { 
//       	       return "Error: Image size is too large"; 
//       	       }
//        	
//			userDetails.setProfilePicture(file.getBytes());
//		} 
//         catch (IOException e) {
//			e.printStackTrace();
//		}
//        userDetails.setUserId(userId);
//        userDetailsRepo.save(userDetails);
//        return "User details saved in DB";
//    }
//    
//    public String updateUserDetails(Long userDetailsId, MultipartFile file, String firstName, String middleName,
//            String lastName, String gender) {
//        UserDetailsModel userDetails = userDetailsRepo.findById(userDetailsId)
//                .orElseThrow(() -> new RuntimeException("User details not found with ID: " + userDetailsId));
//        userDetails.setFirstName(firstName);
//        userDetails.setMiddleName(middleName);
//        userDetails.setLastName(lastName);
//        userDetails.setGender(gender);
//        try {
//        	if(file.getSize() > MAX_ALLOWED_SIZE_BYTES) { 
//        	       return "Error: Image size is too large"; 
//        	       }
//			userDetails.setProfilePicture(file.getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        userDetailsRepo.save(userDetails);
//        return "User details updated in DB";
//    }
//
//
//	/*
//	 * public UserDetailsModel updateUserDetails(UserDetailsModel userDetails,
//	 * MultipartFile profilePicture) { UserDetailsModel existingUserDetails =
//	 * userDetailsRepo.findById(userDetails.getUserDetailsId()) .orElseThrow(() ->
//	 * new RuntimeException("User details not found with id: " +
//	 * userDetails.getUserDetailsId()));
//	 * 
//	 * existingUserDetails.setFirstName(userDetails.getFirstName());
//	 * existingUserDetails.setMiddleName(userDetails.getMiddleName());
//	 * existingUserDetails.setLastName(userDetails.getLastName());
//	 * existingUserDetails.setGender(userDetails.getGender());
//	 * existingUserDetails.setUserId(userDetails.getUserId());
//	 * 
//	 * try { existingUserDetails.setProfilePicture(profilePicture.getBytes()); }
//	 * catch (IOException e) { throw new
//	 * RuntimeException("Failed to update profile picture.", e); }
//	 * 
//	 * return userDetailsRepo.save(existingUserDetails); }
//	 */
//    public void deleteUserDetails(Long userId) {
//        userDetailsRepo.deleteById(userId);
//    }
//}
//	
//
//
