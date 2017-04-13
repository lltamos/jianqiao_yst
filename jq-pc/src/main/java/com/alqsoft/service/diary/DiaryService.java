package com.alqsoft.service.diary;

import java.util.List;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diarybook.DiaryBook;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:11:45
 * 
 */
public interface DiaryService{

	public List<Diary> getDiaryListByPc(Integer page, Integer rows);

	public List<Diary> getDiaryListByPcAll(Integer page, Integer rows);

	public Diary getDiaryDateils(Long diaryid);

	public int getDiaryListByPcCount(Integer page, Integer rows);

	public Integer getDiaryFavourCountByDairyId(Long diaryid);

	public DiaryBook getDiaryBookById(Long diarybookid);

	public Result saveDiaryComment(Customer customer, Long diaryid, String content, Integer niming);

	public Result saveUpdateDiary(Long diarybookid, String content, String aids, long customerId);

	public List<Diary> toDiaryListByBookId(int i, Integer rows, Long diarybookid);

	public int toDiaryListByBookIdCount(Long diarybookids);

	public List<Diary> getDiaryListByList(int i, Integer rows);

	public DicServiceType getDicServiceTypeById(Long productTypeId);

	public Result saveDiaryFavourCount(Long diaryId, Long customerId);

	public Result saveDiaryBrowNum(Long id);

}
