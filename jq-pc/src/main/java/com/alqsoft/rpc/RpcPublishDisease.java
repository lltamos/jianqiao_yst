package com.alqsoft.rpc;

import org.alqframework.result.Result;

import com.alqsoft.entity.patient.PatientDisease;


public interface RpcPublishDisease {
	public Result savePatientDiease(PatientDisease disease);
	public Result deletPatientDiease(PatientDisease disease);
}
