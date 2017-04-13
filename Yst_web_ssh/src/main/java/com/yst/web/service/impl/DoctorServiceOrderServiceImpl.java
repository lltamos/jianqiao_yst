package com.yst.web.service.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorServiceOrderDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.DicServiceType;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.model.PageModel;
import com.yst.web.service.AlipayService;
import com.yst.web.service.DoctorServiceOrderService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.UtilDate;

@Service("doctorServiceOrderService")
@Transactional
public class DoctorServiceOrderServiceImpl implements DoctorServiceOrderService {
	private static Log logger = LogFactory
			.getLog(DoctorServiceOrderServiceImpl.class);
	@Resource(name = "doctorServiceOrderDao")
	private DoctorServiceOrderDao doctorServiceOrderDao;
	@Resource(name = "alipayService")
	private AlipayService alipayService;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AppResult getDoctorServiceOrder(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if (doctor_id != null && !"".equals(doctor_id)) {
			Doctor doctor = this.doctorServiceOrderDao.findByColumnValue(
					Doctor.class, "doctor_id", doctor_id);
			if (doctor != null) {
				List<DoctorServiceOrder> list = this.doctorServiceOrderDao .selectByColumnValue(DoctorServiceOrder.class, "doctor.doctor_id", doctor_id);
				int length = list.size();
				if (list.size() > 0) {
					for (int i = 0; i < length; i++) {
						DoctorServiceOrder doctorServiceOrder = list.get(i);
						Integer service_type_id = doctorServiceOrder.getDicServiceType().getService_type_id();
						if(0==service_type_id){
							doctorServiceOrder.setStr_service_name("在线咨询");
							//0是小时 1是按年
							doctorServiceOrder.setServcie_timeYear("0");
						}
						if(1==service_type_id){
							doctorServiceOrder.setStr_service_name("在线咨询");
							doctorServiceOrder.setServcie_timeYear("1");
						}
						if(2==service_type_id){
							doctorServiceOrder.setStr_service_name("到家服务");
							doctorServiceOrder.setServcie_timeYear("1");
						}
						if(3==service_type_id){
							doctorServiceOrder.setStr_service_name("到家服务");
							doctorServiceOrder.setServcie_timeYear("0");
						}
						if(4==service_type_id){
							doctorServiceOrder.setStr_service_name("会议服务");
						}
					}
					appResult.setData(list);
					appResult.setResult(AppResult.SUCCESS);
				} else {
					appResult.setError_info("所属医生无服务订单记录");
					appResult.setData("");
				}
			} else {
				appResult.setError_info("医生为空");
				appResult.setData("");
			}
		} else {
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult addDoctorServiceOrder(
			DoctorServiceOrder doctorServiceOrder, HttpServletRequest request) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer customer_id = doctorServiceOrder.getCustomer_id();
		Integer doctor_id = doctorServiceOrder.getDoctor_id();
		Integer service_type_id = doctorServiceOrder.getService_type_id();
		Integer price = doctorServiceOrder.getPrice();
		if (customer_id == null || "".equals(customer_id)) {
			appResult.setError_info("用户id不能为空");
			appResult.setData("");
			return appResult;
		}
		if (doctor_id == null || "".equals(doctor_id)) {
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
			return appResult;
		}
		if (service_type_id == null || "".equals(service_type_id)) {
			appResult.setError_info("服务类型id不能为空");
			appResult.setData("");
			return appResult;
		}
		if (price == null || "".equals(price)) {
			appResult.setError_info("服务价格不能为空");
			appResult.setData("");
			return appResult;
		}
		Customer c = this.doctorServiceOrderDao.findByColumnValue(
				Customer.class, "customer_id", customer_id);
		Doctor d = this.doctorServiceOrderDao.findByColumnValue(Doctor.class,
				"doctor_id", doctor_id);
		DicServiceType dst = this.doctorServiceOrderDao.findByColumnValue(
				DicServiceType.class, "service_type_id", service_type_id);
		if(CommUtils.isNull(dst)){
			appResult.setError_info("服务类型不存在！");
			return appResult;
		}
		if (c != null && d != null) {
			// 设置关联
			// 设置默认值
			doctorServiceOrder.setOrder_date(new Date(System
					.currentTimeMillis()));
			// 是否执行，默认为0，未执行，1为正在执行中，2为执行完毕,3为评价完毕
			doctorServiceOrder.setExecuted(0);
			// 暂时只对到家服务有效，默认为1，表示已经同意，到家服务需要后台批准之后才可以，0为开始状态，1为同意，2为拒绝
			doctorServiceOrder.setApproved(1);
			doctorServiceOrder.setDeleted(0);
			doctorServiceOrder.setCustomer(c);
			doctorServiceOrder.setDoctor(d);
			doctorServiceOrder.setDicServiceType(dst);
			String out_trade_no = UtilDate.getOrderNum() + UtilDate.getThree();// 生成订单号
			doctorServiceOrder.setPay_relative_id(out_trade_no);
			if(service_type_id==2||service_type_id==3 ||service_type_id==4){
				doctorServiceOrder.setPay_status(3);//设置等待状态
			}else if(service_type_id==1){
				Integer buy_time = doctorServiceOrder.getBuy_time();
				if (buy_time == null || "".equals(buy_time) || buy_time==0) {
					appResult.setError_info("购买在线通话服务，时长不能为空");
					appResult.setData("");
					return appResult;
				}
				doctorServiceOrder.setBuy_time(buy_time);
			}
			if(price==0){
				doctorServiceOrder.setPay_status(1);//价格为0的话状态为已支付
			}
			this.doctorServiceOrderDao.save(doctorServiceOrder);
			// 调用支付宝接口
			appResult = this.alipayService.addAlipayNO(appResult, out_trade_no,
					dst.getName(), doctorServiceOrder.getPrice(), 2, request);
		} else {
			appResult.setError_info("医生或用户不存在");
			appResult.setData("");
		}

		return appResult;
	}

	@Override
	public void alipayNotify(String out_trade_no, Date gmt_payment,Integer pay_type) {
		// 修改医生服务订单状态
		DoctorServiceOrder doctorServiceOrder = this.doctorServiceOrderDao
				.findByColumnValue(DoctorServiceOrder.class, "pay_relative_id",
						out_trade_no);
		if (doctorServiceOrder == null) {
			logger.error("订单号：" + out_trade_no + ",订单不存在,时间：" + new Date());
		} else if (doctorServiceOrder.getPay_status() == 1) {
			logger.debug("订单号：" + out_trade_no + ",订单已处理过,时间：" + new Date());
		} else {
			doctorServiceOrder.setPay_status(1);// 设置状态为已支付
			doctorServiceOrder.setPay_type(pay_type);// 类型：支付宝
			doctorServiceOrder.setPay_time(gmt_payment);// 支付时间
			this.doctorServiceOrderDao.saveOrUpdate(doctorServiceOrder);
			logger.debug("订单号：" + out_trade_no + ",医生服务付款完成,时间：" + new Date());
		}
	}

	@Override
	public AppResult findCustomerServiceOrderInfo(Integer doctor_id,
			Integer customer_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if (doctor_id == null || doctor_id.equals("")) {
			appResult.setError_info("医生id不能为空");
			return appResult;
		}
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
			return appResult;
		}
		String hql = "select new map(d as doctor,ds as doctorService) from Doctor as d,DoctorService as ds where d.doctor_id=? AND d.doctor_id = ds.doctor.doctor_id";
		List<Map<String, Object>> dslist = this.doctorServiceOrderDao.query(
				hql, null, doctor_id);
		hql = "select new map(dt.name as name) from DicServiceType as dt where dt.service_type_id in (select dso.dicServiceType.service_type_id from Customer as c, Doctor as d, DoctorService as ds,DoctorServiceOrder as dso WHERE d.doctor_id = ds.doctor.doctor_id AND c.customer_id = dso.customer.customer_id AND d.doctor_id = dso.doctor.doctor_id AND d.doctor_id=? AND c.customer_id =? AND dso.pay_status = 1))";
		List<Map<String, Object>> mapList = this.doctorServiceOrderDao.query(
				hql, null, doctor_id, customer_id);
		if (dslist.size() == 0) {
			appResult.setError_info("医生未开通服务或id错误");
			return appResult;
		} else if (dslist.size() == 1) {
			for (Map<String, Object> map : dslist) {
				Object object = map.get("doctorService");
				for (Map<String, Object> map1 : mapList) {
					String propertyName = map1.get("name").toString();
					if (propertyName != null && !propertyName.equals("")) {
						try {
							Method setter = object.getClass().getMethod(
									"set"
											+ propertyName.substring(0, 1)
													.toUpperCase()
											+ propertyName.substring(1),
									Integer.class);
							// 1: on 开 0: off关
							setter.invoke(object, 1);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							logger.error(e.getMessage());
						}
					}
				}
			}
			appResult.setData(dslist);
			appResult.setError_info("获取服务状态");
			appResult.setResult(AppResult.SUCCESS);
		} else {
			appResult.setError_info("数据异常");
		}
		return appResult;
	}

