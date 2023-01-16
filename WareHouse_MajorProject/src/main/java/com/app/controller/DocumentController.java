package com.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Document;
import com.app.model.OrderMethod;
import com.app.service.IDocumentService;

@Controller
@RequestMapping("/doc")
public class DocumentController {
	
	@Autowired
	private IDocumentService documentService;
	
	@GetMapping("/")
	public String documentUpload(Model model)
	{
		Document document=new Document();
		model.addAttribute("document",document);
		model.addAttribute("title","Warehouse : Document");
		return "document";
	}
	
	@GetMapping("/all")
	public String showDocument(@RequestParam("page")Integer page,Model model)
	{
		List<Object[]> documentIdAndName = documentService.getDocumentIdAndName();
		model.addAttribute("list",documentIdAndName);
		return "document";
	}
	
	@PostMapping("/save")
	public String uploadDocument(@RequestParam("id") Integer fid,@RequestParam("docName")MultipartFile multipartFile) throws IOException
	{
		if(fid!=null)
		{
			Document document=new Document();
			document.setId(fid);
			document.setDocName(multipartFile.getOriginalFilename());
			document.setFileData(multipartFile.getBytes());
			documentService.saveDocument(document);
		}
		return "redirect:all";
	}
	
	@GetMapping("/download")
	public String downloadDocFile(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException
	{
		Optional<Document> documentOptional = documentService.getDocumentById(id);
		if(documentOptional.isPresent())
		{
			Document document = documentOptional.get();
			response.addHeader("Content-Disposition", "attachment; filename="+document.getDocName());
			FileCopyUtils.copy(document.getFileData(), response.getOutputStream());
		}
		
		return "redirect:all";
		
	}
	
	@GetMapping("/delete")
	public String deleteDoc(@RequestParam("id") Integer id)
	{
		documentService.deleteDocument(id);
		return "redirect:all";
	}

}
