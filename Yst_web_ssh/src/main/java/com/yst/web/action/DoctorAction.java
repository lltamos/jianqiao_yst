package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.DicHospitalType;
import com.yst.web.model.DicOffice;
import com.yst.web.model.DicSpec;
import com.yst.web.model.DicTitle;
import com.yst.web.model.Doctor;
import com.yst.web.model.Merchant;
import com.yst.web.service.CustomerService;
import com.yst.web.service.DicHospitalTypeService;
import com.yst.web.service.DicOfficeService;
import com.yst.web.service.DicSpecService;
import com.yst.web.service.DicTitleService;
import com.yst.web.service.DoctorService;
import com.yst.web.service.MerchantService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("doctorAction")
@Scope("prototype")
public class DoctorAction extends BaseAction{
	
	private static final long serialVersionUID = -4472649053551297867L;
	
	@Resource(name = "doctorService")
	private DoctorService doctorService;
	@Resource(name = "customerService")
	private CustomerService customerService;
	@Resource(name = "dicOfficeService")
	private DicOfficeService dicOfficeService;
	@Resource(name = "dicTitleService")
	private DicTitleService dicTitleService;
	@Resource(name = "dicSpecService")
	private DicSpecService dicSpecService;
	@Resource(name = "dicHospitalTypeService")
	private DicHospitalTypeService dicHospitalTypeService;
	@Resource(name = "merchantService")
	private MerchantService merchantService;
	
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Doctor doctor = new Doctor();
	AppResult appResult = new AppResult();
	//经纬度参数
	private String latit;
	private String longit;
	private Integer type;
	//数据字典表参数
	private String dics;
	//排序参数
	private String order_parame;
	
