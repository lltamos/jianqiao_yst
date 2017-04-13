package com.alqsoft.service.balance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface GZYLReturnService {

	public String validationParam(HttpServletRequest request, HttpServletResponse response, String encReq);

}
