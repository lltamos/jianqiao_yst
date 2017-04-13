package com.yst.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.DicHospitalTypeDao;
import com.yst.web.dao.DicOfficeDao;
import com.yst.web.dao.DicRelationDao;
import com.yst.web.dao.DicTitleDao;
import com.yst.web.dao.DicTitleDaos;
import com.yst.web.dao.MerchantDao;
import com.yst.web.dao.UserDao;
import com.yst.web.dao.doctor.DoctorCommentDao;
import com.yst.web.dao.doctor.DoctorCommentDaos;
import com.yst.web.dao.doctor.DoctorDao;
import com.yst.web.dao.doctor.DoctorsDao;
import com.yst.web.entity.doctor.Doctors;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.DicHospitalType;
import com.yst.web.model.DicOffice;
import com.yst.web.model.DicRelation;
import com.yst.web.model.DicSpec;
import com.yst.web.model.DicTitle;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorComment;
import com.yst.web.model.Merchant;
import com.yst.web.model.PageModel;
import com.yst.web.model.ProductType;
import com.yst.web.model.Role;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.service.DoctorService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.DistanceUtils;
import com.yst.web.utils.PageModelContext;

@Service("doctorService")
@Transactional
public class DoctorServiceImpl implements DoctorService{
	private static Log logger = LogFactory.getLog(DoctorServiceImpl.class);
	@Resource(name= "doctorDao")
	private DoctorDao doctorDao;
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	@Resource
	private DoctorsDao doctorsDao;
	//数据字典
	@Resource(name = "dicOfficeDao")
	private DicOfficeDao dicOfficeDao;
	@Resource(name = "dicTitleDao")
	private DicTitleDao dicTitleDao;
	@Resource(name = "dicTitleDaos")
	private DicTitleDaos dicTitleDaos;
	@Resource(name = "dicHospitalTypeDao")
	private DicHospitalTypeDao dicHospitalTypeDao;
	@Resource(name = "dicRelationDao")
	private DicRelationDao dicRelationDao;
	@Resource(name = "merchantDao")
	private MerchantDao merchantDao;
	@Resource(name = "doctorCommentDao")
	private DoctorCommentDao doctorCommentDao;
	@Resource(name = "doctorCommentDaos")
	private DoctorCommentDaos doctorCommentDaos;
	@Resource(name = "userDao")
	private UserDao userDao;
	
	@Override
	public Doctor findById(int id) {
		return this.doctorDao.get(Doctor.class, id);
	}
	
