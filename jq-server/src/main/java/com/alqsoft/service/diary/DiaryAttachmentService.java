package com.alqsoft.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.diaryattachment.DiaryAttachment;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午2:53:34
 * 
 */
public interface DiaryAttachmentService extends BaseService<DiaryAttachment>{

	Result saveDiaryById(DiaryAttachment attachment);

}
