package com.app.controller;

import java.util.List;

import javax.servlet.ServletContext;

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

import com.app.model.PurchaseOrder;
import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.PieChartUtil;
import com.app.util.UomExcel;
import com.app.util.UomPdf;


@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService uomService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private PieChartUtil pieChartUtil;
	
	@GetMapping("/")
	public String showUom(Model model)
	{
		Uom uom=new Uom();
		model.addAttribute("uom" , uom);
		model.addAttribute("title","Warehouse : Unit Of Measurement");
		return "uomRegistration";
	}
	
	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom,Model model)
	{
		Integer id = uomService.saveUom(uom);
		model.addAttribute("msg",id);
		return "uomRegistration";
	}
	
	@GetMapping("/all")
	public String getAllUom(@RequestParam("page")Integer page, Model model)
	{
		//List<Uom> allUom = uomService.getAllUom();
		Pageable pageable = PageRequest.of(page, 4);
		Page<Uom> findAllUomByPagenation = uomService.findAllUomByPagenation(pageable);
		
		model.addAttribute("list",findAllUomByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findAllUomByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : Unit Of Measurement");
		return "uomData";
	}
	
	@GetMapping("/edit")
	public String editUom(@RequestParam("id") Integer id,Model model)
	{
		Uom oneUom = uomService.getOneUom(id);
		model.addAttribute("uom",oneUom);
		model.addAttribute("title","Warehouse : Unit Of Measurement");
		return "uomEdit";
	}
	
	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom,Model model)
	{
		uomService.updateUom(uom);
		return "redirect:all?page=0";
	}
	
	@GetMapping("/delete")
	public String deleteUom(@RequestParam("id") Integer id,Model model)
	{
		uomService.deleteUom(id);
		model.addAttribute("list",uomService.getAllUom());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportExcel()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new UomExcel());
		List<Uom> allUom = uomService.getAllUom();
		mav.addObject("list", allUom);
		return mav;
		
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportPdf()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new UomPdf());
		List<Uom> allUom = uomService.getAllUom();
		mav.addObject("list", allUom);
		return mav;
		
	}
	
	@GetMapping("/pie-chart")
	public String viewPieChart()
	{
		List<Object[]> uomTypeAndCount = uomService.getUomTypeAndCount();
		String realPath = servletContext.getRealPath("/");
		pieChartUtil.generatePiChart(realPath, uomTypeAndCount);
		return "uomChart";
	}
	
	@GetMapping("/bar-chart")
	public String viewBarChart()
	{
		List<Object[]> uomTypeAndCount = uomService.getUomTypeAndCount();
		String realPath = servletContext.getRealPath("/");
		pieChartUtil.generateBarChart(realPath, uomTypeAndCount);
		return "uomBarChart";
	}
	
	
}
