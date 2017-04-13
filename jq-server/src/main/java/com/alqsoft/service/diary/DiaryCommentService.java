package com.alqsoft.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.diarycomment.DiaryComment;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午11:08:32
 * 
 */
public interface DiaryCommentService extends BaseService<DiaryComment>{

	public Result saveDiaryComment(DiaryComment dc);

}
