package com.alqsoft.service.sharemoney;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.shareMoney.ShareMoney;
import com.alqsoft.utils.BootStrapResult;

/**
 * 分润记录业务类
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface ShareMoneyService extends BaseService<ShareMoney> {
	/**
	 * 总院列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<ShareMoney>> getMerchantList(Integer page, Integer length, HttpServletRequest request);
	
	/**
	 * 医生列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<ShareMoney>> getDoctorList(Integer page, Integer length, HttpServletRequest request);
	
	/**
	 * 推荐人列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<ShareMoney>> getRecommenderList(Integer page, Integer length, HttpServletRequest request);
}
