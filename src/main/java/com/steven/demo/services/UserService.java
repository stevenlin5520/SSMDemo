package com.steven.demo.services;

import java.util.List;

import com.steven.demo.dto.User;


public interface UserService {

	public User findUserById(String userId);
	public List<User> findUserAll();
	public List<User> findUserByExample(User user);
	public List<User> allUserByPermission(User user);
	
	public int deleteUser(User user);
	public int deleteById(String userId);
	public int insertUser(User user);
	//部分更新，null值不更新
	public int updateUser(User user);
	public int updateUserBatch(List<User> users);
	
	public List<User> selectUserPhone(String phone);
	public List<User> selectUserEmail(String email);
	public List<User> selectUsername(String username);
	public User findUser(User user);
}
