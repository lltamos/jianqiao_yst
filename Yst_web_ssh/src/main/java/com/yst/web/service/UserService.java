package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yst.web.model.AppResult;
import com.yst.web.model.User;
import com.yst.web.utils.SubmitResultInfo;

public interface UserService {
	public User findById(int id);
	public SubmitResultInfo login(String username,String password, String imageCode, Map session);
	public List<User> selectAll();
	public void add(User user);
	public void deleteById(int id,int deleted);
	public AppResult updateInfo(User user);
	public List<User> selectByName(String username);
	public User selectByUserName(String username);
	public int count();
	public AppResult updateRole(User user);
}
