package com.yst.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.ResourceDao;
import com.yst.web.model.Menu;
import com.yst.web.utils.BaseDaoImpl;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl implements ResourceDao {
	@Override
	public Menu getRoot() {
		String hql = "from Menu where parent is null";
		return (Menu) this.queryForObject(hql);
	}
}