	@Override
	public AppResult getMedicalOrders(Integer customer_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String hql = "from DoctorServiceOrder as dso where customer_id=? order by dso.order_date desc";
		PageModel pm = PageModelContext.getPageModel();
		List<DoctorServiceOrder> dsoList = this.doctorServiceOrderDao.query(
				hql, pm, customer_id);
		int length = dsoList.size();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				DoctorServiceOrder doctorServiceOrder = dsoList.get(i);
				Integer service_type_id = doctorServiceOrder.getDicServiceType().getService_type_id();
				doctorServiceOrder.getDoctor().setHuanxin_id(doctorServiceOrder.getDoctor().getCustomer().getHuanxin_id());
				doctorServiceOrder.getDoctor().setHuanxin_password(doctorServiceOrder.getDoctor().getCustomer().getHuanxin_password());
				if(0==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询");
					//0是小时 1是按年
					doctorServiceOrder.setServcie_timeYear("0");
				}
				if(1==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询");
					doctorServiceOrder.setServcie_timeYear("1");
				}
				if(2==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务");
					doctorServiceOrder.setServcie_timeYear("1");
				}
				if(3==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务");
					doctorServiceOrder.setServcie_timeYear("0");
				}
				if(4==service_type_id){
					doctorServiceOrder.setStr_service_name("会议服务");
				}
			}
			appResult.setPage_model(pm);
			appResult.setData(dsoList);
			appResult.setResult(AppResult.SUCCESS);
		} else {
			appResult.setError_info("所属用户无医疗订单");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult deleteDoctorServiceOrder(Integer order_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.doctorServiceOrderDao.delete(DoctorServiceOrder.class, order_id);
		return appResult;
		
	}

	@Override
	public AppResult queryList(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		PageModel pm = PageModelContext.getPageModel();
		if(doctor_id!=null){
			String hql = "from DoctorServiceOrder as o where doctor_id=?";
			List<DoctorServiceOrder> dsoList = this.doctorServiceOrderDao.query(hql, pm,DoctorServiceOrder.class, doctor_id);
			int length = dsoList.size();
			for (int i = 0; i < length; i++) {
				DoctorServiceOrder doctorServiceOrder = dsoList.get(i);
				doctorServiceOrder.setStr_customer_name(doctorServiceOrder.getCustomer().getName());
				doctorServiceOrder.setStr_doctor_name(doctorServiceOrder.getDoctor().getName());
				doctorServiceOrder.setStr_service_type_name(doctorServiceOrder.getDicServiceType().getName());
			}
			appResult.setData(dsoList);
		}else{
			String hql ="from DoctorServiceOrder as o";
			List<DoctorServiceOrder> dsoList = this.doctorServiceOrderDao.query(hql, pm,DoctorServiceOrder.class, null);
			int length = dsoList.size();
			for (int i = 0; i < length; i++) {
				DoctorServiceOrder doctorServiceOrder = dsoList.get(i);
				doctorServiceOrder.setStr_customer_name(doctorServiceOrder.getCustomer().getName());
				doctorServiceOrder.setStr_doctor_name(doctorServiceOrder.getDoctor().getName());
				doctorServiceOrder.setStr_service_type_name(doctorServiceOrder.getDicServiceType().getName());
				Integer service_type_id = doctorServiceOrder.getDicServiceType().getService_type_id();
				if(0==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询/小时");
					//0是小时 1是按年
				}
				if(1==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询/按年");
				}
				if(2==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务/按年");
				}
				if(3==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务/小时");
				}
				if(4==service_type_id){
					doctorServiceOrder.setStr_service_name("会议服务");
				}
			}
			appResult.setData(dsoList);
		}
		return appResult;
	}

	@Override
	public DoctorServiceOrder findDoctorServiceOrderInfo(Integer order_id) {
		DoctorServiceOrder doctorServiceOrder = this.doctorServiceOrderDao.findByColumnValue(DoctorServiceOrder.class, "order_id", order_id);
		doctorServiceOrder.setStr_customer_name(doctorServiceOrder.getCustomer().getName());
		doctorServiceOrder.setStr_doctor_name(doctorServiceOrder.getDoctor().getName());
		doctorServiceOrder.setStr_service_type_name(doctorServiceOrder.getDicServiceType().getName());
		doctorServiceOrder.setStr_customer_phone(doctorServiceOrder.getCustomer().getPhone());
		Integer service_type_id = doctorServiceOrder.getDicServiceType().getService_type_id();
		if(0==service_type_id){
			doctorServiceOrder.setStr_service_name("在线咨询/小时");
			//0是小时 1是按年
		}
		if(1==service_type_id){
			doctorServiceOrder.setStr_service_name("在线咨询/按年");
		}
		if(2==service_type_id){
			doctorServiceOrder.setStr_service_name("到家服务/按年");
		}
		if(3==service_type_id){
			doctorServiceOrder.setStr_service_name("到家服务/小时");
		}
		if(4==service_type_id){
			doctorServiceOrder.setStr_service_name("会议服务");
		}
		return doctorServiceOrder;
	}

	@Override
	public AppResult updateDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer order_id = doctorServiceOrder.getOrder_id();
		if(order_id!=null){
			DoctorServiceOrder dbDoctorServiceOrder = this.doctorServiceOrderDao.findByColumnValue(DoctorServiceOrder.class, "order_id", order_id);
			dbDoctorServiceOrder.setPay_status(0);//未支付
			dbDoctorServiceOrder.setPrice(doctorServiceOrder.getPrice());
			this.doctorServiceOrderDao.update(dbDoctorServiceOrder);
			appResult.setData(dbDoctorServiceOrder);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("医生订单服务id不能为空");
			appResult.setData("");
		}
		return appResult;
	}
	
	@Override
	public AppResult queryDoctorHomeApprovalList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		PageModel pm = PageModelContext.getPageModel();
		String hql ="from DoctorServiceOrder dso where dso.deleted=0 and service_type_id in(2,3,4) order by dso.order_date desc";
		List<DoctorServiceOrder> doctorServiceOrdersList = this.doctorServiceOrderDao.query(hql, pm,null);
		int length = doctorServiceOrdersList.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				DoctorServiceOrder doctorServiceOrder = doctorServiceOrdersList.get(i);
				String phone = doctorServiceOrder.getCustomer().getPhone();
				Integer service_type_id = doctorServiceOrder.getDicServiceType().getService_type_id();
				doctorServiceOrder.setStr_customer_phone(phone);
				doctorServiceOrder.setStr_doctor_name(doctorServiceOrder.getDoctor().getName());
				if(0==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询/小时");
					//0是小时 1是按年
				}
				if(1==service_type_id){
					doctorServiceOrder.setStr_service_name("在线咨询/按年");
				}
				if(2==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务/按年");
				}
				if(3==service_type_id){
					doctorServiceOrder.setStr_service_name("到家服务/小时");
				}
				if(4==service_type_id){
					doctorServiceOrder.setStr_service_name("会议服务");
				}
			}
			appResult.setData(doctorServiceOrdersList);
		}else{
			appResult.setError_info("无数据");
		}
		return appResult;
	}

	@Override
	public AppResult updateCheckServiceTime() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String hql ="from DoctorServiceOrder as o where o.dicServiceType.service_type_id=0 and o.pay_status=1 order by o.pay_time asc";
		DoctorServiceOrder dbDoctorServiceOrder =(DoctorServiceOrder) this.doctorServiceOrderDao.queryForObject(hql);
		if(dbDoctorServiceOrder==null){
			appResult.setError_info("没有在线咨询的已支付订单");
			return appResult;
		}
		long pay_time =dbDoctorServiceOrder.getPay_time().getTime();
		long nowTime = new Date().getTime();
		long lastTime = nowTime-pay_time;//已过时间
		long serviceTime =1000*60*60*24*7;//默认在线咨询为：7天
		long remainTime =1000;//默认1000毫秒
		if(lastTime>=serviceTime){//已经过去7天
			dbDoctorServiceOrder.setPay_status(4);//设置订单状态为已过期
			this.doctorServiceOrderDao.saveOrUpdate(dbDoctorServiceOrder);
			appResult.setData(remainTime);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			remainTime=serviceTime-lastTime-1000;//剩余时间 防止延迟减1000毫秒
			if(remainTime<=0){
				remainTime=1000;
			}
			appResult.setData(remainTime);
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public AppResult findConsultOrderPage(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		PageModel pm = PageModelContext.getPageModel();
		String hql = "from DoctorServiceOrder where doctor_id=? ";
		List<DoctorServiceOrder> doctorServiceOrderlist = doctorServiceOrderDao.query(hql, pm, doctor_id);
		appResult.setData(doctorServiceOrderlist);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("获取成功！");
		return appResult;
	}

	
	@Override
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorServiceOrderByPage(Map<String, Object> searchParams, Integer start,
			Integer pageSize,HttpServletRequest request) {
		Doctor doctor = (Doctor) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		Query query = entityManager.createQuery("from DoctorServiceOrder where doctor_id="+doctor.getDoctor_id());
		int count = query.getResultList().size();
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		List<DoctorServiceOrder> resultList = query.getResultList();
		BootStrapResult<List<DoctorServiceOrder>> br = new BootStrapResult<List<DoctorServiceOrder>>();
		br.setResult("获取成功！");
		br.setData(resultList);
		br.setRecordsFiltered(count);
		br.setRecordsTotal(count);
		return br;
	}

	@Override
	public AppResult getDoctorConsultTime(DoctorServiceOrder doctorServiceOrder) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer doctor_id = doctorServiceOrder.getDoctor_id();
		Integer customer_id = doctorServiceOrder.getCustomer_id();
		Integer service_type_id = doctorServiceOrder.getService_type_id();
		if(doctor_id == null){
			appResult.setError_info("医生id不能为空！");
			return appResult;
		}
		if(customer_id == null){
			appResult.setError_info("用户id不能为空！");
			return appResult;
		}
		if(service_type_id == null){
			appResult.setError_info("服务类型id不能为空！");
			return appResult;
		}
		Map<String, Object> dsomap  = this.doctorServiceOrderDao.getDoctorConsultTime(customer_id, doctor_id,service_type_id);
		if(CommUtils.isNull(dsomap)){
			appResult.setError_info("未够购买或超过有效时间！");
			return appResult;
		}else{
			appResult.setData(dsomap);
			appResult.setResult(AppResult.SUCCESS);
			return appResult;
		}
	}
}
