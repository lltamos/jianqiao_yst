package com.yst.web.service.impl.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.PatientImageDao;
import com.yst.web.entity.patient.PatientImage;
import com.yst.web.service.patient.PatientDiseaseAttachmentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年4月27日 下午2:52:30
 * 
 */
@Service
@Transactional(readOnly=true)
public class PatientDiseaseAttachmentServiceImp implements PatientDiseaseAttachmentService{

	private static Logger logger = LoggerFactory.getLogger(PatientDiseaseAttachmentServiceImp.class);
	
	@Autowired
	private PatientImageDao patientImageDao;
	
	@Override
	@Transactional(readOnly=false)
	public boolean delete(Long arg0) {
		try {
			patientImageDao.delete(arg0);
			logger.info("删除附件成功，id=" + arg0);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除附件失败，id=" + arg0);
			return false;
		}
	}

	@Override
	public PatientImage get(Long arg0) {
		return patientImageDao.findOne(arg0);
	}

	@Override
	@Transactional(readOnly=false)
	public PatientImage saveAndModify(PatientImage arg0) {
		PatientImage PatientImage = null;
		try {
			PatientImage = patientImageDao.save(arg0);
			logger.info("附件新增成功,文件名为：" + PatientImage.getName());
			return PatientImage;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("附件新增失败，文件名为：" + PatientImage.getName());
			return null;
		}
	}

}
