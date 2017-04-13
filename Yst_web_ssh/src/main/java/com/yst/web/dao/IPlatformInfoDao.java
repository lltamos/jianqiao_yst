package com.yst.web.dao;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;

import com.yst.web.entity.platforminfo.PlatformInfo;


public interface IPlatformInfoDao extends BaseDao<PlatformInfo>{

	@Query("from PlatformInfo as p")
	public List<PlatformInfo> getPlatforCount();
}
