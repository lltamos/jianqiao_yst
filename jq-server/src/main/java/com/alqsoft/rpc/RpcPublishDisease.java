package com.alqsoft.rpc;
import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.patient.PatientDisease;

public interface RpcPublishDisease extends BaseService<PatientDisease>{
	public Result savePatientDiease(PatientDisease disease);
	public Result deletPatientDiease(PatientDisease disease);
}
