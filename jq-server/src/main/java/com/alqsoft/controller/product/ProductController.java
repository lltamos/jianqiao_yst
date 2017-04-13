package com.alqsoft.controller.product;

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

import com.alqsoft.entity.Product;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicOfficeService;
import com.alqsoft.service.dic.DicServiceTypeService;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.service.product.ProductService;
import com.alqsoft.service.productdoctor.ProductDoctorService;
import com.alqsoft.service.productimage.ProductImageService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 服务包控制器
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017-3-6 下午8:01:14
 * 
 */
@RequestMapping("product")
@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private DicOfficeService officeService;
	@Autowired
	private DicServiceTypeService dicServiceTypeService;
	@Autowired
	private ProductDoctorService productDoctorService;
	@Autowired
	private ProductImageService productImageService;
	/**
	 * 跳转服务包列表
	 * @return
	 */
	@RequestMapping("product-list")
	@Permission(SystemRole.ADMIN)
	public String productList() {
		return "product/product-list";
	}
	
	/**
	 * 服务包列表分页
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("product-list-data")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Product>> productListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.productService.getProductList(page,length,request);
	}
	
	/**
	 * 跳转服务包新增或者修改页面的视图
	 * @return
	 */
	@RequestMapping("product-input")
	@Permission(SystemRole.ADMIN)
	public String productAddPage(Model model,
			Product product,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(product.getId()!=null){
			Long productId=product.getId();
			model.addAttribute("product", this.productService.get(productId));
			model.addAttribute("doctors", this.productDoctorService.getDoctorByProductId(productId));
			model.addAttribute("images", this.productImageService.getImageByProductId(productId));
		}
		model.addAttribute("merchants", merchantService.findAll());
		model.addAttribute("offices", officeService.findAll());
		model.addAttribute("productTypes", dicServiceTypeService.findAll());
		return "product/product-input";
	}
	
	/**
	 * 新增或者修改服务包
	 * @param model
	 * @param product
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("product-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result productSave(Model model,
			Product product,
			@RequestParam(value="doctorId",required=false)Long[] doctorId,
			@RequestParam(value="aids",required=false)String aids,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return this.productService.save(product,aids,doctorId);
	}
	/**
	 * 恢复/禁用服务包
	 * @param model
	 * @param product
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("product-deleted")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result productDeleted(Model model,
			Product product,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return this.productService.deleted(product);
	}
	
	/**
	 * 查看服务包
	 * @param model
	 * @param id
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("product-info")
	@Permission(SystemRole.ADMIN)
	public String productInfo(Model model,
			@RequestParam(required=true,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		model.addAttribute("product", this.productService.get(id));
		model.addAttribute("doctors", this.productDoctorService.getDoctorByProductId(id));
		model.addAttribute("images", this.productImageService.getImageByProductId(id));
		model.addAttribute("merchants", merchantService.findAll());
		model.addAttribute("offices", officeService.findAll());
		model.addAttribute("productTypes", dicServiceTypeService.findAll());
		return "product/product-info";
	}
	
	/**
	 * 跳转ckeditor
	 * @return
	 */
	@RequestMapping("product-ckeditor")
	@Permission(SystemRole.ADMIN)
	public String productCkeditor(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		return "product/product-ckeditor";
	}
}
