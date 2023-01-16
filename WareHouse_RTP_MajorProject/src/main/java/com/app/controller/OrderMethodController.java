package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.OrderMethod;
import com.app.model.Uom;
import com.app.service.IOrderMethodService;
import com.app.util.OrderMethodExcel;
import com.app.util.OrderMethodPdf;
import com.app.util.UomExcel;
import com.app.util.UomPdf;

@Controller
@RequestMapping("/order")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService OrderMethodService;
	
	@GetMapping("/")
	public String showOrderMethod(Model model)
	{
		OrderMethod orderMethod=new OrderMethod();
		model.addAttribute("OrderMethod" ,orderMethod);
		model.addAttribute("title","Warehouse : OrderMethod");
		return "OrderMethodRegistration";
	}
	
	@PostMapping("/save")
	public String saveOrderMethod(@ModelAttribute("OrderMethod") OrderMethod OrderMethod,Model model)
	{
		Integer id = OrderMethodService.saveOrderMethod(OrderMethod);
		model.addAttribute("msg","Successfully "+id+" Added");
		return "OrderMethodRegistration";
	}
	
	@GetMapping("/all")
	public String getAllOrderMethod(@RequestParam("page")Integer page , Model model)
	{
		//List<OrderMethod> allOrderMethod = OrderMethodService.getAllOrderMethod();
		Pageable pageable = PageRequest.of(page, 3);
		Page<OrderMethod> findAllOrderMethodByPagenation = OrderMethodService.findAllOrderMethodByPagenation(pageable);
		
		model.addAttribute("list",findAllOrderMethodByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findAllOrderMethodByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : OrderMethod");
		return "OrderMethodData";
	}
	
	@GetMapping("/edit")
	public String editOrderMethod(@RequestParam("id") Integer id,Model model)
	{
		OrderMethod oneOrderMethod = OrderMethodService.getOneOrderMethod(id);
		model.addAttribute("OrderMethod",oneOrderMethod);
		model.addAttribute("title","Warehouse : OrderMethod");
		return "OrderMethodEdit";
	}
	
	@PostMapping("/update")
	public String updateOrderMethod(@ModelAttribute("OrderMethod") OrderMethod OrderMethod,Model model)
	{
		OrderMethodService.updateOrderMethod(OrderMethod);
		//model.addAttribute("list",OrderMethodService.getAllOrderMethod());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/delete")
	public String deleteOrderMethod(@RequestParam("id") Integer id,Model model)
	{
		OrderMethodService.deleteOrderMethod(id);
		//model.addAttribute("list",OrderMethodService.getAllOrderMethod());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportExcel()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new OrderMethodExcel());
		List<OrderMethod> allOrderMethod = OrderMethodService.getAllOrderMethod();
		mav.addObject("list", allOrderMethod);
		return mav;
		
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportPdf()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new OrderMethodPdf());
		List<OrderMethod> allOrderMethod = OrderMethodService.getAllOrderMethod();
		mav.addObject("list", allOrderMethod);
		return mav;
		
	}
	
}
