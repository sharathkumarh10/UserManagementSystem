package com.jsp.um.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jsp.um.entity.User;
import com.jsp.um.service.UserService;
import com.jsp.um.usersrequestdto.UserRequest;
import com.jsp.um.usersresponsedto.UserResponse;
import com.jsp.um.utility.ErrorStructure;
import com.jsp.um.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/v1")
@Tag(name="User Endpoint",description="contains all the endpoints that are related to user entity")
public class UserController {
	@Autowired
	private UserService userService;
	@Operation(description="The endpoint is used to add",
			responses= {
			@ApiResponse(responseCode = "201",description="User created"),
			@ApiResponse(responseCode = "400",description="Invalid output",
			content= {
					@Content(schema=@Schema(oneOf = ErrorStructure.class))
			
			})
			
	})
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid
			UserRequest userRequest){
		return userService.saveUser(userRequest);
	}
		@GetMapping("/users/{userId}")
		
		public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int userId){
			return userService.findUserById(userId);
		}
		@PutMapping("/users/{userId}")
		public ResponseEntity<ResponseStructure<User>> updateUserById(@RequestBody User updatedUser,
				@PathVariable int userId){
			return userService.updateUser(userId,updatedUser);
		}
		@DeleteMapping("users/{userId}")
		public ResponseEntity<ResponseStructure<User>> deleteUserById(@PathVariable int userId) {
			return userService.deleteUser(userId);
	}
		@GetMapping("/users")
		public ResponseEntity<ResponseStructure<List<User>>> findAllUser(){
			return userService.findAllUser();
		}

}
