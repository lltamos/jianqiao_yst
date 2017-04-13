package com.yst.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.RelativeMedicineRecordDao;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.utils.BaseDaoImpl;
@Repository("relativeMedicineRecordDao")
public class RelativeMedicineRecordDaoImpl extends BaseDaoImpl implements RelativeMedicineRecordDao{

	@Override
	public void deleteRelativeMedicne(
			RelativeMedicineRecord relativeMedicineRecord) {
		this.getSession().delete(relativeMedicineRecord);
	}

}
