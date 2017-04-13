package com.yst.web.service.diaryfavour;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.diaryfavour.DiaryFavour;

public interface DiaryFavourService extends BaseService<DiaryFavour>{

	/**
	 * 日记详情-点赞功能
	 * @param diaryId
	 * @param customerId
	 * @return
	 */
	Result customerDiaryFavour(Integer diaryId, Integer customerId);

}
