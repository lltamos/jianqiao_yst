package com.alqsoft.service.diarycomment;

import java.util.List;

import com.alqsoft.entity.diarycomment.DiaryComment;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午5:06:18
 * 
 */
public interface DiaryCommentService {

	public List<DiaryComment> findDiaryCommentAllByDiaryId(Integer diaryid);

}
