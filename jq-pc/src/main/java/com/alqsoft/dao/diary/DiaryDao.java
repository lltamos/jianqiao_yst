package com.alqsoft.dao.diary;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.diary.Diary;


  
/**
*
* @author 黄鑫
* @e-mail abc12707058@hotmail.com
* @version v1.0
* @copyright 2010-2015
* @create-time 2017年3月10日 上午11:16:02
* 
*/
@MyBatisRepository
public interface DiaryDao{

	public List<Diary> getHomeDiaryList(Map<String, Object> map);

	public Diary getDiaryDateils(Long diaryid);

	public int getDiaryListByPcCount(Map<String, Object> map);

	public List<Diary> getDiaryListByBookId(Map<String, Object> map);

	public int getDiaryListByBookIdCount(Map<String, Object> map);

	/**
	 * 根据日记本id查找相应的日记数据
	 * @param diaryBookId
	 * @return
	 */
	/*@Query("SELECT s FROM Diary s WHERE s.diaryBook.id=:diaryBookId")
	public List<Diary> findToOne(@Param("diaryBookId")Long diaryBookId);*/

	

}