	@Override
	public AppResult updateApprovalDoctor(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);//默认为1
		appResult.setResult(AppResult.FAILED);//默认失败
		Integer doctor_id = doctor.getDoctor_id();
		if(doctor_id!=null&&!"".equals(doctor_id)){
			Doctor d = this.doctorDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
			//设置审核状态
			if(doctor.getVerify()!=null&&!"".equals(doctor.getVerify())){
				d.setVerify(doctor.getVerify());
				if(doctor.getVerify()==1){//审核通过
					//关联保存医生角色信息
					User user = new User();
					user.setLogin_name(d.getCustomer().getPhone());
					user.setPassword(d.getCustomer().getPassword());
					user.setCreate_time(new Date());
					user.setUpdate_time(new Date());
					user.setCreate_by("医生后台用户");
					Set<UserRole> roleUsers =new HashSet<UserRole>();
					Role role =this.customerDao.get(Role.class, 7);
					UserRole userRole = new UserRole();
					userRole.setUser(user);
					userRole.setRole(role);
					roleUsers.add(userRole);
					user.setRoleUsers(roleUsers);
					this.customerDao.saveOrUpdate(user);
				}
			}
			d.setUpdate_date(new Date(System.currentTimeMillis()));
			this.doctorDao.update(d);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("请输入医生信息");
		}
		return appResult;
}

	@Override
	public AppResult updateDoctorInfo(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);//默认为1
		appResult.setResult(AppResult.FAILED);//默认失败
		Integer did = doctor.getDoctor_id();
		Doctor d = this.doctorDao.findByColumnValue(Doctor.class, "doctor_id", did);
		if(did!=null&&!"".equals(did)){
			if(d!=null&&!"".equals(d)){
				Integer office_id = doctor.getOffice_id();
				Integer title_id = doctor.getTitle_id();
				Integer spec_id = doctor.getSpec_id();
				Integer customer_id = doctor.getCustomer_id();
				Integer hospital_type_id = doctor.getHospital_type_id();
				Integer merchant_id = doctor.getMerchant_id();
				if(office_id!=null){
					DicOffice dicOffice = new DicOffice();
					dicOffice.setOffice_id(office_id);
					d.setDicOffice(dicOffice);
				}
				if(title_id!=null){
					DicTitle dicTitle = new DicTitle();
					dicTitle.setTitle_id(title_id);
					d.setDicTitle(dicTitle);
				}
				if(spec_id!=null){
					DicSpec dicSpec = new DicSpec();
					dicSpec.setSpec_id(spec_id);
					d.setDicSpec(dicSpec);
				}
				
				if(customer_id!=null){
					Customer customer = new Customer();
					customer.setCustomer_id(customer_id);
					d.setCustomer(customer);
				}
				if(hospital_type_id!=null){
					DicHospitalType dicHospitalType = new DicHospitalType();
					dicHospitalType.setHospital_type_id(hospital_type_id);
					d.setDicHospitalType(dicHospitalType);
				}
				//关联商户
				if(merchant_id!=null){
					Merchant merchant = new Merchant();
					merchant.setMerchant_id(merchant_id);
					d.setMerchant(merchant);
				}
				doctor.setDeleted(0);
				//修改时间
				doctor.setUpdate_date(new Date(System.currentTimeMillis()));
				appResult = BeanUtils.uploadImage(doctor, "doctor");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				BeanUtils.copy(doctor, d);
				d.setVerify(0);
				this.doctorDao.update(d);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("无医生信息");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
		}
		return appResult;
	}
	
	
	@Override
	public AppResult addOnlineTimeInfo(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);//默认为1
		appResult.setResult(AppResult.FAILED);//默认失败
		Integer did = doctor.getDoctor_id();
		if(did!=null&&!"".equals(did)){
			Doctor d = this.doctorDao.findByColumnValue(Doctor.class, "doctor_id", did);
			if(d!=null&&!"".equals(d)){
				d.setOnline_time(doctor.getOnline_time());
				BeanUtils.copy(doctor, d);
				this.doctorDao.update(d);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("无医生信息");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
		}
		return appResult;
	}
	
	@Override
	public AppResult add(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);//默认为1
		appResult.setResult(AppResult.FAILED);//默认失败
		if(doctor.getCustomer_id()==null&&"".equals(doctor.getCustomer_id())){
			appResult.setError_info("所属用户不能为空");
		}else{
			DicOffice dicOffce = new DicOffice();
			dicOffce.setOffice_id(doctor.getOffice_id());
			doctor.setDicOffice(dicOffce);
			DicTitle dicTitle = new DicTitle();
			dicTitle.setTitle_id(doctor.getTitle_id());
			doctor.setDicTitle(dicTitle);
			DicSpec dicSpec = new DicSpec();
			dicSpec.setSpec_id(doctor.getSpec_id());
			doctor.setDicSpec(dicSpec);
			Customer customer  = new Customer();
			customer.setCustomer_id(doctor.getCustomer_id());
			doctor.setCustomer(customer);
			DicHospitalType dicHospitalType = new DicHospitalType();
			dicHospitalType.setHospital_type_id(doctor.getHospital_type_id());
			doctor.setDicHospitalType(dicHospitalType);
			
			appResult = BeanUtils.uploadImage(doctor, "doctor");
			if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
				if (appResult.getResult().equals(AppResult.FAILED)) {
					return appResult;
				}
			}
			doctor.setCreate_date(new Date(System.currentTimeMillis()));
			doctor.setUpdate_date(new Date(System.currentTimeMillis()));
			doctor.setVerify(0);
			this.doctorDao.save(doctor);
			appResult.setError_info("添加成功");
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public AppResult addDoctorApply(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);//默认为1
		appResult.setResult(AppResult.FAILED);//默认失败
		if(StringUtils.isEmptyOrWhitespaceOnly(doctor.getPhone())){
			appResult.setError_info("手机号为空");
		}else{
 			Customer costomer = this.customerDao.findByColumnValue(Customer.class, "phone", doctor.getPhone());
			if(costomer!=null){
				Doctor dbdoctor = this.customerDao.findByColumnValue(Doctor.class, "customer.customer_id", costomer.getCustomer_id());
				if(dbdoctor!=null){
					appResult.setError_info("该用户已申请医生");
				}else{
					Integer merchant_id = doctor.getMerchant_id();
					Integer type = doctor.getType();
					if(type==1){
						appResult = BeanUtils.uploadImage(doctor, "doctor");
						if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
							if (appResult.getResult().equals(AppResult.FAILED)) {
								return appResult;
							}
						}
						doctor.setCreate_date(new Date(System.currentTimeMillis()));//申请时间
						doctor.setUpdate_date(new Date(System.currentTimeMillis()));//修改时间
						doctor.setVerify(0);//设置等待验证
						doctor.setCustomer(costomer);
						if(doctor.getOffice_id()!=null){
							DicOffice d =new DicOffice();
							d.setOffice_id(doctor.getOffice_id());
							doctor.setDicOffice(d);
						}
						if(doctor.getTitle_id()!=null){
							DicTitle dt = new DicTitle();
							dt.setTitle_id(doctor.getTitle_id());
							doctor.setDicTitle(dt);
						}
						if(doctor.getSpec_id()!=null){
							DicSpec ds = new DicSpec();
							ds.setSpec_id(doctor.getSpec_id());
							doctor.setDicSpec(ds);
						}
						if(doctor.getHospital_type_id()!=null){
							DicHospitalType dt = new DicHospitalType();
							dt.setHospital_type_id(doctor.getHospital_type_id());
							doctor.setDicHospitalType(dt);
						}
						appResult.setResult(AppResult.SUCCESS);
						this.doctorDao.save(doctor);
					}else if(type==2){
						if(merchant_id!=null){
							appResult = BeanUtils.uploadImage(doctor, "doctor");
							if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
								if (appResult.getResult().equals(AppResult.FAILED)) {
									return appResult;
								}
							}
							doctor.setCreate_date(new Date(System.currentTimeMillis()));//申请时间
							doctor.setUpdate_date(new Date(System.currentTimeMillis()));//修改时间
							doctor.setVerify(0);//设置等待验证
							doctor.setCustomer(costomer);
							if(doctor.getOffice_id()!=null){
								DicOffice d =new DicOffice();
								d.setOffice_id(doctor.getOffice_id());
								doctor.setDicOffice(d);
							}
							if(doctor.getTitle_id()!=null){
								DicTitle dt = new DicTitle();
								dt.setTitle_id(doctor.getTitle_id());
								doctor.setDicTitle(dt);
							}
							if(doctor.getSpec_id()!=null){
								DicSpec ds = new DicSpec();
								ds.setSpec_id(doctor.getSpec_id());
								doctor.setDicSpec(ds);
							}
							if(doctor.getHospital_type_id()!=null){
								DicHospitalType dt = new DicHospitalType();
								dt.setHospital_type_id(doctor.getHospital_type_id());
								doctor.setDicHospitalType(dt);
							}
							//关联商户
							Merchant merchant = new Merchant();
							merchant.setMerchant_id(merchant_id);
							doctor.setMerchant(merchant);
							appResult.setResult(AppResult.SUCCESS);
							this.doctorDao.save(doctor);
						}else{
							appResult.setError_info("商户id为空");
							appResult.setData("");
						}
					}else{
						appResult.setError_info("医生类型不正确");
						appResult.setData("");
					}
					
				}
			}else{
				appResult.setError_info("用户不存在");
				appResult.setData("");
			}
		}
		return appResult;
	}


	@Override
	public AppResult queryDoctorAppealStatusByColumn(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		HashMap<String, Object> map = new HashMap<String, Object>();
		appResult.setVersion(1);
		if(StringUtils.isEmptyOrWhitespaceOnly(doctor.getPhone())){
			appResult.setError_info("手机号为空");
		}else{
			Customer dbCustomer = this.doctorDao.findByColumnValue(Customer.class, "phone", doctor.getPhone());
			if(dbCustomer!=null){
				Doctor dbdoctor = this.doctorDao.findByColumnValue(Doctor.class, "customer.customer_id", dbCustomer.getCustomer_id());
				if(dbdoctor!=null){
					if(dbdoctor.getVerify()==2){//status:(认证为1，拒绝为2 等待验证为0，未提交为-1)
						appResult.setError_info("申请失败，请联系客服进行重新审核");
					}else{
						appResult.setDoctor_id(dbdoctor.getDoctor_id());
					}
					map.put("status", dbdoctor.getVerify());
					if(dbdoctor.getVerify()== 1){
						map.put("str_status", "1");
					}
					if(dbdoctor.getVerify()== 2){
						map.put("str_status", "2");
					}
					if(dbdoctor.getVerify()== 0){
						map.put("str_status", "0");
					}
					if(dbdoctor.getVerify()== -1){
						map.put("str_status", "-1");
					}
					appResult.setData(map);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					map.put("status", "-1");
					appResult.setData(map);
					appResult.setError_info("不存在该医生");
				}
				
			}else{
				appResult.setError_info("手机号不存在");
			}
		}
		return appResult;
	}


	@Override
	public AppResult searchDic(String dics) {
		AppResult appResult = new AppResult();
		appResult.setResult(appResult.FAILED);//默认失败
		appResult.setVersion(1);
		if(StringUtils.isEmptyOrWhitespaceOnly(dics)){
			appResult.setError_info("dics参数为空");
		}else{
			String[] strarray = dics.split(":");
			Map<String,Object> map = new HashMap<String,Object>();
			for (int i = 0; i < strarray.length; i++) {
				if("office".equals(strarray[i])){
					List<DicOffice> dicOfficeList = this.dicOfficeDao.query(DicOffice.class);
					map.put("office", dicOfficeList);
				}
				if("title".equals(strarray[i])){
					List<DicTitle> dicTitleList = this.dicTitleDao.query(DicTitle.class);
					map.put("title", dicTitleList);
				}
				if("hospital_type".equals(strarray[i])){
					List<DicHospitalType> dicHospitalTypeList= this.dicHospitalTypeDao.query(DicHospitalType.class);
					map.put("hospital_type", dicHospitalTypeList);
				}
				if("relation".equals(strarray[i])){
					List<DicRelation> dicHospitalTypeList= this.dicRelationDao.query(DicRelation.class);
					map.put("relation", dicHospitalTypeList);
				}
				if("spec".equals(strarray[i])){
					List<DicSpec> dicSpecList= this.dicRelationDao.query(DicSpec.class);
					map.put("spec", dicSpecList);
				}
				if("product_type".equals(strarray[i])){
					List<ProductType> productType= this.dicRelationDao.query(ProductType.class);
					map.put("product_type", productType);
				}
			}
			if(map!=null){
				appResult.setData(map);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("未找到数据");
			}
		}
		return appResult;
	}

	@Override
	public List<Doctor> selectDoctorListByParame() {
		String hql = "from  Doctor as o ";
		List<Doctor> doctorList = this.doctorDao.query(hql, PageModelContext.getPageModel(),Doctor.class, null);
		int length = doctorList.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				Doctor doctor = doctorList.get(i);
				if(doctor.getDicOffice()!=null){
					doctor.setStr_office(doctor.getDicOffice().getName());
				}
				if(doctor.getDicSpec()!=null){
					doctor.setStr_spec(doctor.getDicSpec().getName());
				}
				if(doctor.getDicTitle()!=null){
					doctor.setStr_title(doctor.getDicTitle().getName());
				}
				if(doctor.getCustomer().getLast_login_time()!=null){
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
					String lastlogintime = format.format(doctor.getCustomer().getLast_login_time());
					doctor.setStr_last_login_time(lastlogintime);
				}
			}
		}
		return doctorList;
	}

	@Override
	public AppResult getDoctorList(String latitude2,String longitude2,Integer type) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		StringBuffer sb = new StringBuffer();
		sb.append("from Doctor as d where d.verify=1").toString();
		if(type!=null&&!"".equals(type)){
			sb.append(" and d.type="+type).toString();
		}
		PageModel pm = PageModelContext.getPageModel();
		List<Doctor> list = this.doctorDao.query(sb.toString(), pm, null);
		int length = list.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				Doctor doctor = list.get(i);
				if(doctor.getDicOffice()!=null){
					doctor.setStr_office(doctor.getDicOffice().getName());
				}
				if(doctor.getDicSpec()!=null){
					doctor.setStr_spec(doctor.getDicSpec().getName());
				}
				if(doctor.getDicTitle()!=null){
					doctor.setStr_title(doctor.getDicTitle().getName());
				}
				if(doctor.getLatitude()!=null && !"".equals(doctor.getLatitude())&&doctor.getLongitude()!=null&&!"".equals(doctor.getLongitude())){
					double latitude1 = Double.valueOf(doctor.getLatitude());//医生纬度
					double longitude1 = Double.valueOf(doctor.getLongitude());//医生经度
					double lat2 = Double.valueOf(latitude2);
					double long2 = Double.valueOf(longitude2);
					//计算距离
					double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
					doctor.setDistance(distance);
				}
			}
			appResult.setData(list);
			appResult.setPage_model(pm);
			appResult.setError_info("获取列表成功");
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("无医生记录");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult getDoctorDetails(Integer doctor_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(doctor_id!=null || !"".equals(doctor_id)){
			Doctor doctor = this.doctorDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
			if(doctor!=null){
				String huanxinid = doctor.getCustomer().getHuanxin_id();
				doctor.setHuanxin_id(huanxinid);
				doctor.setStr_office(doctor.getDicOffice().getName());
				doctor.setStr_title(doctor.getDicTitle().getName());
				doctor.setStr_spec(doctor.getDicSpec().getName());
				doctor.setStr_dicHospitalType(doctor.getDicHospitalType().getName());
				com.yst.web.model.DoctorService ds = this.doctorDao.findByColumnValue(com.yst.web.model.DoctorService.class, "doctor.doctor_id", doctor_id);
				if(ds!=null){
					doctor.setDoctorService(ds);
				}else{
					appResult.setError_info("所属医生无服务信息");
				}
				doctor.setVeiw_count(doctor.getVeiw_count()+1);
				this.doctorDao.update(doctor);
				
				appResult.setData(doctor);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("无所属id医生");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("医生id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult findDoctorOrderBySpecList(Doctor doctor, String order_parame, String latitude2,String longitude2) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer spec_id = doctor.getSpec_id();
		Integer office_id = doctor.getOffice_id();
		Integer hospital_type_id = doctor.getHospital_type_id();
		Integer doctor_type = doctor.getType();
		PageModel pm = PageModelContext.getPageModel();
		if(doctor_type==null||"".equals(doctor_type)){
			appResult.setError_info("医生类型不能为空");
			appResult.setData("");
			return appResult;
		}else{
			//专长
			if(spec_id!=null&&"desc".equals(order_parame)){
				String hql = "from Doctor as d where dicSpec.spec_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, spec_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
			if(spec_id!=null&&"asc".equals(order_parame)){
				String hql = "from Doctor as d where dicSpec.spec_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, spec_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
			//科室
			if(office_id!=null&&"desc".equals(order_parame)){
				String hql = "from Doctor as d where dicOffice.office_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, office_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
			if(office_id!=null&&"asc".equals(order_parame)){
				String hql = "from Doctor as d where dicOffice.office_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, office_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
			//医生类别
			if(hospital_type_id!=null&&"desc".equals(order_parame)){
				String hql = "from Doctor as d where dicHospitalType.hospital_type_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, hospital_type_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
			if(hospital_type_id!=null&&"asc".equals(order_parame)){
				String hql = "from Doctor as d where dicHospitalType.hospital_type_id=? and d.type=? order by doctor_id "+order_parame;
				List<Doctor> doctorList = this.doctorDao.query(hql, pm, hospital_type_id,doctor_type);
				int length = doctorList.size();
				if(length>0){
					for (int i = 0; i < length; i++) {
						Doctor d = doctorList.get(i);
						d.setStr_office(d.getDicOffice().getName());
						d.setOffice_id(d.getDicOffice().getOffice_id());
						d.setStr_spec(d.getDicSpec().getName());
						d.setSpec_id(d.getDicSpec().getSpec_id());
						d.setStr_title(d.getDicTitle().getName());
						d.setTitle_id(d.getDicTitle().getTitle_id());
						d.setStr_dicHospitalType(d.getDicHospitalType().getName());
						d.setHospital_type_id(d.getDicHospitalType().getHospital_type_id());
						if(d.getLatitude()!=null && !"".equals(d.getLatitude())&&d.getLongitude()!=null&&!"".equals(d.getLongitude())){
							double latitude1 = Double.valueOf(d.getLatitude());//医生纬度
							double longitude1 = Double.valueOf(d.getLongitude());//医生经度
							double lat2 = Double.valueOf(latitude2);
							double long2 = Double.valueOf(longitude2);
							//计算距离
							double distance = DistanceUtils.Distance(latitude1,longitude1,lat2,long2);
							d.setDistance(distance);
						}
					}
					appResult.setData(doctorList);
					appResult.setPage_model(pm);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("无医生信息");
					appResult.setData("");
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult getDoctorByParam(Doctor doctor) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer order_type = doctor.getOrder_type();
		Integer type = doctor.getType();
		Integer office_id = doctor.getOffice_id();
		Double customer_latitude = doctor.getCustomer_latitude();//纬度
		Double customer_longitude = doctor.getCustomer_longitude();//经度
		PageModel pm = PageModelContext.getPageModel();
		List resultlist = new ArrayList();
		if(type==null){
			appResult.setError_info("医生类型为空");
			return appResult;
		}
		if(order_type==null){//默认
			order_type = 0;
		}
		if(order_type==1){//距离最近
			if(customer_latitude==null||customer_longitude==null||"".equals(customer_latitude)||"".equals(customer_longitude)){
				appResult.setError_info("经纬度为空");
				return appResult;
			}
			String hql  ="from Doctor as d where d.type=? and d.verify=1 ";
			hql = getDistanceHql(hql,customer_latitude,customer_longitude);
			List doctorsList = this.doctorDao.query(hql, pm, doctor.getType());
			int length = doctorsList.size();
			List<Doctor> doctorList =new ArrayList<Doctor>();
			for (int i = 0; i < length; i++) {
				Map map =(Map) doctorsList.get(i);
				Doctor d = (Doctor) map.get("doctor");
				Double distance = (Double) map.get("distance");
				d.setDistance(distance);
				d.setStr_office(d.getDicOffice().getName());
				d.setStr_spec(d.getDicSpec().getName());
				d.setStr_title(d.getDicTitle().getName());
				doctorList.add(d);
			}
			if(doctorList.size()>0){
				appResult.setPage_model(pm);
				appResult.setData(doctorList);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				appResult.setError_info("无数据");
				return appResult;
			}
		}else if(order_type==2){//人气最高
			String hql ="from Doctor as d where d.type=? and d.verify=1 order by d.veiw_count desc ";
			hql = getDistanceHqlDefault(hql,customer_latitude,customer_longitude);
			List<Doctor> doctorsList = this.doctorDao.query(hql, pm, doctor.getType());
			if(doctorsList.size()==0){
				appResult.setError_info("无数据");
				return appResult;
			}
			int length = doctorsList.size();
			List<Doctor> doctorList =new ArrayList<Doctor>();
			for (int i = 0; i < length; i++) {
				Map map =(Map) doctorsList.get(i);
				Doctor d = (Doctor) map.get("doctor");
				Double distance = (Double) map.get("distance");
				d.setDistance(distance);
				d.setStr_office(d.getDicOffice().getName());
				d.setStr_spec(d.getDicSpec().getName());
				d.setStr_title(d.getDicTitle().getName());
				doctorList.add(d);
			}
			if(doctorList.size()>0){
				appResult.setPage_model(pm);
				appResult.setData(doctorList);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				appResult.setError_info("无数据");
				return appResult;
			}
		}else if(order_type==3){
			if(office_id==null){
				appResult.setError_info("科室id为空");
				return appResult;
			}
			String hql ="from Doctor as d where d.type=? and d.verify=1 and d.dicOffice.office_id=?";
			hql = getDistanceHqlDefault(hql,customer_latitude,customer_longitude);
			List<Doctor> doctorsList = this.doctorDao.query(hql, pm, doctor.getType(),office_id);
			if(doctorsList.size()==0){
				appResult.setError_info("无数据");
				return appResult;
			}
			int length = doctorsList.size();
			List<Doctor> doctorList =new ArrayList<Doctor>();
			for (int i = 0; i < length; i++) {
				Map map =(Map) doctorsList.get(i);
				Doctor d = (Doctor) map.get("doctor");
				Double distance = (Double) map.get("distance");
				d.setDistance(distance);
				d.setStr_office(d.getDicOffice().getName());
				d.setStr_spec(d.getDicSpec().getName());
				d.setStr_title(d.getDicTitle().getName());
				doctorList.add(d);
			}
			if(doctorList.size()>0){
				appResult.setPage_model(pm);
				appResult.setData(doctorList);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				appResult.setError_info("无数据");
				return appResult;
			}
		}else if(order_type==0||order_type==null){
			String hql ="from Doctor as d where d.type=? and d.verify=1";
			hql = getDistanceHqlDefault(hql,customer_latitude,customer_longitude);
			List<Doctor> doctorsList = this.doctorDao.query(hql, pm, doctor.getType());
			if(doctorsList.size()==0){
				appResult.setError_info("无数据");
				return appResult;
			}
			int length = doctorsList.size();
			List<Doctor> doctorList =new ArrayList<Doctor>();
			for (int i = 0; i < length; i++) {
				Map map =(Map) doctorsList.get(i);
				Doctor d = (Doctor) map.get("doctor");
				Double distance = (Double) map.get("distance");
				d.setDistance(distance);
				d.setStr_office(d.getDicOffice().getName());
				d.setStr_spec(d.getDicSpec().getName());
				d.setStr_title(d.getDicTitle().getName());
				doctorList.add(d);
			}
			if(doctorList.size()>0){
				appResult.setPage_model(pm);
				appResult.setData(doctorList);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				appResult.setError_info("无数据");
				return appResult;
			}
		}else{
			appResult.setError_info("类型异常:order_type不正确");
			appResult.setData("");
		}
		return appResult;
	}
	
	public String getDistanceHql(String hql, Double lat1, Double long1) {
		// "select new map(((ACOS(SIN(("+lat1+" * 3.1415) / 180 ) *SIN((s.latitude * 3.1415) / 180 ) +COS(("+lat1+" * 3.1415) / 180 ) * COS((s.latitude * 3.1415) / 180 ) *COS(("+long1+" * 3.1415) / 180 - (s.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as juli) "
		// ,(ROUND(6378.138*2*ASIN(SQRT(POW(SIN(("
		// + lat1 + "*PI()/180-s.latitude*PI()/180)/2),2)+COS(" + lat1
		// + "*PI()/180)*COS(s.latitude*PI()/180)*POW(SIN((" + long1
		// + "*PI()/180-s.longitude*PI()/180)/2),2)))*1000)) as juli
		String str = "select  new map(((ACOS(SIN(("
				+ lat1
				+ " * 3.1415) / 180 ) *SIN((d.latitude * 3.1415) / 180 ) +COS(("
				+ lat1
				+ " * 3.1415) / 180 ) * COS((d.latitude * 3.1415) / 180 ) *COS(("
				+ long1
				+ " * 3.1415) / 180 - (d.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as distance,d as doctor)  "
				+ hql
				+ " ORDER BY ((ACOS(SIN(("
				+ lat1
				+ " * 3.1415) / 180 ) *SIN((d.latitude * 3.1415) / 180 ) +COS(("
				+ lat1
				+ " * 3.1415) / 180 ) * COS((d.latitude * 3.1415) / 180 ) *COS(("
				+ long1
				+ " * 3.1415) / 180 - (d.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) asc";
		return str;
	}
	
	public String getDistanceHqlDefault (String hql, Double lat1, Double long1) {
		// "select new map(((ACOS(SIN(("+lat1+" * 3.1415) / 180 ) *SIN((s.latitude * 3.1415) / 180 ) +COS(("+lat1+" * 3.1415) / 180 ) * COS((s.latitude * 3.1415) / 180 ) *COS(("+long1+" * 3.1415) / 180 - (s.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as juli) "
		// ,(ROUND(6378.138*2*ASIN(SQRT(POW(SIN(("
		// + lat1 + "*PI()/180-s.latitude*PI()/180)/2),2)+COS(" + lat1
		// + "*PI()/180)*COS(s.latitude*PI()/180)*POW(SIN((" + long1
		// + "*PI()/180-s.longitude*PI()/180)/2),2)))*1000)) as juli
		String str = "select  new map(((ACOS(SIN(("
				+ lat1
				+ " * 3.1415) / 180 ) *SIN((d.latitude * 3.1415) / 180 ) +COS(("
				+ lat1
				+ " * 3.1415) / 180 ) * COS((d.latitude * 3.1415) / 180 ) *COS(("
				+ long1
				+ " * 3.1415) / 180 - (d.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as distance,d as doctor)  "
				+ hql;
		return str;
	}

	@Override
	public AppResult getMerchantDoctorList(Integer merchant_id) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if(merchant_id!=null){
			String hql  ="from Doctor as d where d.merchant.merchant_id=?";
			List<Doctor> doctorList = this.doctorDao.query(hql, null, merchant_id);
			int length = doctorList.size();
			if(length>0){
				for (int i = 0; i < length; i++) {
					Doctor doctor = doctorList.get(i);
					doctor.setStr_office(doctor.getDicOffice().getName());
					doctor.setStr_spec(doctor.getDicSpec().getName());
					doctor.setStr_title(doctor.getDicTitle().getName());
				}
				
				appResult.setData(doctorList);
				appResult.setResult(AppResult.SUCCESS);
				appResult.setError_info("获取列表成功");
			}else{
				appResult.setError_info("所属商家无医生");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("商家id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public AppResult getDoctorIdByHuanXinId(String haunxin_id) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if(haunxin_id!=null&&!"".equals(haunxin_id)){
			String hql ="from Customer as c where huanxin_id=?";
			Customer customer = (Customer) this.doctorDao.queryForObject(hql, haunxin_id);
			if(customer!=null){
				appResult.setData(customer);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属的用户不存在");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("环信id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

	@Override
	public List<Doctor> queryDoctorByParame(Doctor doctor) {
		PageModel pm = PageModelContext.getPageModel();
		String name = doctor.getName();
		List<Doctor> doctorList;
		if(doctor==null||"".equals(doctor)){
			String hql ="from Doctor ds d ";
			doctorList = this.doctorDao.query(hql.toString(), pm, null);
		}else{
			StringBuffer hql = new StringBuffer("from Doctor as d where ");
			if(name!=null&&!"".equals(name)){
				hql.append("d.name like ?");
			}
			doctorList = this.doctorDao.query(hql.toString(), pm, "%"+name+"%");
		}
		return doctorList;
	}

	@Override
	public Result findDoctorAll() {
		Map<String, Object> mapAttachment = new HashMap<String, Object>();
		mapAttachment.put("EQ_verify", 1);
		Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
		Specification<Doctors> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),Doctors.class);
		List<Doctors> doctorList = doctorsDao.findAll(attachmentSpec);
		if(doctorList.isEmpty()){
			return ResultUtils.returnError("无数据");
		}
		for (Doctors doctors : doctorList) {
			Integer title_id = doctors.getTitle_id();
			DicTitle dicTitle = this.dicTitleDaos.getTitleNameById(title_id);
			doctors.setTitleName(dicTitle.getName());
		}
		if(doctorList.size() <= 12){
			return ResultUtils.returnSuccess("获取成功",doctorList);
		}
		List<Doctors> listDoctors = new ArrayList<Doctors>();
		if(doctorList.size() > 12){
			for (int i = 0; i < 12; i++) {
				Doctors doctors = doctorList.get(i);
				listDoctors.add(doctors);
			}
			return ResultUtils.returnSuccess("获取成功", listDoctors);
		}
		return null;
	}

	@Override
	public Result getDoctorListAll(Integer page, Integer rows) {
		Map<String, Object> mapAttachment = new HashMap<String, Object>();
		mapAttachment.put("EQ_verify", 1);
		Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
		Specification<Doctors> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),Doctors.class);
		Page<Doctors> doctorsPages = doctorsDao.findAll(attachmentSpec, new PageRequest(page,rows));
		List<Doctors> doctorList = doctorsPages.getContent();
		if(doctorList.isEmpty()){
			return ResultUtils.returnError("无数据");
		}
		for (Doctors doctors : doctorList) {
			Integer title_id = doctors.getTitle_id();
			DicTitle dicTitle = this.dicTitleDaos.getTitleNameById(title_id);
			doctors.setTitleName(dicTitle.getName());
		}
		return ResultUtils.returnSuccess("获取成功", doctorsPages);
		
	}

	@Override
	public Result getOneDoctorDetails(Integer doctor_id) {
		if(doctor_id == null){
			return ResultUtils.returnError("医生id为空");
		}
		Doctors doctor = doctorsDao.getDoctorById(doctor_id);
		if(CommUtils.isNull(doctor)){
			return ResultUtils.returnError("该医生不存在");
		}
		Integer title_id = doctor.getTitle_id();
		DicTitle dicTitle = this.dicTitleDaos.getTitleNameById(title_id);
		if(CommUtils.isNull(dicTitle)){
			doctor.setTitleName("");
		}else{
			doctor.setTitleName(dicTitle.getName());
		}
		return ResultUtils.returnSuccess("获取成功",doctor);
	}

	@Override
	public Result getDoctorComment(Integer doctor_id, Integer page, Integer rows) {
		if(doctor_id == null){
			return ResultUtils.returnError("医生id为空");
		}
		Doctor doctor = doctorDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
		if(CommUtils.isNull(doctor)){
			return ResultUtils.returnError("该医生不存在");
		}
		Map<String, Object> mapAttachment = new HashMap<String, Object>();
		mapAttachment.put("EQ_doctorId", doctor_id);
		Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
		Specification<DoctorComment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DoctorComment.class);
		Page<DoctorComment> doctorCommentPages = doctorCommentDaos.findAll(attachmentSpec, new PageRequest(page,rows));
		List<DoctorComment> listDoctorComment = doctorCommentPages.getContent();
		if(listDoctorComment.isEmpty()){
			return ResultUtils.returnError("该医生暂无评价");
		}
		for (DoctorComment doctorComment : listDoctorComment) {
			Integer customerId = doctorComment.getCustomer_id();
			Customer dbCustomer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
			if(!CommUtils.isNull(dbCustomer)){
			doctorComment.setAge(dbCustomer.getAge());
			doctorComment.setPhone(dbCustomer.getPhone());
			doctorComment.setSex(dbCustomer.getSex());
			doctorComment.setcImage(dbCustomer.getImage());
			}else{
				doctorComment.setAge(null);
				doctorComment.setPhone(null);
				doctorComment.setSex(null);
				doctorComment.setcImage(null);
			}
		}
		return ResultUtils.returnSuccess("获取成功", doctorCommentPages);
	}

	@Override
	public Result saveDoctorComment(Integer doctor_id, Integer customer_id, Integer service_star, String customer_comment) {
		DoctorComment doctorComment = new DoctorComment();
		if(doctor_id == null){
			return ResultUtils.returnError("医生id不能为空");
		}
		Doctor doctor = this.doctorDao.findByColumnValue(Doctor.class, "doctor_id", doctor_id);
		if(CommUtils.isNull(doctor)){
			return ResultUtils.returnError("医生不存在");
		}
		if(customer_id == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer dbCustomer = customerDao.findByColumnValue(Customer.class, "customer_id", customer_id);
		if(CommUtils.isNull(dbCustomer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(service_star == null){
			return ResultUtils.returnError("评价不能为空");
		}
		if(customer_comment == null){
			return ResultUtils.returnError("评论内容不能为空");
		}
		doctorComment.setDoctorId(doctor_id);
		doctorComment.setCustomer_id(customer_id);
		doctorComment.setService_star(service_star);
		doctorComment.setCustomer_comment(customer_comment);
		doctorCommentDao.save(doctorComment);
		return ResultUtils.returnSuccess("评论添加成功");
	}

	@SuppressWarnings("unused")
	@Override
	public Result getDic(String dics) {
		if(StringUtils.isEmptyOrWhitespaceOnly(dics)){
			return ResultUtils.returnError("dics参数为空");
		}
		String[] strarray = dics.split(":");
		Map<String,Object> map = new HashMap<String,Object>();
		for (int i = 0; i < strarray.length; i++) {
			if("office".equals(strarray[i])){
				List<DicOffice> dicOfficeList = this.dicOfficeDao.query(DicOffice.class);
				map.put("office", dicOfficeList);
			}
			if("title".equals(strarray[i])){
				List<DicTitle> dicTitleList = this.dicTitleDao.query(DicTitle.class);
				map.put("title", dicTitleList);
			}
			if("hospital_type".equals(strarray[i])){
				List<DicHospitalType> dicHospitalTypeList= this.dicHospitalTypeDao.query(DicHospitalType.class);
				map.put("hospital_type", dicHospitalTypeList);
			}
			if("relation".equals(strarray[i])){
				List<DicRelation> dicHospitalTypeList= this.dicRelationDao.query(DicRelation.class);
				map.put("relation", dicHospitalTypeList);
			}
			if("spec".equals(strarray[i])){
				List<DicSpec> dicSpecList= this.dicRelationDao.query(DicSpec.class);
				map.put("spec", dicSpecList);
			}
			if("product_type".equals(strarray[i])){
				List<ProductType> productType= this.dicRelationDao.query(ProductType.class);
				map.put("product_type", productType);
			}
		}
		if(map!=null){
			return ResultUtils.returnSuccess("获取成功",map);
		}else{
			return ResultUtils.returnError("未找到数据");
		}
	}

	@Override
	public AppResult findDoctorListPage() {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		PageModel pm = PageModelContext.getPageModel();
		String hql = "from Doctor where verify = 1";
		List<Doctor> doctorList = this.doctorDao.query(hql, pm, null);
		appResult.setData(doctorList);
		appResult.setResult(AppResult.FAILED);
		appResult.setError_info("获取成功！");
		return appResult;
	}

	/*@Override
	public Result getDoctorListByProductTypeId(Integer productTypeId, Integer page, Integer rows) {
		if(productTypeId == null){
			return ResultUtils.returnError("分类id不能为空");
		}
		Page<Doctors> doctorsPages = doctorsDao.findDoctorByProductTypeId(productTypeId, new PageRequest(page,rows));
		List<Doctors> doctorsList = doctorsPages.getContent();
		if(doctorsList.isEmpty()){
			return ResultUtils.returnError("无该分类下的医生");
		}
		return ResultUtils.returnSuccess("获取成功", doctorsPages);
	}*/

}
