package com.steven.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.steven.demo.dto.User;
import com.steven.demo.services.UserService;
import com.steven.demo.util.UUIDUtil;
import com.steven.demo.util.UserList;

@Component
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUserById")
	@ResponseBody
	public User findUserById(@RequestBody User user) {
		if(user != null && user.getUserId().length() > 0) {
			User user2 = userService.findUserById(user.getUserId());
			if(user2 != null) {
				return user2;
			}
		}
		return null;
	}
	
	
	@RequestMapping("/findUser")
	@ResponseBody
	public List<User> findUser(@RequestBody User user) {
		if(user != null) {
			List<User> list = userService.findUserByExample(user);
			if(list != null && list.size() > 0) {
				return list;
			}
		}
		return null;
	}
	
	
	@RequestMapping("/findUserAll")
	@ResponseBody
	public List<User> findUserAll(){
		return userService.findUserAll();
	}
	
	
	@RequestMapping("/deleteById")
	@ResponseBody
	public int deleteById(@RequestBody String userId) {
		return userService.deleteById(userId);
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,String> deleteUser(@RequestBody User user) {
		Map<String,String> map  = new HashMap<String, String>();
		if(user != null) {
			int count = userService.deleteUser(user);
			if(count > 0) {
				map.put("result", "OK");
				return map;
			}
		}
		map.put("count", "ERR");
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping("/update")
	public Map<String,String> update(@RequestBody User user){
		Map<String,String> map  = new HashMap<String, String>();
		if(user != null){
			int result = userService.updateUser(user);
			if(result > 0){
				map.put("result", "OK");
			}else{
				map.put("result", "ERR");
			}
		}else{
			map.put("count", "ERR");
		}
		return map;
	}
	

	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String,Integer> insertUser(HttpServletRequest request) throws ServletException, IOException{
		//返回数据操作结果
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		//存在相同的名字或手机�?
		if((Boolean)request.getAttribute("exist") == true) {
			map.put("count", 0);
			return map;
		}
		
		User user = (User) request.getAttribute("user");
		user.setUserId(UUIDUtil.getUUIDSTR());
		user.setUserType(0L);
		user.setUserSex(0L);
		Integer count = userService.insertUser(user);
		
		map.put("count",count);
		return map;
	}

	/**
	 *	在插入数据前查询是否有已注册的用户的相同信息
	 */
	@RequestMapping("/findCommonUser")
	public String findCommonUser(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) {
		User userTmp = userService.findUser(user);
		
		if(userTmp == null) {
			request.setAttribute("exist", Boolean.FALSE);
			request.setAttribute("user", user);
		}else {
			request.setAttribute("exist", Boolean.TRUE);
		}
		return "forward:/user/addUser";
	}
	
	
	
	@ResponseBody
	@RequestMapping("/forget")
	public User forgetPassword(@RequestBody User user) {
		if(user != null) {
			List<User> users = userService.findUserByExample(user);
			if(users.size() > 0) {
				return users.get(0);
			}
		}
		
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/allUserByPermission")
	public List<User> allUserByPermission(@RequestBody User user){
		if(user != null) {
			List<User> result = userService.allUserByPermission(user);
			if(result != null && result.size() > 0) {
				return result;
			}
		}
		return null;
	}
	
	
	@ResponseBody
	@RequestMapping("/updateUserBatch")
	public Map<String,String> updateUserBatch(@RequestBody UserList userList){
		List<User> users = userList.getUsers();
		Map<String,String> result = new HashMap<String, String>();
		if(users != null && users.size() > 0) {
			Integer count = userService.updateUserBatch(users);
			if(count > 0) {
				result.put("result", "OK");
				return result;
			}
		}
		result.put("result", "ERR");
		return result;
	}
	
}
