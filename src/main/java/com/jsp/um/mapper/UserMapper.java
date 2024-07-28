package com.jsp.um.mapper;

import org.springframework.stereotype.Component;

import com.jsp.um.entity.User;
import com.jsp.um.usersrequestdto.UserRequest;
import com.jsp.um.usersresponsedto.UserResponse;
@Component
public class UserMapper {
	
	public User mapToUser(UserRequest userRequest,User user) {
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(userRequest.getPassword());
		return user;
	}
	
	public UserResponse mapToUserResponse(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.build();
				
	
	}
	

}
