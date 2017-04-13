package com.alqsoft.controller.pc.after.product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.product.ProductService;

@Controller
@RequestMapping("/pc/view/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	/**
	 * 疑难杂症的页面
	 * @param model
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/todisease")
	public String toDisease(Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="12")Integer rows){
		Map<String, Object> params = new HashMap<>();
		params.put("page", (page-1)*rows);
		params.put("rows",rows);
		List<Map<String, Object>> listProductAll = productService.getProductByPage(params);
		Integer total = productService.getProductTotal();
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("listProductAll", listProductAll);
		model.addAttribute("check","product");
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		return "/view/disease/diseasetwopage";
	}
	/**
	 * 疑难杂症的导航异步请求
	 */
	@RequestMapping("/category")
	@ResponseBody
	public List<Map<String,Object>> getCategory(@RequestParam(value="departmentName")String departmentName){
		List<Map<String,Object>> result = productService.findServiceByCategory(departmentName);
		return result;
	}
	/**
	 * 首页疑难杂症显示
	 * @param rows
	 * @return
	 */
	@RequestMapping("/productindex")
	@ResponseBody
	public Result getProductIndex(@RequestParam(value = "rows", defaultValue = "6") Integer rows){
		Result result = new Result();
		List<Map<String, Object>> productIndx = productService.getProductIndx(rows);
		if(productIndx!=null){
			result.setContent(productIndx);
			return result;
		}
		return ResultUtils.returnError("没有查询到任何服务");
	}
	/**
	 * 根据科室类型查询疑难杂症
	 */
	@RequestMapping("/search")
	public String searchProduct
			(Model model,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="12")Integer rows,
			@RequestParam(value="serviceName")String serviceName){
		Map<String, Object> params = new HashMap<>();
		params.put("page", (page-1)*rows);
		params.put("rows",rows);
		params.put("serviceName", serviceName);
		List<Map<String, Object>> listProductAll = productService.serviceByType(params);
		Integer total = productService.getProductTotalByServiceName(serviceName);
		double totalPage2 = Math.ceil(total / rows.doubleValue());// 向上取整
		Integer totalPage = (new Double(totalPage2)).intValue();
		model.addAttribute("listProductAll", listProductAll);
		model.addAttribute("currPage", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("check","product");
		return "/view/disease/diseasesearchpage";
	}

}
