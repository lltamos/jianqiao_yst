package com.alqsoft.service.impl.doctor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.attachment.AttachmentDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.PatientHistory;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.doctor.DoctorInfo;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.patient.PatientDisease;
import com.alqsoft.rpc.RpcDoctorService;
import com.alqsoft.service.doctor.DoctorService;
@Service
@Transactional(readOnly=true)
public class DoctorServiceImpl implements DoctorService{
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Autowired
	private RpcDoctorService rpcDoctorService;
	
	@Autowired
	private MerchantDao MerchantDao;
	
	@Override
	public List<Map<String, Object>> getDoctorIndx() {
		List<Map<String, Object>> types = doctorDao.getServiceNameByOrder();
		for(Map<String, Object> type : types){
			Object id = type.get("id");
			List<DoctorInfo> doctorList = doctorDao.getDoctorByProducTypeId((Long)id);
			type.put("doctorList", doctorList) ;
		}
		return types;
	}

	/**
	 * 名医高手页面
	 */
	@Override
	public List<DoctorInfo> doctorPage(Map<String, Object> params) {
		List<DoctorInfo> doctorPage = doctorDao.doctorPage(params);
		if(doctorPage.size() == 0){
			return doctorPage;
		}else{
			for(DoctorInfo info : doctorPage){
				Long doctorId = info.getId();
				Integer onlineAsk = doctorDao.countOnlineAsk(doctorId);
				Integer onlineDate = doctorDao.countOnlineDate(doctorId);
				info.setOnlineAsk(onlineAsk);
				info.setOnlineDate(onlineDate);
			}
		}
		return doctorPage;
	}
	
	/**
	 *医生详情页
	 */
		@Override
		public DoctorInfo getDoctorInfoById(Long id) {
			DoctorInfo doctorInfo = doctorDao.getDoctorInfoById(id);
			if(doctorInfo.getId()==id){
				if(null != doctorDao.getDoctorOrderPriceById(id)){
					//设置在线咨询的价格
					doctorInfo.setPrice1(doctorDao.getDoctorOrderPriceById(id));
				}
				//设置在线预约的价格
				Map<String, Object> product = doctorDao.getProductOrderPriceById(id);
				if(null != product){
					Long productId = (Long) product.get("id");
					String productName = (String) product.get("name");
					if(null !=product.get("deposite_price").toString()){
						Long price2 =Long.parseLong(product.get("deposite_price").toString()) ;
						doctorInfo.setPrice2(price2);
					}
					doctorInfo.setProductId(productId);
					doctorInfo.setProductName(productName);
				}
			}
			return doctorInfo;
		}

	/**
	 * 获取医生的图片
	 */
	@Override
	public List<Map<String, Object>> doctorImages(Map<String, Object> params) {
		List<Map<String, Object>> doctorImages = doctorDao.doctorImage(params);
		return doctorImages;
	}
	/**
	 * 获取用户对该医生的日记
	 */
	@Override
	public List<Map<String, Object>> doctorDiary(Map<String, Object> params) {
		List<Map<String, Object>> doctorDiarys = doctorDao.doctorDiary(params);
		return doctorDiarys;
	}

	/**
	 * 查找患者求医的记录
	 */
	@Override
	public List<PatientHistory> findDoctor(Map<String, Object> params) {
		List<PatientHistory> findDoctorList = doctorDao.findDoctor(params);
		if(findDoctorList.size() == 0){
			return findDoctorList;
		}else{
			for (PatientHistory patientDisease : findDoctorList) {
				if(null != patientDisease.getCustomerId()){
					String headImage = doctorDao.findHeaderImage(patientDisease.getCustomerId());
					patientDisease.setHeadImage(headImage);
				}
				long id = patientDisease.getId();
				List<String> diseaseImages = doctorDao.findDiseaseImage(id);
				String[] imagesArray = new String[diseaseImages.size()];
				for(int i = 0; i < diseaseImages.size(); i++){
					imagesArray[i] =  diseaseImages.get(i);
				}
				patientDisease.setImageUrl(imagesArray);
				String name= doctorDao.getPatientProvById(Long.parseLong(patientDisease.getProvId()+""));
				patientDisease.setProvName(name);
				Date time = patientDisease.getCreatedTime();
				if(time!=null){
					DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					patientDisease.setCreateTime(df.format(time));
				}
			}
		}
		return findDoctorList;
	}
/**
 * 根据城市和科室获取医生
 */
	@Override
	public List<DoctorInfo> findDoctorByCityName(Map<String, Object> params) {
		List<DoctorInfo> doctorPage = doctorDao.findDoctorByCityName(params);
		if(doctorPage.size() == 0){
			return doctorPage;
		}else{
			for(DoctorInfo info : doctorPage){
				Long doctorId = info.getId();
				Integer onlineAsk = doctorDao.countOnlineAsk(doctorId);
				Integer onlineDate = doctorDao.countOnlineDate(doctorId);
				info.setOnlineAsk(onlineAsk);
				info.setOnlineDate(onlineDate);
			}
		}
		return doctorPage;
	}

