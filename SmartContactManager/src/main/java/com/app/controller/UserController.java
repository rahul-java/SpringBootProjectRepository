package com.app.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.app.helper.Message;
import com.app.model.Contact;
import com.app.model.MyOrder;
import com.app.model.User;
import com.app.repository.IContactRepository;
import com.app.repository.IMyOrderRepository;
import com.app.repository.IUserRepository;

import com.razorpay.*;

import netscape.javascript.JSObject;


@Controller
@RequestMapping("/user")
public class UserController {

	
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IContactRepository contactRepository; 
	@Autowired
	private IMyOrderRepository myOrderRepository;
	
	@ModelAttribute
	public void addCommonData(Model model,Principal principal)
	{
		String userName = principal.getName();
		User user = userRepository.getUserByUserName(userName);
		model.addAttribute("user",user);
	}
	
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) 
	{
		model.addAttribute("title","User Dashboard");
		return "normal/user_dashboard";
	}
	
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model)
	{
		model.addAttribute("title","Add Contact");
		model.addAttribute("contact",new Contact());
		return "normal/add_contact_form";
	}
	
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute("contact")Contact contact,
			                     @RequestParam("profileImage")MultipartFile multipartFile ,
			                     Principal principal, HttpSession session)
	{
		try {
		String name = principal.getName();
		User user = this.userRepository.getUserByUserName(name);
		contact.setUser(user);
		
		if(multipartFile.isEmpty())
		{
			System.out.println("image file is empty.....");
			contact.setImage("default.png");
		}
		else 
		{
			//this is for byte[] in db
			//contact.setImage(multipartFile.getBytes());
			
			//this is for storing in folder named: 
			 contact.setImage(multipartFile.getOriginalFilename());
			 File file = new ClassPathResource("static/img/download").getFile();
			 Path path = Paths.get(file.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
			 Files.copy(multipartFile.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image is uploaded");
		}
		contact.setUser(user);
		user.getContacts().add(contact);
		this.userRepository.save(user);
		
		//Success Message
		session.setAttribute("message", new Message("Your Contact is added !! Add More.","success"));
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR : "+e.getMessage());
			//ERROR Message
			session.setAttribute("message", new Message("Something went wrong ! Try again.","danger"));
			
		}
		return "normal/add_contact_form";
	}
	
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page")Integer page ,Model model,Principal principal)
	{
		model.addAttribute("title","Show-Contacts");
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		/*
		List<Contact> contacts = user.getContacts();
		*/
		//this will return whole list of contacts.
		//List<Contact> contactList = this.contactRepository.findContactsByUser(user.getId());
		
		//this will return sub list of list 5-5
		
		Pageable pageable = PageRequest.of(page, 5);
		Page<Contact> contactList = this.contactRepository.findContactsByUser(user.getId(), pageable);
		
		
		model.addAttribute("contacts",contactList);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",contactList.getTotalPages());
		return "/normal/show_contacts";
	}
	/*
	private BufferedImage getImageFile(byte[] byteArray) throws IOException
	{
		ByteArrayInputStream inStreambj = new ByteArrayInputStream(byteArray);
		BufferedImage newImage = ImageIO.read(inStreambj);
		ImageIO.write(newImage, "jpg", new File("outputImage.jpg"));
		return newImage;
	}
	*/
	
	@GetMapping("/{cid}/contact")
	public String showContactDetail(@PathVariable("cid")Integer cid,Model model,Principal principal)
	{
		Optional<Contact> optional = this.contactRepository.findById(cid);
		Contact contact = optional.get();
		//
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		if(user.getId()==contact.getUser().getId())
		{
			model.addAttribute("contact",contact);
			model.addAttribute("title",contact.getName());
		}
		
		return "normal/contact-detail";
	}
	
	@GetMapping("/delete/{cid}")
	public String deleteContact(@PathVariable("cid")Integer cid,Model model,Principal principal,HttpSession session)
	{
		Optional<Contact> optional = this.contactRepository.findById(cid);
		Contact contact = optional.get();
		
		String userName = principal.getName();
		User user = this.userRepository.getUserByUserName(userName);
		
		if(user.getId()==contact.getUser().getId())
		{
			//contact.setUser(null);// unlinking contact from user
			
			//remove photo first
			this.contactRepository.delete(contact);
			model.addAttribute("contact",contact);
			session.setAttribute("message", new Message("Contact Deleted Successfully....","success"));
		}
		else {
			session.setAttribute("message", new Message("Something went wrong ! Try again.","danger"));
		}
		
		return "redirect:/user/show-contacts/0";
	}
	
	@PostMapping("/update-contact/{cid}")
	public String updateContact(@PathVariable("cid")Integer cid ,Model model)
	{
		Contact contact = this.contactRepository.findById(cid).get();
		model.addAttribute("contact",contact);
		model.addAttribute("title","Update-Contact");
		return "/normal/update-form";
	}
	
	//@RequestMapping(value = "/process-update",method = RequestMethod.POST)
	@PostMapping("/process-update")
	public String processUpdate(@ModelAttribute("contact") Contact contact,
			                    @RequestParam("profileImage")MultipartFile multipartFile,
			                    Model model,HttpSession session,Principal principal)
	
	{
		try {
			
			System.out.println(contact.getName());
			System.out.println(contact.getCid());
			//old contact detail
			Contact oldContact = this.contactRepository.findById(contact.getCid()).get();
			
			if(multipartFile.isEmpty())
			{
				
				contact.setImage(oldContact.getImage());
			}
			else 
			{
				//delete old file
				File deleteFile = new ClassPathResource("static/img/download").getFile();
				File file1=new File(deleteFile,oldContact.getImage());
				file1.delete();
				
				//upload new photo 
				 contact.setImage(multipartFile.getOriginalFilename());
				 File file = new ClassPathResource("static/img/download").getFile();
				 Path path = Paths.get(file.getAbsolutePath()+File.separator+multipartFile.getOriginalFilename());
				 Files.copy(multipartFile.getInputStream(),path , StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Image is uploaded");
			}
			
			
			User user = this.userRepository.getUserByUserName(principal.getName());
			contact.setUser(user);
			this.contactRepository.save(contact);
			
			session.setAttribute("message", new Message("Your contact is UPDATED Successfully...","success"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/user/"+contact.getCid()+"/contact";
	}
	
	@GetMapping("/profile")
	public String yourProfile(Model model,Principal principal)
	{
		User user = this.userRepository.getUserByUserName(principal.getName());
		model.addAttribute("user",user);
		model.addAttribute("title","Profile Page");
		return "normal/profile";
	}

	@GetMapping("/settings")
	public String openSettings()
	{
		
		return "normal/settings";
	}
	
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword,
			                     Principal principal, HttpSession session)
	{
		User user = this.userRepository.getUserByUserName(principal.getName());	
		if(this.encoder.matches(oldPassword, user.getPassword()))
		{
			user.setPassword(this.encoder.encode(newPassword));
			this.userRepository.save(user);
			session.setAttribute("message", new Message("Your Password is Changed Successfully...","success"));
			
		}
		else 
		{
			session.setAttribute("message", new Message("Invalid Password ! Try again.","danger"));
			return "redirect:/user/settings";
		}
		
		return "redirect:/user/index";
		
	}
	
	@GetMapping("/payment")
	public String paymentForm()
	{
		return "normal/payment";
	}
	
	
	@PostMapping("/create_order")
	@ResponseBody
	public String createOrder(@RequestBody Map<String , Object> data, Principal principal) throws Exception
	{
		System.out.println("Order function executed");
		System.out.println(data);
		
		int amt = Integer.parseInt(data.get("amount").toString());
		
		RazorpayClient razorpayClient = new RazorpayClient("rzp_live_7m44W5uO4Bbit3", "9eyvMYvfPY5MvqIkEx7JkHgc");
		
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("amount", amt*100);
		 jsonObject.put("currency", "INR");
		 jsonObject.put("receipt", "txn-123456");
		 
		 Order order = razorpayClient.orders.create(jsonObject);
		 System.out.println(order);
		 
		 MyOrder myOrder = new MyOrder();
		 myOrder.setAmount(order.get("amount")+"");
		 myOrder.setOrderId(order.get("id"));
		 myOrder.setStatus("created");
		 myOrder.setPaymentId(null);
		 myOrder.setUser(this.userRepository.getUserByUserName(principal.getName()));
		 myOrder.setReceipt(order.get("receipt"));
		 
		 this.myOrderRepository.save(myOrder);
		 
		
		return order.toString();
	}
	
	@PostMapping("/update_order")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data)
	{
		System.out.println("Data : "+data);
		
		MyOrder myOrder = this.myOrderRepository.findByOrderId(data.get("order_id").toString());
		myOrder.setPaymentId(data.get("payment_id").toString());
		myOrder.setStatus(data.get("status").toString());
		this.myOrderRepository.save(myOrder);
		return ResponseEntity.ok(Map.of("msg","updated"));
	}
	
}

	

