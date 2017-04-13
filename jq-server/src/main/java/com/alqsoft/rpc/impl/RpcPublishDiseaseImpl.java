package com.alqsoft.rpc.impl;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.attachment.AttachmentDao;
import com.alqsoft.dao.patient.PatientDiseaseDao;
import com.alqsoft.dao.patientimage.PatientImageDao;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.patient.PatientDisease;
import com.alqsoft.entity.patient.PatientImage;
import com.alqsoft.rpc.RpcPublishDisease;

@Service
@Transactional
public class RpcPublishDiseaseImpl implements RpcPublishDisease{
	
	@Autowired
	private PatientDiseaseDao patientDiseaseDao;
	@Autowired
	private PatientImageDao patientImageDao;
	@Autowired
	private AttachmentDao attachmentDao;
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PatientDisease get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PatientDisease saveAndModify(PatientDisease arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result savePatientDiease(PatientDisease disease) {
		PatientDisease save = patientDiseaseDao.save(disease);
		Result result = new Result();
		if(save!=null){
			String[] imgAddressId = save.getImgAddress();
			if(imgAddressId.length>0){
				for(String image :imgAddressId){
					if(null!=image){
						Attachment address = attachmentDao.findOne(Long.parseLong(image));
						PatientImage patientImage = new PatientImage();
						patientImage.setAddress(address.getAddress());
						patientImage.setName(address.getName());
						patientImage.setPatientDisease(disease);
						patientImageDao.save(patientImage);
					}
				}
				result.setCode(1);
				result.setMsg("添加患者求医记录成功，但圖片添加失敗！");
				result.setContent(save);
				return result;
			}
			result.setCode(1);
			result.setMsg("添加患者求医记录成功！");
			result.setContent(save);
			return result;
		}
		result.setCode(0);
		result.setMsg("添加患者求医记录失败！");
		return result;
	}

	@Override
	public Result deletPatientDiease(PatientDisease disease) {
		if(null!=disease.getId()){
			Long patientDiseaseId = disease.getId();
			/**
			 * TODO
			 * 根据patientDiseaseId删除患者求医记录
			 */
		}
		
		
		return null;
	}
}