	@Override
	public Result doctorCertification(String aids,Doctors doctor, Customer customer) {
		Result result = new Result();
		String[] attachents = aids.split(",");
		if(attachents.length != 4){
			result.setCode(1);
			result.setMsg("上传影像资料不全");
			return result;
		}
		String handimg = attachents[3];
		Attachment attachment4 = attachmentDao.getOneById(Long.parseLong(handimg));
		doctor.setImage_header(attachment4.getAddress());
		String work1 = attachents[0];
		Attachment attachment1 = attachmentDao.getOneById(Long.parseLong(work1));
		doctor.setImageWork1(attachment1.getAddress());
		String work2 = attachents[1];
		Attachment attachment2 = attachmentDao.getOneById(Long.parseLong(work2));
		doctor.setImageWork2(attachment2.getAddress());
		String work3 = attachents[2];
		Attachment attachment3 = attachmentDao.getOneById(Long.parseLong(work3));
		doctor.setImageWork3(attachment3.getAddress());
		Long merchantId = doctor.getMerchantId();
		if(merchantId == null){
			result.setCode(1);
			result.setMsg("工作单位不存在");
			return result;
		}
		Merchant merchant = MerchantDao.getMerchantById(merchantId);
		if(merchant == null){
			result.setCode(1);
			result.setMsg("工作单位不存在");
			return result;
		}
		Long titleId = doctor.getTitleId();
		if(titleId == null){
			result.setCode(1);
			result.setMsg("工作职称不存在");
			return result;
		}
		Long officeId = doctor.getOfficeId();
		if(officeId == null){
			result.setCode(1);
			result.setMsg("工作科室不存在");
			return result;
		}
		doctor.setApplyStatus(0);
		result = rpcDoctorService.applyDoctor(doctor, customer);
		return result;
	}

	@Override
	public Integer getDoctorTotal() {
		return doctorDao.getDoctorTotal();
	}

	@Override
	public Integer getPatientTotal() {
		return doctorDao.getPatientTotal();
	}

	@Override
	public Integer getDoctorByCitNameTotal() {
		return doctorDao.getDoctorByCitNameTotal();
	}

	@Override
	public PatientDisease findCustomerIdByPatientDisease(Long id) {
		return doctorDao.findCustomerIdByPatientDisease(id);
	}

	@Override
	public Result verificationDoctor(Long cid) {
		Result result = new Result();
		int count = doctorDao.verificationDoctor(cid);
		if(count > 0){
			result.setCode(1);
			result.setMsg("已申请，请勿重复申请");
		}else{
			result.setCode(0);
		}
		return result;
	}

	@Override
	public Doctors getDoctorByCustomerId(Long customerId) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorByCustomerId(customerId);
	}

	@Override
	public Map<String, Object> getDoctorServiceTimeByDoctorId(Long doctorId) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorServiceTimeByDoctorId(doctorId);
	}

	@Override
	public Integer getDoctorOrderPriceById(Long doctorId) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorOrderPriceById(doctorId);
	}

	@Override
	public List<Map<String, Object>> findAllPro() {
		// TODO Auto-generated method stub
		return doctorDao.findAllPro();
	}

	@Override
	public List<Map<String, Object>> findProductType() {
		// TODO Auto-generated method stub
		return doctorDao.findProductType();
	}

	@Override
	public Doctors findByCid(Long customer_id) {
		return doctorDao.findByCid(customer_id); 
	}

	@Override
	public Integer findDiaryByDoctorIdTotal(Long id) {
		// TODO Auto-generated method stub
		return doctorDao.findDiaryByDoctorIdTotal(id);
	}

	@Override
	public Integer getDoctorServiceStats(Integer doctorId) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorServiceStats(doctorId);
	}

	@Override
	public Doctors getDoctorById(Long doctorId) {
		// TODO Auto-generated method stub
		return doctorDao.getDoctorById(doctorId);
	}
	
	

}
