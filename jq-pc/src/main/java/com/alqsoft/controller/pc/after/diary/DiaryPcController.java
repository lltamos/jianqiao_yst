package com.alqsoft.controller.pc.after.diary;

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
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.Product;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.ProductType;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.diarycomment.DiaryComment;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.diary.DiaryService;
import com.alqsoft.service.diarybook.DiaryBookService;
import com.alqsoft.service.diarycomment.DiaryCommentService;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.order.ProductOrderService;
import com.alqsoft.service.product.ProductService;
import com.alqsoft.service.producttype.ProductTypeService;
import com.alqsoft.utils.ServerParam;


/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:13:13
 * 
 */
@Controller
@RequestMapping("/pc/view/diary")
public class DiaryPcController {

	@Autowired
	private DiaryService diaryService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DiaryCommentService diaryCommentService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private DiaryBookService diaryBookService;
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Autowired
	private DoctorService doctorService;
	
	
	/**
	 * 健桥首页日记列表
	 * @param model
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("toDiaryHomePage.do")
	public String toDiaryHomePage(
			Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="2")Integer rows
			){
		List<Diary> listDiary = this.diaryService.getDiaryListByPc(page-1, rows);
		List<Map<String, Object>> productIndx = productService.getProductIndx(4);
		model.addAttribute("listDiary", listDiary);
		model.addAttribute("productIndx", productIndx);
		model.addAttribute("check", "diary");
		return "view/diary/diaryhomepage";
	}
	
	/**
	 * 日记分享页面
	 * @param model
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("toDiaryAllPage.do")
	public String toDiaryAllPage(
			Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="12")Integer rows
			){
		page = (page == 0) ? 1 : page;
		List<Diary> listDiaryAll = this.diaryService.getDiaryListByList((page-1)*rows, rows);
		int DiaryCount = this.diaryService.getDiaryListByPcCount(page, rows);
		double totalPage= Math.ceil(DiaryCount/rows.doubleValue());//向上取整
		int  totalPage1 = (new   Double(totalPage)).intValue();
		model.addAttribute("listDiaryAll", listDiaryAll);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		model.addAttribute("check", "diary");
		return "view/diary/todiarylist";
	}
	
	/**
	 * 去日记分享详情页面
	 * @return
	 */
	@RequestMapping("toDiaryListPage.do")
	public String toDiaryListPage(Model model){
		model.addAttribute("check", "diary");
		return "view/diary/diarylist";
	}
	
	@RequestMapping("getDiaryDateils.do")
	public String getDiaryDateils(
			Model model,
			@RequestParam(value="diaryid")Long diaryid
			){
		Diary diary = this.diaryService.getDiaryDateils(diaryid);
		long cid = diary.getCustomerId();
		long oid = diary.getProductOrderId();
		long bid = diary.getDiaryBookId();
		DiaryBook book = diaryBookService.getBookById(bid);
		Long productId = book.getProductId();
		Customer customer = customerService.getCustomerById(cid);
		//DicServiceType productType = diaryService.getDicServiceTypeById(diary.getProductTypeId());
		int pid = productOrderService.getProductIdById(oid);
		Integer did = Integer.valueOf(diaryid.toString());
		List<DiaryComment> diaryCommentAll = diaryCommentService.findDiaryCommentAllByDiaryId(did);
		Integer favourCount = diaryService.getDiaryFavourCountByDairyId(diaryid);
		String address = productService.getProductImageById(productId);
		Product product = productService.getProductById(productId);
		model.addAttribute("diary", diary);
		model.addAttribute("address", address);
		model.addAttribute("product", product);
		model.addAttribute("book", book);
		model.addAttribute("customer", customer);
		model.addAttribute("diaryCommentAll", diaryCommentAll);
		model.addAttribute("favourCount", favourCount);
		model.addAttribute("pid", pid);
		model.addAttribute("check", "diary");
		return "view/diary/diarydatelis";
	}
	
	/**
	 * 添加评论
	 * @param diaryid
	 * @param content
	 * @param niming
	 * @return
	 */
	@RequestMapping("saveDiaryComment.do")
	@ResponseBody
	public Result saveDiaryComment(
			@RequestParam(value="diaryid")Long diaryid,
			@RequestParam(value="content")String content,
			@RequestParam(value="niming")Integer niming,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Result result = new Result();
		if(customer == null){
			result.setCode(3);
			result.setMsg("请先登录");
			return result;
		}
		if(niming == null){
			niming = 0;
		}
		if(niming == 1){
			customer = null;
		}
		result = diaryService.saveDiaryComment(customer, diaryid, content, niming);
		return result;
	}
	
