package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
 
@Entity 
public class UserDetailsModel {
	

	 public Long getUserDetailsId() {
		return userDetailsId;
	}


	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long userDetailsId;

	 private String firstName;
	 private String middleName;
	 private String lastName;
	 private String gender;
	 private Long userId;

	
	 @Lob 
	 @Column(name = "profile_picture", columnDefinition = "LONGBLOB")

	 private byte[] profilePicture;
		 
	public byte[] getProfilePicture() {
		return profilePicture;
	}


	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}
 

