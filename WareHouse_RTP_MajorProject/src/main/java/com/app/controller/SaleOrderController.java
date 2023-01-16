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

import com.app.model.SaleOrderDetails;
import com.app.constant.OrderStatus;
import com.app.model.SaleOrder;
import com.app.model.SaleOrder;
import com.app.service.IShipmentTypeService;
import com.app.service.ISaleOrderDetailsService;
import com.app.service.IProductService;
import com.app.service.ISaleOrderService;
import com.app.service.IUserService;
import com.app.util.SaleInvoicePDF;
import com.app.util.SaleInvoicePDF;

@Controller
@RequestMapping("/saleOrder")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService SaleOrderService;
	@Autowired
	private IUserService UserService;  //Integration
	@Autowired
	private IShipmentTypeService ShipmentTypeService;  //Integration
	@Autowired
	private IProductService ProductService;  //Integration
	@Autowired
	private ISaleOrderDetailsService saleOrderDetailsService;
	
	private Integer flag=0;

	//Screen #1 Integration
	private void AddDynamicComponentScreen1(Model model) {
		
		model.addAttribute("Users" , UserService.getUserIdAndUserCode("CUSTOMER"));
		model.addAttribute("ShipmentTypes" , ShipmentTypeService.getShipmentTypeIdAndShipmentMode("Yes"));
	}
   private void AddDynamicComponentScreen2(Model model) {
		
	   model.addAttribute("Products" , ProductService.getProductIdAndProductName());
	}
	
	//Sale Order Screen #1 OPEN
	@GetMapping("/")
	public String showSaleOrder(Model model)
	{
		SaleOrder SaleOrder = new SaleOrder();
		SaleOrder.setStatus("OPEN");
		model.addAttribute("SaleOrder",SaleOrder);
		AddDynamicComponentScreen1(model);
		model.addAttribute("title","Warehouse : Sale Order");
		return "SaleOrderRegistration";
	}
	
	@PostMapping("/save")
	public String saveSaleOrder(@ModelAttribute("SaleOrder") SaleOrder SaleOrder,Model model)
	{
		Integer prodId = SaleOrderService.saveSaleOrder(SaleOrder);
		model.addAttribute("msg","SUCCESSFULLY "+prodId+" Added !");
		return "redirect:";
	}
	
	@GetMapping("/all")
	public String getAllSaleOrder(@RequestParam("page")Integer page, Model model)
	{
		//List<SaleOrder> allSaleOrder = SaleOrderService.getAllSaleOrder();
		Pageable pageable = PageRequest.of(page, 4);
		Page<SaleOrder> findSaleOrderByPagenation = SaleOrderService.findSaleOrderByPagenation(pageable);

		
		model.addAttribute("list",findSaleOrderByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findSaleOrderByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : Sale Order");
		return "SaleOrderData";
	}
	
	//Sale Order Screen #1 CLOSED
	
//	@GetMapping("/edit")
//	public String editSaleOrder(@RequestParam("saleOrderId") Integer saleOrderId,Model model)
//	{
//		SaleOrder oneSaleOrder = SaleOrderService.getOneSaleOrder(saleOrderId);
//		model.addAttribute("SaleOrder",oneSaleOrder);
//		model.addAttribute("Users" , UserService.getUserIdAndUserCode());
//		model.addAttribute("ShipmentTypes" , ShipmentTypeService.getShipmentTypeIdAndShipmentMode());
//		model.addAttribute("title","Warehouse : Sale Order");
//		return "SaleOrderEdit";
//	}
	
//	@PostMapping("/update")
//	public String updateSaleOrder(@ModelAttribute("SaleOrder") SaleOrder SaleOrder,Model model)
//	{
//		SaleOrderService.updateSaleOrder(SaleOrder);
//		model.addAttribute("list",SaleOrderService.getAllSaleOrder());
//		return "SaleOrderData";
//	}
//	
//	@GetMapping("/delete")
//	public String deleteSaleOrder(@RequestParam("saleOrderId") Integer saleOrderId,Model model)
//	{
//		SaleOrderService.deleteSaleOrder(saleOrderId);
//		model.addAttribute("list",SaleOrderService.getAllSaleOrder());
//		return "SaleOrderData";
//	}
	

	
	/**
	 *Methods for Screen #2
	 *Sale Order Details
	 */
	@GetMapping("/addItems")
	public String addItemsToSaleOrderDetails(@RequestParam("saleOrderId")Integer id, Model model) {
		
		//Section #1:
		
		SaleOrder oneSaleOrder = SaleOrderService.getOneSaleOrder(id);
		SaleOrderDetails saleOrderDetails = new SaleOrderDetails();
		model.addAttribute("orderDetails",saleOrderDetails);
		model.addAttribute("saleOrder", oneSaleOrder);
		AddDynamicComponentScreen2(model);
		
		List<SaleOrderDetails> allOrderDetails = saleOrderDetailsService.getAllSaleOrderDetailsByOrderId(id);
		model.addAttribute("list",allOrderDetails);
		
		model.addAttribute("title","Warehouse : Order Details");
		flag=0;
		return "addSOItems";
		
	}
	
	@PostMapping("/saveItems")
	public String saveItemsOrderDetails(@ModelAttribute("orderDetails") SaleOrderDetails saleOrderDetails,Model model)
	{
		//To Automatic Update the OrderDetails if already exist
		Integer soId = saleOrderDetails.getSaleOrder().getId();
		List<SaleOrderDetails> allOrderDetails = saleOrderDetailsService.getAllSaleOrderDetailsByOrderId(soId);
		if(flag==0)
		{
			for(SaleOrderDetails od:allOrderDetails)
			{
				if(od.getProduct().getProdId()==saleOrderDetails.getProduct().getProdId())
				{	
					saleOrderDetails.setId(od.getId());
					saleOrderDetails.setQuantity(saleOrderDetails.getQuantity()+od.getQuantity());
				}
			}
		}
		Integer id = saleOrderDetailsService.saveOrderDetails(saleOrderDetails);
		SaleOrderService.updateStatus(soId, OrderStatus.PICKING.name());
		
		return "redirect:addItems?saleOrderId="+soId;
	}
	
	@GetMapping("/modifyItem") 
	public String modifyOrderDetails(@RequestParam("dtlsId") Integer dtls_id, @RequestParam("soId") Integer so_id,Model model) {
		
		SaleOrderDetails oneOrderDetails = saleOrderDetailsService.getOneSaleOrderDetails(dtls_id);
		model.addAttribute("orderDetails",oneOrderDetails);
		SaleOrder oneSaleOrder = SaleOrderService.getOneSaleOrder(so_id);
		model.addAttribute("saleOrder",oneSaleOrder);
		List<SaleOrderDetails> allOrderDetails = saleOrderDetailsService.getAllSaleOrderDetailsByOrderId(so_id);
		model.addAttribute("list",allOrderDetails);
		
		AddDynamicComponentScreen2(model);
		flag=1;
		return "addSOItems";
	}
	
	@GetMapping("/removeItem")
	public String deleteDoc(@RequestParam("dtlsId") Integer dtls_id, @RequestParam("soId") Integer so_id)
	{
		saleOrderDetailsService.deleteOrderDetails(dtls_id);
		if(saleOrderDetailsService.getCountSaleOrderDetailsByOrderId(so_id)==0)
			SaleOrderService.updateStatus(so_id, OrderStatus.OPEN.name());
		return "redirect:addItems?saleOrderId="+so_id;
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(@RequestParam("soId") Integer so_id) {
		SaleOrderService.updateStatus(so_id, OrderStatus.ORDERED.name());
		return "redirect:addItems?saleOrderId="+so_id;
	}
	
	@GetMapping("/generateInvoice")
	public String generateInvoice(@RequestParam("saleOrderId") Integer so_id) {
		SaleOrderService.updateStatus(so_id, OrderStatus.INVOICED.name());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/printInvoice")
	public ModelAndView printInvoice(@RequestParam("saleOrderId") Integer so_id) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new SaleInvoicePDF());
		SaleOrder oneSaleOrder = SaleOrderService.getOneSaleOrder(so_id);
		List<SaleOrderDetails> allSaleOrderDetails = saleOrderDetailsService.getAllSaleOrderDetailsByOrderId(so_id);
		modelAndView.addObject("list", allSaleOrderDetails);
		modelAndView.addObject("so", oneSaleOrder);
		return modelAndView;
	}
	
	
	
}