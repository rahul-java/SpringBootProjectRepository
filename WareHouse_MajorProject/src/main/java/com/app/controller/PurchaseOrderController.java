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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.PurchaseOrderDetails;
import com.app.constant.OrderStatus;
import com.app.model.PurchaseOrder;
import com.app.service.IShipmentTypeService;
import com.app.service.IPurchaseOrderDetailsService;
import com.app.service.IProductService;
import com.app.service.IPurchaseOrderService;
import com.app.service.IUserService;
import com.app.util.PurchaseInvoicePDF;

@Controller
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService PurchaseOrderService;
	@Autowired
	private IUserService UserService;  //Integration
	@Autowired
	private IShipmentTypeService ShipmentTypeService;  //Integration
	@Autowired
	private IProductService ProductService;  //Integration
	@Autowired
	private IPurchaseOrderDetailsService purchaseOrderDetailsService;
	
	private Integer flag=0;

	//Screen #1 Integration
	private void AddDynamicComponentScreen1(Model model) {
		
		model.addAttribute("Users" , UserService.getUserIdAndUserCode("VENDOR"));
		model.addAttribute("ShipmentTypes" , ShipmentTypeService.getShipmentTypeIdAndShipmentMode("Yes"));
	}
   private void AddDynamicComponentScreen2(Model model) {
		
	   model.addAttribute("Products" , ProductService.getProductIdAndProductName());
	}
	
	//Purchase Order Screen #1 OPEN
	@GetMapping("/")
	public String showPurchaseOrder(Model model)
	{
		PurchaseOrder PurchaseOrder = new PurchaseOrder();
		PurchaseOrder.setStatus("OPEN");
		model.addAttribute("PurchaseOrder",PurchaseOrder);
		AddDynamicComponentScreen1(model);
		model.addAttribute("title","Warehouse : Purchase Order");
		return "PurchaseOrderRegistration";
	}
	
	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute("PurchaseOrder") PurchaseOrder PurchaseOrder,Model model)
	{
		Integer prodId = PurchaseOrderService.savePurchaseOrder(PurchaseOrder);
		model.addAttribute("msg","SUCCESSFULLY "+prodId+" Added !");
		return "redirect:";
	}
	
	@GetMapping("/all")
	public String getAllPurchaseOrder(@RequestParam("page")Integer page, Model model)
	{
		
		
		//List<PurchaseOrder> allPurchaseOrder = PurchaseOrderService.getAllPurchaseOrder();
		
		Pageable pageable = PageRequest.of(page, 4);
		Page<PurchaseOrder> findPurchaseOrderByPagenation = PurchaseOrderService.findPurchaseOrderByPagenation(pageable);
		
		model.addAttribute("list",findPurchaseOrderByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findPurchaseOrderByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : Purchase Order");
		return "PurchaseOrderData";
	}
	
	//Purchase Order Screen #1 CLOSED
	
//	@GetMapping("/edit")
//	public String editPurchaseOrder(@RequestParam("purchaseOrderId") Integer purchaseOrderId,Model model)
//	{
//		PurchaseOrder onePurchaseOrder = PurchaseOrderService.getOnePurchaseOrder(purchaseOrderId);
//		model.addAttribute("PurchaseOrder",onePurchaseOrder);
//		model.addAttribute("Users" , UserService.getUserIdAndUserCode());
//		model.addAttribute("ShipmentTypes" , ShipmentTypeService.getShipmentTypeIdAndShipmentMode());
//		model.addAttribute("title","Warehouse : Purchase Order");
//		return "PurchaseOrderEdit";
//	}
	
//	@PostMapping("/update")
//	public String updatePurchaseOrder(@ModelAttribute("PurchaseOrder") PurchaseOrder PurchaseOrder,Model model)
//	{
//		PurchaseOrderService.updatePurchaseOrder(PurchaseOrder);
//		model.addAttribute("list",PurchaseOrderService.getAllPurchaseOrder());
//		return "PurchaseOrderData";
//	}
//	
//	@GetMapping("/delete")
//	public String deletePurchaseOrder(@RequestParam("purchaseOrderId") Integer purchaseOrderId,Model model)
//	{
//		PurchaseOrderService.deletePurchaseOrder(purchaseOrderId);
//		model.addAttribute("list",PurchaseOrderService.getAllPurchaseOrder());
//		return "PurchaseOrderData";
//	}
	

	
	/**
	 *Methods for Screen #2
	 *Purchase Order Details
	 */
	@GetMapping("/addItems")
	public String addItemsToPurchaseOrderDetails(@RequestParam("purchaseOrderId")Integer id, Model model) {
		
		//Section #1:
		
		PurchaseOrder onePurchaseOrder = PurchaseOrderService.getOnePurchaseOrder(id);
		PurchaseOrderDetails purchaseOrderDetails = new PurchaseOrderDetails();
		model.addAttribute("orderDetails",purchaseOrderDetails);
		model.addAttribute("purchaseOrder", onePurchaseOrder);
		AddDynamicComponentScreen2(model);
		
		List<PurchaseOrderDetails> allOrderDetails = purchaseOrderDetailsService.getAllPurchaseOrderDetailsByOrderId(id);
		model.addAttribute("list",allOrderDetails);
		
		model.addAttribute("title","Warehouse : Order Details");
		flag=0;
		return "addPOItems";
		
	}
	
	@PostMapping("/saveItems")
	public String saveItemsOrderDetails(@ModelAttribute("orderDetails") PurchaseOrderDetails purchaseOrderDetails,Model model)
	{
		//To Automatic Update the OrderDetails if already exist
		Integer poId = purchaseOrderDetails.getPurchaseOrder().getId();
		List<PurchaseOrderDetails> allOrderDetails = purchaseOrderDetailsService.getAllPurchaseOrderDetailsByOrderId(poId);
		if(flag==0)
		{
			for(PurchaseOrderDetails od:allOrderDetails)
			{
				if(od.getProduct().getProdId()==purchaseOrderDetails.getProduct().getProdId())
				{	
					purchaseOrderDetails.setId(od.getId());
					purchaseOrderDetails.setQuantity(purchaseOrderDetails.getQuantity()+od.getQuantity());
				}
			}
		}
		Integer id = purchaseOrderDetailsService.saveOrderDetails(purchaseOrderDetails);
		PurchaseOrderService.updateStatus(poId, OrderStatus.PICKING.name());
		
		return "redirect:addItems?purchaseOrderId="+poId;
	}
	
	@GetMapping("/modifyItem") 
	public String modifyOrderDetails(@RequestParam("dtlsId") Integer dtls_id, @RequestParam("poId") Integer po_id,Model model) {
		
		PurchaseOrderDetails oneOrderDetails = purchaseOrderDetailsService.getOnePurchaseOrderDetails(dtls_id);
		model.addAttribute("orderDetails",oneOrderDetails);
		PurchaseOrder onePurchaseOrder = PurchaseOrderService.getOnePurchaseOrder(po_id);
		model.addAttribute("purchaseOrder",onePurchaseOrder);
		List<PurchaseOrderDetails> allOrderDetails = purchaseOrderDetailsService.getAllPurchaseOrderDetailsByOrderId(po_id);
		model.addAttribute("list",allOrderDetails);
		
		AddDynamicComponentScreen2(model);
		flag=1;
		return "addPOItems";
	}
	
	@GetMapping("/removeItem")
	public String deleteDoc(@RequestParam("dtlsId") Integer dtls_id, @RequestParam("poId") Integer po_id)
	{
		purchaseOrderDetailsService.deleteOrderDetails(dtls_id);
		if(purchaseOrderDetailsService.getCountPurchaseOrderDetailsByOrderId(po_id)==0)
			PurchaseOrderService.updateStatus(po_id, OrderStatus.OPEN.name());
		return "redirect:addItems?purchaseOrderId="+po_id;
	}
	
	@GetMapping("/placeOrder")
	public String placeOrder(@RequestParam("poId") Integer po_id) {
		PurchaseOrderService.updateStatus(po_id, OrderStatus.ORDERED.name());
		return "redirect:addItems?purchaseOrderId="+po_id;
	}
	
	@GetMapping("/generateInvoice")
	public String generateInvoice(@RequestParam("purchaseOrderId") Integer po_id) {
		PurchaseOrderService.updateStatus(po_id, OrderStatus.INVOICED.name());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/printInvoice")
	public ModelAndView printInvoice(@RequestParam("purchaseOrderId") Integer po_id) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new PurchaseInvoicePDF());
		PurchaseOrder onePurchaseOrder = PurchaseOrderService.getOnePurchaseOrder(po_id);
		List<PurchaseOrderDetails> allPurchaseOrderDetails = purchaseOrderDetailsService.getAllPurchaseOrderDetailsByOrderId(po_id);
		modelAndView.addObject("list", allPurchaseOrderDetails);
		modelAndView.addObject("po", onePurchaseOrder);
		return modelAndView;
	}
	
	
	
}