package com.yst.web.service.impl.diaryfavour;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.diaryfavour.DiaryFavourDao;
import com.yst.web.entity.diaryfavour.DiaryFavour;
import com.yst.web.service.diaryfavour.DiaryFavourService;
@Service
@Transactional(readOnly=true)
public class DiaryFavourServiceImpl implements DiaryFavourService{

	@Autowired
	private DiaryFavourDao diaryFavourDao;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public boolean delete(Long arg0) {
		this.diaryFavourDao.delete(arg0);
		return true;
	}

	@Override
	public DiaryFavour get(Long arg0) {
		return diaryFavourDao.findOne(arg0);
	}

	@Override
	public DiaryFavour saveAndModify(DiaryFavour arg0) {
		return diaryFavourDao.save(arg0);
	}

	@Override
	@Transactional(readOnly=false)
	public Result customerDiaryFavour(Integer diaryId, Integer customerId) {
		Session session = (Session) entityManager.getDelegate();
		SQLQuery sqlQuery = session.createSQLQuery("select * from diary_favour where customer_id="+customerId+" and diary_id="+diaryId);
		List<DiaryFavour> diaryFavour = sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		if(diaryFavour.size() > 0){
			return ResultUtils.returnError("该用户已点赞");
		}else{
			//点赞
			DiaryFavour diaryFavour2 = new DiaryFavour();
			diaryFavour2.setDiaryId(diaryId);
			diaryFavour2.setCustomerId(customerId);
			diaryFavourDao.save(diaryFavour2);
			return ResultUtils.returnSuccess("点赞成功！");
		}
	}

}
