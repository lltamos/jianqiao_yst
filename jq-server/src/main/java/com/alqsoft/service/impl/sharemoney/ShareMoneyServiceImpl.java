package com.alqsoft.service.impl.sharemoney;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.sharemoney.ShareMoneyDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.shareMoney.ShareMoney;
import com.alqsoft.service.sharemoney.ShareMoneyService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.SystemRole;

/**
 * 分润记录业务实体类
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午10:53:50
 * 
 */
@Service
@Transactional(readOnly = true)
public class ShareMoneyServiceImpl implements ShareMoneyService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ShareMoneyServiceImpl.class);

	@Autowired
	private ShareMoneyDao shareMoneyDao;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			shareMoneyDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ShareMoney get(Long arg0) {
		return shareMoneyDao.findOne(arg0);
	}

	@Override
	@Transactional
	public ShareMoney saveAndModify(ShareMoney arg0) {
		return shareMoneyDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<ShareMoney>> getMerchantList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Merchant dbMerchant=(Merchant) request.getSession().getAttribute(SystemRole.HOSPITAL.getName());
		searchParams.put("EQ_customerId", dbMerchant.getCustomerId());
		searchParams.put("EQ_shareType", 0);
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<ShareMoney> specification = DynamicSpecifications.bySearchFilter(filter.values(), ShareMoney.class);
		Page<ShareMoney> accountPage = shareMoneyDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(ShareMoney.class, accountPage);
	}
	
	@Override
	public BootStrapResult<List<ShareMoney>> getDoctorList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Doctors dbDoctor=(Doctors) request.getSession().getAttribute(SystemRole.DOCTOR.getName());
		searchParams.put("EQ_customerId", dbDoctor.getCustomerId());
		searchParams.put("EQ_shareType", 1);
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<ShareMoney> specification = DynamicSpecifications.bySearchFilter(filter.values(), ShareMoney.class);
		Page<ShareMoney> accountPage = shareMoneyDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(ShareMoney.class, accountPage);
	}
	
	@Override
	public BootStrapResult<List<ShareMoney>> getRecommenderList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Customer dbCustomer=(Customer) request.getSession().getAttribute(SystemRole.RECOMMENDER.getName());
		searchParams.put("EQ_customerId", dbCustomer.getId());
		searchParams.put("GT_shareType", 1);
		searchParams.put("LT_shareType", 4);
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<ShareMoney> specification = DynamicSpecifications.bySearchFilter(filter.values(), ShareMoney.class);
		Page<ShareMoney> accountPage = shareMoneyDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(ShareMoney.class, accountPage);
	}
}
