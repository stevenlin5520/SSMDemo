package com.steven.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.steven.demo.dao.UserMapper;
import com.steven.demo.dto.User;
import com.steven.demo.dto.UserExample;
import com.steven.demo.dto.UserExample.Criteria;
import com.steven.demo.services.UserService;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public User findUserById(String userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		
		if(user != null) {
			return user;
		}
		return null;
	}

	public List<User> findUserAll() {
		List<User> users = userMapper.selectByExample(new UserExample());
		
		if(users.size()>0) {
			return users;
		}
		return null;
	}

	@Before("execution(* com.steven.demo.service.*.delete*(..))")
	public int deleteById(String userId) {
		return userMapper.deleteByPrimaryKey(userId);
	}

	@Before("execution(* com.steven.demo.service.*.insert*(..))")
	public int insertUser(User user) {
		return userMapper.insert(user);
	}

	public List<User> selectUserPhone(String phone) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserPhoneEqualTo(phone);
		List<User> userTmp = userMapper.selectByExample(example);
		
		if(userTmp.size()>0) {
			return userTmp;
		}
		return null;
	}

	public List<User> selectUserEmail(String email) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserEmailEqualTo(email);
		List<User> userTmp = userMapper.selectByExample(example);
		
		if(userTmp.size()>0) {
			return userTmp;
		}
		return null;
	}

	public List<User> selectUsername(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(username);
		List<User> userTmp = userMapper.selectByExample(example);
		
		if(userTmp.size()>0) {
			return userTmp;
		}
		return null;
	}

	public User findUser(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		UserExample example2 = new UserExample();
		Criteria criteria2 = example2.createCriteria();
		List<User> userTmp ;
		
		if(user.getUserName()!=null) {
			criteria.andUserNameEqualTo(user.getUserName());
			userTmp = userMapper.selectByExample(example);
			if(userTmp.size()>0) {
				return userTmp.get(0);
			}else {
				if(user.getUserPhone()!=null) {
					criteria2.andUserPhoneEqualTo(user.getUserPhone());
					userTmp = userMapper.selectByExample(example2);
					if(userTmp.size()>0) {
						return userTmp.get(0);
					}
				}
			}
		}
		return null;
	}

	@Before("execution(* com.steven.demo.service.*.update*(..))")
	public int updateUser(User user) {
		int count = userMapper.updateByPrimaryKeySelective(user);
		return count;
	}

	public List<User> findUserByExample(User user) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		String name = user.getUserName();
		String phone = user.getUserPhone();
		String email = user.getUserEmail();
		Long status = user.getUserType();
		if(name != null && name.length() > 0) {
			criteria.andUserNameLike("%"+name+"%");
		}
		if(phone != null && phone.length() > 0) {
			criteria.andUserPhoneLike("%"+phone+"%");
		}
		if(email != null && email.length() > 0) {
			criteria.andUserEmailLike("%"+email+"%");
		}
		if(status != null) {
			criteria.andUserTypeEqualTo(status);
		}
		
		List<User> list = userMapper.selectByExample(example);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public int deleteUser(User user) {
		if(user.getUserId() == null || user.getUserId().length() <= 0) {
			return 0;
		}
		return userMapper.deleteByPrimaryKey(user.getUserId());
	}

	
	public List<User> allUserByPermission(User user) {
		Long type = user.getUserType();
		UserExample example = new UserExample();
		if(type == 1) {
			List<Long> tmp = new ArrayList<Long>();
			tmp.add(0L);
			tmp.add(1L);
			example.createCriteria().andUserTypeIn(tmp);
		}else if(type == 0) {
			example.createCriteria().andUserTypeEqualTo(0L);
		}else {
			
		}
		List<User> result = userMapper.selectByExample(example);
		
		//杩斿洖缁撴灉
		if(result != null && result.size() > 0) {
			return result;
		}
		return null;
	}

	
	//鎵归噺鏇存柊鐨勯�昏緫
	@Around("execution(* com.steven.demo.service.*.update*(..))")
	public int updateUserBatch(List<User> users) {
		try {
			update(users);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
	public void update(List<User> users) throws Exception {
		for (User user : users) {
			userMapper.updateByPrimaryKeySelective(user);
		}
	}

}
