package com.yst.web.service.impl.diary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.entity.satisfactionpraisetread.SatisfactionPraiseTread;
import com.yst.web.service.diary.SatisfactionPraisetreadService;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:11:47
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class SatisfactionPraisetreadServiceImpl implements SatisfactionPraisetreadService{

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SatisfactionPraiseTread get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisfactionPraiseTread saveAndModify(SatisfactionPraiseTread arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
