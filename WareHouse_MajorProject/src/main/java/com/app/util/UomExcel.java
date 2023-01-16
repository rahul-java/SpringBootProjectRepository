package com.app.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.model.Uom;

public class UomExcel extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		//set fileName
		response.addHeader("Content-Disposition", "attachment; filename=Uom_Data.xls");
		//create sheet
		Sheet sheet = workbook.createSheet("uom");
		uomHeaders(sheet);
		
		@SuppressWarnings("unchecked")
		List<Uom> uomList=(List<Uom>)model.get("list");
		
		uomRecordsBody(sheet,uomList);
		
		
	}
	
	private void uomRecordsBody(Sheet sheet, List<Uom> uomList) {

		int r_no=1;
		for(Uom uom:uomList)
		{
			Row row = sheet.createRow(r_no++);
			row.createCell(0).setCellValue(uom.getId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getUomDiscription());
			
			
		}
		
	}

	private void uomHeaders(Sheet sheet)
	{
		//create row
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("TYPE");
		row.createCell(2).setCellValue("Model");
		row.createCell(3).setCellValue("Description");
		
	}

}
