package com.alqsoft.service.diarybook;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;

import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:17:28
 * 
 */
public interface DiaryBookService{

	public List<DiaryBook> getBookServicePcList(Map<String, Object> map);

	public int getBookServicePcListCount(Long cid);

	public List<ProductOrder> getProductorderByCid(Map<String, Object> map);

	/*public int getProductorderByCidCount(long cid);*/

	public Result saveDiaryBook(Integer scorep, Long product, String bookName, long cid, String fabulousval, String steponval);

	public DiaryBook getBookById(Long diarybookid);

}
