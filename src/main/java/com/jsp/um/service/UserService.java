package com.jsp.um.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.um.entity.User;
import com.jsp.um.usersrequestdto.UserRequest;
import com.jsp.um.usersresponsedto.UserResponse;
import com.jsp.um.utility.ResponseStructure;

public interface UserService {

//	ResponseEntity<ResponseStructure<User>> saveUser(User user);
	
	public ResponseEntity<ResponseStructure<User>> findUserById(int userId);
	
	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user);
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId);
	
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser();

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

}
