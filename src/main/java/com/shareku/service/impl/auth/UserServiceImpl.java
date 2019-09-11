package com.shareku.service.impl.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.shareku.entity.auth.User;
import com.shareku.mapper.auth.UserMapper;
import com.shareku.service.auth.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserById(userId);
	}

	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		int count = userMapper.getCountByName(user.getLoginname());
		if(count>0){
			return false;
		}else{
			userMapper.insertUser(user);
			return true;
		}
		
	}

	public List<User> listPageUser(User user){
		return userMapper.listPageUser(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}

	public void updateUserBaseInfo(User user){
		userMapper.updateUserBaseInfo(user);
	}
	
	public void updateUserRights(User user){
		userMapper.updateUserRights(user);
	}
	
	public User getUserByNameAndPwd(String loginname, String password) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		return userMapper.getUserInfo(user);
	}
	
	public void deleteUser(int userId){
		userMapper.deleteUser(userId);
	}

	public User getUserAndRoleById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUserAndRoleById(userId);
	}

	public void updateLastLogin(User user) {
		// TODO Auto-generated method stub
		userMapper.updateLastLogin(user);
	}

	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return userMapper.listAllUser();
	}

	@Override
	public User findUserByLoginname(String loginname) {
		// TODO Auto-generated method stub
		return userMapper.findUserByLoginname(loginname);
	}

	@Override
	public void updateUserBaseInfoByLoginname(User user) {
		this.userMapper.updateUserBaseInfoByLoginname(user);
	}
	
}
