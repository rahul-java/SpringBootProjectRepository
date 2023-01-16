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

import com.app.model.Product;
import com.app.model.PurchaseOrder;
import com.app.service.IOrderMethodService;
import com.app.service.IProductService;
import com.app.service.IUomService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService ProductService;
	@Autowired
	private IUomService uomService;
	@Autowired
	private IOrderMethodService orderMethodService;

	@GetMapping("/")
	public String showProduct(Model model)
	{
		model.addAttribute("Product",new Product());
		model.addAttribute("uoms" , uomService.getUomIdAndUomModel());
		model.addAttribute("orderMethods" , orderMethodService.getOrderMethodIdAndOrderType());
		model.addAttribute("title","Warehouse : Product");
		return "ProductRegistration";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("Product") Product Product,Model model) throws Exception
	{
		Integer prodId = ProductService.saveProduct(Product);
		model.addAttribute("msg","SUCCESSFULLY "+prodId+" Added !");
		model.addAttribute("Product",new Product());
		model.addAttribute("uoms" , uomService.getUomIdAndUomModel());
		model.addAttribute("orderMethods" , orderMethodService.getOrderMethodIdAndOrderType());
		return "ProductRegistration";
	}
	
	@GetMapping("/all")
	public String getAllProduct(@RequestParam("page")Integer page ,Model model)
	{
		//List<Product> allProduct = ProductService.getAllProduct();
		Pageable pageable = PageRequest.of(page, 4);
		Page<Product> findAllProductByPagenation = ProductService.findAllProductByPagenation(pageable);
		
		model.addAttribute("list",findAllProductByPagenation);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",findAllProductByPagenation.getTotalPages());
		model.addAttribute("title","Warehouse : Product");
		return "ProductData";
	}
	
	@GetMapping("/edit")
	public String editProduct(@RequestParam("prodId") Integer prodId,Model model)
	{
		Product oneProduct = ProductService.getOneProduct(prodId);
		model.addAttribute("Product",oneProduct);
		model.addAttribute("uoms" , uomService.getUomIdAndUomModel());
		model.addAttribute("orderMethods" , orderMethodService.getOrderMethodIdAndOrderType());
		model.addAttribute("title","Warehouse : Product");
		return "ProductEdit";
	}
	
	@PostMapping("/update")
	public String updateProduct(@ModelAttribute("Product") Product Product,Model model) throws Exception
	{
		ProductService.updateProduct(Product);
		model.addAttribute("list",ProductService.getAllProduct());
		return "redirect:all?page=0";
	}
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("prodId") Integer prodId,Model model)
	{
		ProductService.deleteProduct(prodId);
		model.addAttribute("list",ProductService.getAllProduct());
		return "redirect:all?page=0";
	}
	

	
}
