package com.yst.web.dao;

import com.yst.web.model.Menu;
import com.yst.web.utils.BaseDao;

public interface ResourceDao extends BaseDao {
	public Menu getRoot();
}