	/**
	 * 医生列表
	 */
	public void listAjax(){
		List<Doctor> doctorList = this.doctorService.selectDoctorListByParame();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(doctorList);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 根据医生参数查询医生列表
	 */
	public void queryDoctorByParame(){
		List<Doctor> doctorList = this.doctorService.queryDoctorByParame(doctor);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(doctorList);
		JSONUtils.sendJSON(appResult);
	}
	
	public String addPage(){
		//初始化下拉信息
		List<Customer> customerList = this.customerService.selectAll();
		List<DicOffice> dicOfficeList = this.dicOfficeService.selectAll();
		List<DicTitle> dicTitleList = this.dicTitleService.selectAll();
		List<DicSpec> dicSpecList = this.dicSpecService.selectAll();
		List<DicHospitalType> dicHospitalTypeList = this.dicHospitalTypeService.selectAll();
		List<Merchant> merchantList = this.merchantService.selectAll();
		ActionContext.getContext().put("customerList", customerList);//用户
		ActionContext.getContext().put("dicOfficeList", dicOfficeList);
		ActionContext.getContext().put("dicTitleList", dicTitleList);
		ActionContext.getContext().put("dicSpecList", dicSpecList);
		ActionContext.getContext().put("dicHospitalTypeList", dicHospitalTypeList);
		ActionContext.getContext().put("merchantList", merchantList);
		return "add_page";
	} 
	
	
	public void addAjax(){
		BeanUtils.getBase64Image(doctor);// 转换image为base64字符串
		appResult = this.doctorService.add(doctor);
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	public String updatePage() {
		//初始化下拉信息
		//List<Customer> customerList = this.customerService.selectAll();
		List<DicOffice> dicOfficeList = this.dicOfficeService.selectAll();
		List<DicTitle> dicTitleList = this.dicTitleService.selectAll();
		List<DicSpec> dicSpecList = this.dicSpecService.selectAll();
		List<Merchant> merchantList = this.merchantService.selectAll();
		List<DicHospitalType> dicHospitalTypeList = this.dicHospitalTypeService.selectAll();
		//ActionContext.getContext().put("customerList", customerList);//用户
		ActionContext.getContext().put("dicOfficeList", dicOfficeList);
		ActionContext.getContext().put("dicTitleList", dicTitleList);
		ActionContext.getContext().put("dicSpecList", dicSpecList);
		ActionContext.getContext().put("dicHospitalTypeList", dicHospitalTypeList);
		ActionContext.getContext().put("merchantList", merchantList);
		doctor = this.doctorService.findById(doctor.getDoctor_id());
		return "update_page";
	}
	
	
	public void updateAjax(){
		if(doctor!=null){
			BeanUtils.getBase64Image(doctor);// 转换image为base64字符串
			appResult = this.doctorService.updateDoctorInfo(doctor);
			JSONUtils.sendJSON(appResult);
		}
	}
	
	
	public String showPage(){
		doctor = this.doctorService.findById(doctor.getDoctor_id());
		doctor.setStr_office(doctor.getDicOffice().getName());
		doctor.setStr_title(doctor.getDicTitle().getName());
		doctor.setStr_spec(doctor.getDicSpec().getName());
		doctor.setStr_dicHospitalType(doctor.getDicHospitalType().getName());
		List<Merchant> merchantList = this.merchantService.selectAll();
		ActionContext.getContext().put("merchantList", merchantList);
		return "show_page";
	}
	
	
	public String approval(){
		doctor = this.doctorService.findById(doctor.getDoctor_id());
		doctor.setStr_office(doctor.getDicOffice().getName());
		doctor.setStr_title(doctor.getDicTitle().getName());
		doctor.setStr_spec(doctor.getDicSpec().getName());
		doctor.setStr_dicHospitalType(doctor.getDicHospitalType().getName());
		return "approval";
	}
	
	
	public void approvalDoctor(){
		if(doctor!=null){
			appResult = this.doctorService.updateApprovalDoctor(doctor);
			JSONUtils.sendJSON(appResult);
		}
		
	}
	
	/**
	 * 跳转医生列表页面
	 * @return
	 */
	public String toDoctorListPage(){
		return "doctor_list";
	}
	/**
	 * 跳转医生审核列表页面
	 * @return
	 */
	public String toDoctorApprovalListPage(){
		return "doctor_approval_list";
	}
	/**
	 * 获取医生分页列表
	 */
	public void findDoctorList(){
		appResult = doctorService.findDoctorListPage();
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * ===============================================================================
	 * =================================APP接口地址部分===================================
	 * ===============================================================================
	 */
	
	/**
	 * 医生申请接口
	 */
	public void doctorApply(){
		appResult = this.doctorService.addDoctorApply(doctor);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 查询医生申请状态接口
	 */
	public void queryDoctorAppealStatus(){
		appResult = this.doctorService.queryDoctorAppealStatusByColumn(doctor);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 获取医生列表接口
	 */
	public void getDoctorList(){
		if(latit!=null&&longit!=null){
		appResult = this.doctorService.getDoctorList(latit ,longit,doctor.getType());
		}else{
			appResult.setError_info("经纬度不能为空");
			appResult.setResult(AppResult.FAILED);
			appResult.setData("");
		}
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 修改医生信息接口
	 */
	public void updateDoctorInfo(){
		appResult = this.doctorService.updateDoctorInfo(doctor);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 添加医生在线时间
	 */
	public void addOnlineTimeInfo(){
		appResult = this.doctorService.addOnlineTimeInfo(doctor);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 获取医生详情接口
	 */
	public void getDoctorDetails(){
		appResult = this.doctorService.getDoctorDetails(doctor.getDoctor_id());
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 获取数据字典接口
	 */
	public void getDic(){
		appResult = this.doctorService.searchDic(dics);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 医生排序接口
	 */
	public void doctorOrderBy(){
		appResult = this.doctorService.findDoctorOrderBySpecList(doctor,order_parame,latit ,longit);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 获取家庭健康顾问接口
	 */
	public void getDoctorByParam(){
		appResult = this.doctorService.getDoctorByParam(doctor);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 获取商家医生列表
	 */
	public void getMerchantDoctorList(){
		appResult = this.doctorService.getMerchantDoctorList(doctor.getMerchant_id());
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 根据环信id获取医生id
	 */
	public void getDoctorIdByHuanXinId(){
		appResult = this.doctorService.getDoctorIdByHuanXinId(doctor.getHuanxin_id());
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	
	
	
	
	public String getOrder_parame() {
		return order_parame;
	}

	public void setOrder_parame(String order_parame) {
		this.order_parame = order_parame;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDics() {
		return dics;
	}

	public void setDics(String dics) {
		this.dics = dics;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getLatit() {
		return latit;
	}

	public void setLatit(String latit) {
		this.latit = latit;
	}

	public String getLongit() {
		return longit;
	}

	public void setLongit(String longit) {
		this.longit = longit;
	}

	@Override
	public Doctor getModel() {
		return doctor;
	}
}