	/**
	 * 去添加日记
	 * @param model
	 * @param diarybookid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toUpdateDiary.do")
	public String toUpdateDiary(
			Model model,
			@RequestParam(value="diarybookid")Long diarybookid,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		if(customer == null){
			return "view/customer/login";
		}
		DiaryBook diaryBook =this.diaryService.getDiaryBookById(diarybookid);
		model.addAttribute("diaryBook", diaryBook);
		model.addAttribute("check", "diary");
		return "view/diary/savediary";
	}
	
	@RequestMapping("saveDiaryByBookId.do")
	@ResponseBody
	public Result saveDiaryByBookId(
			@RequestParam(value="diarybookid")Long diarybookid,
			@RequestParam(value="content")String content,
			@RequestParam(value="aids")String aids,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		long customerId = 0;
		if(customer == null){
			result.setCode(3);
			result.setMsg("未登录，请先登录");
			return result;
		}else{
			customerId = customer.getId();
		}
		System.out.println(diarybookid+"+"+content+"+"+aids);
		result = diaryService.saveUpdateDiary(diarybookid, content, aids, customerId);
		
		return result;
	}
	
	@RequestMapping("toDiaryListByBookId.do")
	public String toDiaryListByBookId(
			@RequestParam(value="diarybookid")Long diarybookid,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="4")Integer rows,
			Model model
			){
		DiaryBook diaryBook = diaryBookService.getBookById(diarybookid);
		String fabulousval = diaryBook.getFabulousval();
		String steponval = diaryBook.getSteponval();
		String[] fabulousvals = fabulousval.split(",");
		String[] steponvals = steponval.split(",");
		Long customerId = diaryBook.getCustomerId();
		//Long productOrderId = diaryBook.getProductOrderId();
		Long doctorId = diaryBook.getDoctorId();
		Long productId = diaryBook.getProductId();
		Product product = productService.getProductById(productId);
		Customer customer = customerService.getCustomerById(customerId);
		//ProductOrder productOrder = productOrderService.getProductOrderByOid(productOrderId);
		
		Doctors doctors = doctorService.getDoctorById(doctorId);
		model.addAttribute("diaryBook", diaryBook);
		model.addAttribute("customer", customer);
		//model.addAttribute("productOrder", productOrder);
		model.addAttribute("doctors", doctors);
		model.addAttribute("product", product);
		model.addAttribute("fabulousvals", fabulousvals);
		model.addAttribute("steponvals", steponvals);
		return "view/diary/diarylistbybook";
	}
	
	@RequestMapping("getDiaryListByBookId.do")
	public String getDiaryListByBookId(
		@RequestParam(value="diarybookid")Long diarybookid,
		@RequestParam(value="page",defaultValue="1")Integer page,
		@RequestParam(value="rows",defaultValue="3")Integer rows,
		Model model
		){
		DiaryBook diaryBook = diaryBookService.getBookById(diarybookid);
		List<Diary> listDiary = diaryService.toDiaryListByBookId(page-1, rows,diarybookid);
		for (Diary diary : listDiary) {
			Long diaryid = diary.getId();
			Integer did = Integer.valueOf(diaryid.toString());
			List<DiaryComment> diaryCommentAll = diaryCommentService.findDiaryCommentAllByDiaryId(did);
			Integer favourCount = diaryService.getDiaryFavourCountByDairyId(diaryid);
			diary.setCommenterNum(diaryCommentAll.size());
			diary.setSatisfaction(favourCount);
		}
		int diaryCount = diaryService.toDiaryListByBookIdCount(diarybookid);
		double totalPage= Math.ceil(diaryCount/rows.doubleValue());//向上取整
		int  totalPage1 = (new   Double(totalPage)).intValue();
		model.addAttribute("diaryBook", diaryBook);
		model.addAttribute("listDiary", listDiary);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage1);
		return "view/diary/diarylistbydiarybook";
	}
	
	@RequestMapping("saveDiaryFavourCount")
	@ResponseBody
	public Result saveDiaryFavourCount(
			@RequestParam(value="diaryId")Long diaryId,
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		Long customerId = (long) 0;
		if(customer == null){
			result.setCode(3);
			result.setMsg("未登录，请先登录");
			return result;
		}else{
			customerId = customer.getId();
		}
		result = diaryService.saveDiaryFavourCount(diaryId, customerId);
		return result;
	}
	
	@RequestMapping("saveDiaryBrowNum")
	@ResponseBody
	public Result saveDiaryBrowNum(
			@RequestParam(value="id")Long id
			){
		Result result = new Result();
		result = diaryService.saveDiaryBrowNum(id);
		return result;
	}
	
}

