package com.app.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Uom;
import com.app.service.IUomService;

@RestController
@RequestMapping("/rest/uom")
@CrossOrigin
public class UomRestController {

	@Autowired
	private IUomService uomService;
	
	@GetMapping("/data")
	public ResponseEntity<?> getAllUoms()
	{
		ResponseEntity<?> response=null;
		
		try {
			List<Uom> allUom = uomService.getAllUom();
			response=new ResponseEntity<>(allUom , HttpStatus.OK);
		} catch (Exception e) {
			response=new ResponseEntity<>("Something went wrong" , HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return response;
	}
}
