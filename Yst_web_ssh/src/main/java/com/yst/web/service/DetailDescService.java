package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.DetailDesc;

public interface DetailDescService {

	AppResult getObjectInfo(DetailDesc detailDesc);

	AppResult saveOrUpdateInfo(DetailDesc detailDesc);

	AppResult deleteInfo(DetailDesc detailDesc);

	AppResult updateOrder(DetailDesc detailDesc);

	AppResult addShowPage(DetailDesc detailDesc);
}
