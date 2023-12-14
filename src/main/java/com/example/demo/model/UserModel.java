package com.example.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Builder
@Entity
@ToString
public class UserModel {
	
	public UserModel(Long userId, String email, String userName, String password, String name, Long phoneNo,
			List<AddressModel> addresses) {
		super();
		this.userId = userId;
		this.email = email;
		this.userName = userName;
		this.password = password;
		Name = name;
		this.phoneNo = phoneNo;
		this.addresses = addresses;
	}
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
    private String email;
    private String userName;
    private String password;
    private String Name;
	private Long phoneNo;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<AddressModel> addresses;
	
	
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
//	public Set<Role> getRole() {
//		return role;
//	}
//	public void setRole(Set<Role> role) {
//		this.role = role;
//	}
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinTable(name = "USER_ROLE",
//            joinColumns = {
//                    @JoinColumn(name = "USER_ID")
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "ROLE_ID")
//            }
//    )
//    private Set<Role> role;

	
	public UserModel() {
		
	}
	
	public Long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	
	public UserModel(Long userId) {
        this.userId = userId;
    }
	
	
	public Long getUserId() {
		return userId;
	}
	public List<AddressModel> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressModel> addresses) {
		this.addresses = addresses;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
//	@Override
//	public String getUsername() {
//		return email;
//	}
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//	
	

}
