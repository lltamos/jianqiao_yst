package com.alqsoft.controller.pc.after.diarybook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.service.diarybook.DiaryBookService;
import com.alqsoft.utils.ServerParam;


/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:13:36
 * 
 */
@RequestMapping("/pc/view/diarybook")
@Controller
public class DiaryBookPcController {

	@Autowired
	public DiaryBookService diaryBookService;
	
	/**
	 * 日记本页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toDiaryListPage.do")
	public String toDiaryListPage(Model model,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			return "view/customer/login";
		}
		model.addAttribute("check", "diary");
		model.addAttribute("modeltype", "2");
		return "view/diarybook/diarybooklist";
	}
	
	/**
	 * 日记本列表
	 * @param model
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getDiaryBookList.do")
	public String getDiaryBookList(
			Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="2")Integer rows,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		long cid = 0;
		if(customer == null){
			return "view/customer/login";
		}else{
			cid = customer.getId();
		}
		Map<String , Object> map = new HashMap<String , Object>();
		page = (page == 0) ? 1 : page;
		map.put("page", (page-1)*rows);
		map.put("rows", rows);
		map.put("cid", cid);
		int diaryBookslistCount = diaryBookService.getBookServicePcListCount(cid);
		List<DiaryBook> diaryBookslist = this.diaryBookService.getBookServicePcList(map);
		double totalPage= Math.ceil(diaryBookslistCount/rows.doubleValue());//向上取整
		int  totalPage1 = (new   Double(totalPage)).intValue();
		model.addAttribute("listDiaryAll", diaryBookslist);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("check", "diary");
		return "view/diarybook/todiarybooklist";
	}
	
	
	@RequestMapping("toSaveDiaryBook.do")
	public String toSaveDiaryBook(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			return "view/customer/login";
		}
		model.addAttribute("check", "diary");
		return "view/diarybook/savediarybook";
	}
	
	@RequestMapping("saveDiaryBook.do")
	@ResponseBody
	public Result saveDiaryBook(
			@RequestParam(value="scorep")Integer scorep,
			@RequestParam(value="product")Long product,
			@RequestParam(value="bookName")String bookName,
			@RequestParam(value="fabulousval")String fabulousval,
			@RequestParam(value="steponval")String steponval,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		long cid = 0;
		if(customer == null){
			result.setCode(3);
			result.setMsg("未登录，请先登录");
			return result;
		}else{
			cid = customer.getId();
		}
		result = diaryBookService.saveDiaryBook(scorep, product, bookName, cid, fabulousval, steponval);
		return result;
	}
	
	/**
	 * 取得用户做个的服务
	 * @param model
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getProductorder.do")
	public String getProductorder(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		long cid = 0;
		if(customer == null){
			return "view/customer/login";
		}else{
			cid = customer.getId();
		}
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("cid", cid);
		List<ProductOrder> orderlist = diaryBookService.getProductorderByCid(map);
		if(orderlist.size() == 0){
			model.addAttribute("orderlist", orderlist);
			model.addAttribute("type", 1);
		}else{
			model.addAttribute("type", 0);
			model.addAttribute("orderlist", orderlist);
		}
		model.addAttribute("check", "diary");
		return "view/diarybook/productorder";
	}
}

