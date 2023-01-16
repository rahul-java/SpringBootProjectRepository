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
import com.app.model.ShipmentType;
import com.app.model.Uom;
import com.app.service.IShipmentTypeService;
import com.app.util.ShipmentTypeExcel;
import com.app.util.ShipmentTypePdf;
import com.app.util.UomExcel;
import com.app.util.UomPdf;

@Controller
@RequestMapping("/shipment")
public class ShipmentTypeController {

	@Autowired
	private IShipmentTypeService ShipmentTypeService;
	
	@GetMapping("/")
	public String showShipmentType(Model model)
	{
		ShipmentType shipmentType = new ShipmentType();
		model.addAttribute("ShipmentType",shipmentType);
		model.addAttribute("title","Warehouse : ShipmentType");
		return "ShipmentTypeRegistration";
	}
	
	@PostMapping("/save")
	public String saveShipmentType(@ModelAttribute("ShipmentType") ShipmentType ShipmentType,Model model)
	{
		Integer id = ShipmentTypeService.saveShipmentType(ShipmentType);
		model.addAttribute("msg","SUCCESSFULLY "+id+" Added !");
		return "ShipmentTypeRegistration";
	}
	
	@GetMapping("/all")
	public String getAllShipmentType(@RequestParam("page")Integer page,Model model)
	{
		//List<ShipmentType> allShipmentType = ShipmentTypeService.getAllShipmentType();
		
		Pageable pageable = PageRequest.of(page, 3);
		Page<ShipmentType> findAllShipmentTypeByPagenation = ShipmentTypeService.findAllShipmentTypeByPagenation(pageable);
		
		model.addAttribute("list",findAllShipmentTypeByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findAllShipmentTypeByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : ShipmentType");
		return "ShipmentTypeData";
	}
	
	@GetMapping("/edit")
	public String editShipmentType(@RequestParam("id") Integer id,Model model)
	{
		ShipmentType oneShipmentType = ShipmentTypeService.getOneShipmentType(id);
		model.addAttribute("ShipmentType",oneShipmentType);
		model.addAttribute("title","Warehouse : ShipmentType");
		return "ShipmentTypeEdit";
	}
	
	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute("ShipmentType") ShipmentType ShipmentType,Model model)
	{
		ShipmentTypeService.updateShipmentType(ShipmentType);
		//model.addAttribute("list",ShipmentTypeService.getAllShipmentType());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/delete")
	public String deleteShipmentType(@RequestParam("id") Integer id,Model model)
	{
		ShipmentTypeService.deleteShipmentType(id);
		//model.addAttribute("list",ShipmentTypeService.getAllShipmentType());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/excel")
	public ModelAndView exportExcel()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new ShipmentTypeExcel());
		List<ShipmentType> allShipmentType = ShipmentTypeService.getAllShipmentType();
		mav.addObject("list", allShipmentType);
		return mav;
		
	}
	
	@GetMapping("/pdf")
	public ModelAndView exportPdf()
	{
		ModelAndView mav=new ModelAndView();
		mav.setView(new ShipmentTypePdf());
		List<ShipmentType> allShipmentType = ShipmentTypeService.getAllShipmentType();
		mav.addObject("list", allShipmentType);
		return mav;
		
	}
}
