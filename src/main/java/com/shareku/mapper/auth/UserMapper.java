package com.shareku.mapper.auth;

import java.util.List;

import com.shareku.entity.auth.User;

public interface UserMapper  {
	List<User> listAllUser();
	
	User getUserById(Integer userId);
	
	void insertUser(User user);
	
	void updateUser(User user);
	
	User getUserInfo(User user);
	
	int getCountByName(String loginname);
	
	void deleteUser(int userId);
	
	int getCount(User user);
	
	List<User> listPageUser(User user);
	
	User getUserAndRoleById(Integer userId);

	User findUserByLoginname(String loginname);
	
	void updateUserBaseInfo(User user);
	
	void updateUserBaseInfoByLoginname(User user);	//前台修改【个人中心】
	
	void updateUserRights(User user);
	
	void updateLastLogin(User user);

}
