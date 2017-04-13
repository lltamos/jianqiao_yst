package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.Role;

public interface RoleService {
	public Role findById(int id);
	public List<Role> selectAll();
	public void add(Role role);
	public void deleteById(int id);
	public void update(Role role);
	public List<Role> queryList();
	
	public Role findRoleInfo(Integer role_id);
	public AppResult updateRole(Role role);
	public AppResult addRole(Role role);
	public AppResult updateResource(Role role);
}
