package com.alqsoft.service.impl.manage;

import java.util.List;

import org.alqframework.easyui.EasyUtils;
import org.alqframework.easyui.EasyuiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.manage.ManageDao;
import com.alqsoft.entity.manager.Manager;
import com.alqsoft.service.manage.ManageService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年10月13日 下午5:30:15
 * 
 */
@Service
@Transactional(readOnly = true)
public class ManageServiceImpl implements ManageService{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);

	@Autowired
	private ManageDao  manageDao;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try{
			manageDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Manager get(Long arg0) {
		return manageDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Manager saveAndModify(Manager arg0) {
		return manageDao.save(arg0);
	}

	@Override
	public EasyuiResult<List<Manager>> getManageListAll(Integer page, Integer rows) {
		Page<Manager> managePage = manageDao.findAll(new PageRequest(page - 1, rows,
				new Sort(Direction.DESC, new String[] { "createdTime" })));
		return EasyUtils.returnPage(Manager.class,managePage);
	}

	@Override
	public Manager getManageByAccount(String account) {
		return manageDao.getManageByAccount(account);
	}
}
