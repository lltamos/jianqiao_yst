package com.yst.web.service.impl.diary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.entity.diarybookpraisetread.DiaryBookPraisetread;
import com.yst.web.service.diary.DiarybookPraisetreadService;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:09:15
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiarybookPraisetreadServiceImpl implements DiarybookPraisetreadService{

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DiaryBookPraisetread get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DiaryBookPraisetread saveAndModify(DiaryBookPraisetread arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
