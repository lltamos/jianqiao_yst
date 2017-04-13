package com.yst.web.service;

import java.util.List;
import java.util.Map;

import com.yst.web.model.AppResult;
import com.yst.web.model.Menu;
import com.yst.web.model.Role;
import com.yst.web.model.vo.TreeVO;

public interface ResourceService {
	public Menu findById(int id);
	public List<Menu> selectAll();
	public AppResult add(Menu menu);
	public void deleteById(int id);
	public AppResult update(Menu menu);
	public TreeVO findRoot(Map<String,Object> map);
	public List<Menu> selectAllByPage();
	TreeVO findRoot(Map<String, Object> map, String act);
}
