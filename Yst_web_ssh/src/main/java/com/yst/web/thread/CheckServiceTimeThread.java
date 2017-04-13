package com.yst.web.thread;

import com.yst.web.action.DoctorServiceOrderAction;
import com.yst.web.model.AppResult;

public class CheckServiceTimeThread implements Runnable {
	@SuppressWarnings("unused")
	private DoctorServiceOrderAction dsoAction;
	public CheckServiceTimeThread(DoctorServiceOrderAction dsoa) {
		this.dsoAction=dsoa;
	}
	@Override
	public void run() {
		long checkTime = 10000;//默认
		while(true){
			try {
				Thread.sleep(checkTime);
				AppResult appResult =dsoAction.checkServiceTime();
				if(appResult.getResult().equals(AppResult.SUCCESS)){
					checkTime=(long) appResult.getData();
				}else{
					checkTime=60000;//1分钟后重新检查
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
