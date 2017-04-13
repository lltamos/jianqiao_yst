package com.yst.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yst.web.dao.PatientOrderDao;
import com.yst.web.entity.order.PatientOrder;
import com.yst.web.entity.orderrecord.OrderRecord;
import com.yst.web.entity.patient.Patient;
import com.yst.web.model.AppResult;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.User;
import com.yst.web.service.PatientOrderService;
import com.yst.web.service.ShareMoneyService;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.UpLoadUtils;
import com.yst.web.utils.UtilDate;
@Service
@Transactional
public class PatientOrderServiceImpl implements PatientOrderService {
	@Autowired
	private PatientOrderDao patientOrderDao;
	@Autowired
	private ShareMoneyService moneyService;//分润service方法
	@Override
	public List<PatientOrder> selectPatientPage(HttpServletRequest request, HttpServletResponse response) {
		
		return (List<PatientOrder>) this.patientOrderDao.findByPage(PatientOrder.class);
	}
	@Override
	public AppResult addPatientOrder(PatientOrder patientOrder,HttpServletRequest request,
			HttpServletResponse response) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String orderNum=UtilDate.getOrderNum()+UtilDate.getThree();
		Integer patientId=patientOrder.getPatientId();
		Integer productId=patientOrder.getProductId();
		User user=(User) request.getSession().getAttribute("dbUser");
		if(user==null){
			appResult.setError_info("请重新登录");
			return appResult;
		}
		if (CommUtils.isNull(productId)) {
			appResult.setError_info("项目编号不能为空");
		}
		if (CommUtils.isNull(patientId)) {
			appResult.setError_info("患者编号不能为空");
		}
		Product dbProduct=this.patientOrderDao.get(Product.class, productId);
		if(dbProduct==null){
			appResult.setError_info("项目编号错误或不存在");
			return appResult;
		}
		Patient dbPatient=this.patientOrderDao.get(Patient.class, new Long(patientId));
		if(dbPatient==null){
			appResult.setError_info("患者编号错误或不存在");
			return appResult;
		}
		OrderRecord or = new OrderRecord();
		Integer merchantId=dbProduct.getMerchant().getMerchant_id();
		String merchantName=dbProduct.getMerchant().getName();
		patientOrder.setPatientName(dbPatient.getPatientName());
		patientOrder.setProductName(dbProduct.getName());
		patientOrder.setMoney(new Long(dbProduct.getPrice()));
		patientOrder.setMerchantId(merchantId);
		patientOrder.setMerchantName(merchantName);
		patientOrder.setOrderNum(orderNum);
		patientOrder.setType(0);
		patientOrder.setAmountPaid(0L);
		patientOrder.setDividePaid(0);
		patientOrder.setFenrunStatu(0);
		patientOrder.setCreatedTime(new Date());
		this.patientOrderDao.save(patientOrder);
		or.setPatientOrder(patientOrder);
		or.setType(patientOrder.getType());
		or.setDescriber("手动创建订单");
		or.setName(user.getLogin_name());
		or.setCreatedTime(new Date());
		this.patientOrderDao.save(or);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public Map<String, Object> findByID(Long id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		PatientOrder patientOrder =this.patientOrderDao.get(PatientOrder.class, id);
		resultMap.put("patientOrder", patientOrder);
		Patient patient = this.patientOrderDao.get(Patient.class, new Long(patientOrder.getPatientId()));
		resultMap.put("patient", patient);
		Integer productId=patientOrder.getProductId();
		Product dbProduct=this.patientOrderDao.get(Product.class, productId);
		resultMap.put("product", dbProduct);
		List<OrderRecord> OrderRecordList=this.patientOrderDao.selectByColumnValue(OrderRecord.class,"patientOrder.id", id);
		if(OrderRecordList.size()>0){
			resultMap.put("orderRecord", OrderRecordList);
		}
		return resultMap;
	}

	@Override
	public List<PatientOrder> findByOtherColumn(String column, Object value) {
		return this.patientOrderDao.findOtherColumnByPage(PatientOrder.class, column, value);
	}
	@Override
	public AppResult updatePatientOrder(MultipartFile uploadFile,PatientOrder patientOrder,
			HttpServletRequest request, HttpServletResponse response) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Long id=patientOrder.getId();
		Integer type=patientOrder.getType();
		User user=(User) request.getSession().getAttribute("dbUser");
		if(user==null){
			appResult.setError_info("请重新登录");
			return appResult;
		}
		PatientOrder dbPatientOrder=this.patientOrderDao.get(PatientOrder.class, id);
		if(dbPatientOrder==null){
			appResult.setError_info("订单编号错误或不存在");
			return appResult;
		}
		Integer productId=dbPatientOrder.getProductId();
		Product dbProduct=this.patientOrderDao.get(Product.class, productId);
		if(dbProduct==null){
			appResult.setError_info("项目编号错误或不存在");
			return appResult;
		}
		Long dbAmountPaid=dbPatientOrder.getAmountPaid();//已付金额
		Long dbMoney=dbPatientOrder.getMoney();//订单总金额
		Integer dbDividePaid =dbPatientOrder.getDividePaid();//已付期数
		String str="";
		if(type==0){
			str="未支付";
		}else if(type==1){
			str="已签约";
			if(uploadFile!=null){
				Result result=UpLoadUtils.springUploadFile(uploadFile, "patientOrder");
				if(result.getCode()!=1){
					appResult.setError_info(result.getMsg());
					return appResult;
				}
				dbPatientOrder.setContractAddress(result.getContent().toString());
			}
		}else if(type==2){
			Integer divide=dbProduct.getDivide();
			Integer currentDivied=dbDividePaid+1;
			if(currentDivied>divide){
				appResult.setError_info("分期数据错误");
				return appResult;
			}
			Long amountPaid=0L;//付款金额
			if(currentDivied==1){
				amountPaid=new Long(dbProduct.getFee1());
			}else if(currentDivied==2){
				amountPaid=new Long(dbProduct.getFee2());
			}else if(currentDivied==3){
				amountPaid=new Long(dbProduct.getFee3());
			}else if(currentDivied==4){
				amountPaid=new Long(dbProduct.getFee4());
			}else if(currentDivied==5){
				amountPaid=new Long(dbProduct.getFee5());
			}
			str="部分支付：本次付款金额为："+new Double(amountPaid)/100+"元";
			dbPatientOrder.setDividePaid(dbDividePaid+1);
			dbPatientOrder.setAmountPaid(dbAmountPaid+amountPaid);
		}else if(type==3){
			str="全额支付：本次付款金额为："+(new Double(dbMoney-dbAmountPaid)/100)+"元";
			dbPatientOrder.setAmountPaid(dbMoney);
			dbPatientOrder.setDividePaid(dbDividePaid+1);
		}else if(type==4){
			str="阶段治疗";
		}else if(type==5){
			str="最终治疗";
		}else if(type==6){
			str="订单完成";
		}
		dbPatientOrder.setType(type);
		dbPatientOrder.setUpdateTime(new Date());
		this.patientOrderDao.update(dbPatientOrder);
		OrderRecord or = new OrderRecord();
		or.setPatientOrder(patientOrder);
		or.setType(patientOrder.getType());
		or.setDescriber("订单修改为："+str);
		or.setName(user.getLogin_name());
		or.setCreatedTime(new Date());
		this.patientOrderDao.save(or);
		appResult.setError_info("修改成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
}
