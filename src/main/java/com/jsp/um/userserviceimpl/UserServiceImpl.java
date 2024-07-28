package com.jsp.um.userserviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.um.entity.User;
import com.jsp.um.exception.UserNotFoundByIdException;
import com.jsp.um.mapper.UserMapper;
import com.jsp.um.repository.UserRepository;
import com.jsp.um.service.UserService;
import com.jsp.um.usersrequestdto.UserRequest;
import com.jsp.um.usersresponsedto.UserResponse;
import com.jsp.um.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserMapper userMapper;

//	@Override
//	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
//		user = userRepo.save(user);
//		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<User>()
//				.setStatusCode(HttpStatus.CREATED.value()).setMessage("User created").setData(user));
//	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		User user = userRepo.save(userMapper.mapToUser(userRequest,new User()));
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<UserResponse>()
				.setStatusCode(HttpStatus.CREATED.value())
				.setMessage("User created")
				.setData(userMapper.mapToUserResponse(user)));
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserById(int userId) {
		return userRepo.findById(userId)
				.map(user -> ResponseEntity.status(HttpStatus.FOUND)
						.body(new ResponseStructure<User>().setStatusCode(HttpStatus.FOUND.value())
								.setMessage("User found").setData(user)))
				.orElseThrow(() -> new UserNotFoundByIdException("Failed to find user"));
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(int userId, User user) {
		return userRepo.findById(userId).map(exUser -> {
			exUser.setUsername(user.getUsername());
			exUser.setEmail(user.getEmail());
			exUser.setPassword(user.getPassword());

			exUser = userRepo.save(exUser);

			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<User>()
					.setStatusCode(HttpStatus.OK.value()).setMessage("user updated").setData(exUser));
		}).orElseThrow(() -> new UserNotFoundByIdException("failed to updated user"));
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> deleteUser(int userId) {
		return userRepo.findById(userId).map(user -> {
			userRepo.delete(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<User>()
					.setStatusCode(HttpStatus.OK.value()).setMessage("user deleted").setData(user));
		}).orElseThrow(() -> new UserNotFoundByIdException("failed to delete user"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser() {
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<User>>()
				.setData(userRepo.findAll()).setMessage("users found").setStatusCode(HttpStatus.FOUND.value()));
	}

}
