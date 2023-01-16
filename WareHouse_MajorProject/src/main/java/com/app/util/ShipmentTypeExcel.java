package com.app.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.model.ShipmentType;
import com.app.model.Uom;

public class ShipmentTypeExcel extends AbstractXlsView implements View {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		       //set fileName
				response.addHeader("Content-Disposition", "attachment; filename=ShipmentType_Data.xls");
				//create sheet
				Sheet sheet = workbook.createSheet("ShipmentType");
				shipmentType(sheet);
				
				@SuppressWarnings("unchecked")
				List<ShipmentType> shipmentTypeList=(List<ShipmentType>)model.get("list");
				
				shipmentTypeRecordsBody(sheet,shipmentTypeList);
	}
	private void shipmentTypeRecordsBody(Sheet sheet, List<ShipmentType> shipmentTypeList) {

		int r_no=1;
		for(ShipmentType shipmentType:shipmentTypeList)
		{
			Row row = sheet.createRow(r_no++);
			row.createCell(0).setCellValue(shipmentType.getId());
			row.createCell(1).setCellValue(shipmentType.getShipmentMode());
			row.createCell(2).setCellValue(shipmentType.getShipmentCode());
			row.createCell(3).setCellValue(shipmentType.getEnableShipment());
			row.createCell(4).setCellValue(shipmentType.getGrade());
			row.createCell(5).setCellValue(shipmentType.getShipmentDescription());
			
			
		}
		
	}

	private void shipmentType(Sheet sheet)
	{
		//create row
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("ENABLE");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("DESCRIPTION");
		
	}


}
