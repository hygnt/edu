package com.shareku.service.auth;

import java.util.List;

import com.shareku.entity.auth.Role;

public interface RoleService {
	public List<Role> listAllRoles();
	public Role getRoleById(int roleId);
	public boolean insertRole(Role role);
	public boolean updateRoleBaseInfo(Role role);
	public void deleteRoleById(int roleId);
	public void updateRoleRights(Role role);
}
