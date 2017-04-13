package com.alqsoft.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.LocCity;
import com.alqsoft.entity.Store;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicHospitalTypeService;
import com.alqsoft.service.loccity.LocCityService;
import com.alqsoft.service.locprov.LocProvService;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.service.store.StoreService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 分院控制器
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017-3-6 下午8:01:14
 * 
 */
@RequestMapping("store")
@Controller
public class StoreController {

	@Autowired
	private StoreService storeService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private LocCityService locCityService;
	@Autowired
	private LocProvService locProvService;
	@Autowired
	private DicHospitalTypeService dicHospitalService;
	
	/**
	 * 跳转分院列表
	 * @return
	 */
	@RequestMapping("store-list")
	@Permission(SystemRole.ADMIN)
	public String storeList() {
		return "store/store-list";
	}
	
	/**
	 * 分院列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("store-list-data")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Store>> storeListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.storeService.getStoreList(page,length,request);
	}
	
	/**
	 * 跳转添加分院
	 * @return
	 */
	@RequestMapping("store-input")
	@Permission(SystemRole.ADMIN)
	public String storeAdd(Model model,
			@RequestParam(required=false,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(id!=null){
			model.addAttribute("store", this.storeService.get(id));
		}
		model.addAttribute("merchants", merchantService.findAll());
		model.addAttribute("provs", locProvService.findAll());
		model.addAttribute("hospitalType", dicHospitalService.findAll());
		return "store/store-input";
	}
	
	/**
	 * 跳转高德地图
	 * @return
	 */
	@RequestMapping("store-map")
	@Permission(SystemRole.ADMIN)
	public String storeMap(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return "store/store-map";
	}
	
	/**
	 * 获取市区列表
	 * @param model
	 * @param provId
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("store-city")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public List<LocCity> getCity(Model model,
			@RequestParam(value = "provId", required=true) Integer provId,
			HttpServletRequest request,
			HttpSession session) {
		return this.locCityService.getCityByProvId(provId);
	}
	
	/**
	 * 保存分院
	 * @param model
	 * @param store
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("store-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result storeSave(Model model,
			Store store,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return this.storeService.save(store);
	}
	
	/**
	 * 恢复/禁用分院
	 * @param model
	 * @param store
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("store-deleted")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result storeDeleted(Model model,
			Store store,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return this.storeService.deleted(store);
	}
	
	/**
	 * 跳转查看分院
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("store-info")
	@Permission(SystemRole.ADMIN)
	public String storeInfo(Model model,
			@RequestParam(required=true,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		model.addAttribute("store", this.storeService.get(id));
		return "store/store-info";
	}
	
	/**
	 * 
	* @Title: storeCheck 
	* @Description: 添加分院时候检查分院是存在
	* @return Result    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("store-check")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result storeCheck(
			@RequestParam(required=true,value="merchantId")Integer merchantId,
			@RequestParam(required=true,value="name")String name,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		return storeService.storeCheck(merchantId, name);
	}
}
